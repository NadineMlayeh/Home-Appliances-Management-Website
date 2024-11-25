package org.sid.electromenager.web;

import org.sid.electromenager.dao.AchatRepository;
import org.sid.electromenager.dao.ArticleRepository;
import org.sid.electromenager.dao.ClientRepository;
import org.sid.electromenager.dao.NotificationRepository;
import org.sid.electromenager.entities.Achat;
import org.sid.electromenager.entities.Article;
import org.sid.electromenager.entities.Client;
import org.sid.electromenager.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AchatController {
    @Autowired
    private AchatRepository achatRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/addAchat")
    public String showAchatForm(Model model) {
        model.addAttribute("achat", new Achat());
        List<Client> clients = clientRepository.findAll();
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("articles", articles);
        return "formAchat";
    }
    @PostMapping("/saveAchat")
    public String saveAchat(Achat achat, @RequestParam Long clientId, @RequestParam Long articleId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        Article article = articleRepository.findById(articleId).orElseThrow();

        achat.setClient(client);
        achat.setArticle(article);

        // Calculate montant
        BigDecimal montant = article.getPrixVente().multiply(BigDecimal.valueOf(achat.getQuantite()));
        if ("facilite".equals(achat.getModePayment())) {
            montant = montant.multiply(BigDecimal.valueOf(1.15));
           
        } else {
            achat.setMontantRestant(montant);
        }
        achat.setMontant(montant);
        achat.setAvanceH(achat.getAvance());
        achat.setTr1H(achat.getTr1());
        achat.setTr2H(achat.getTr2()); 
        achat.setTr3H(achat.getTr3()); 
        achat.setTr4H(achat.getTr4()); 
        achat.setTr5H(achat.getTr5()); 
        achat.setTr6H(achat.getTr6()); 
        achat.setTr7H(achat.getTr7()); 
        achat.setTr8H(achat.getTr8()); 
        achat.setTr9H(achat.getTr9()); 
        achat.setTr10H(achat.getTr10());
        // Check stock availability
        int availableQuantity = article.getQuantite();
        if (achat.getQuantite() > availableQuantity) {
            throw new RuntimeException("Insufficient stock for article: " + articleId);
        }

        // Update the article's quantity
        article.setQuantite(availableQuantity - achat.getQuantite());
        achatRepository.save(achat);
        
        handleArticleNotifications(article);
        return "redirect:/ListAchats";
    }
    private void handleArticleNotifications(Article article) {
        // Check if the quantity is now low
        if (article.getQuantite() <= 2) {
            String message = String.format("The quantity of %s is low (current quantity: %d)!", article.getName(), article.getQuantite());
            List<Notification> existingNotifications = notificationRepository.findByMessageContaining(article.getName());
            if (existingNotifications.isEmpty()) {
                Notification notification = new Notification(message);
                notificationRepository.save(notification);
            }
        }

        // Remove notifications if the quantity is above threshold
        if (article.getQuantite() > 2) {
            List<Notification> notificationsToRemove = notificationRepository.findByMessageContaining(article.getName());
            notificationRepository.deleteAll(notificationsToRemove);
        }
    }


    @GetMapping("/ListAchats")
    public String list(Model model,
                       @RequestParam(name="page", defaultValue = "0") int page,
                       @RequestParam(name="size", defaultValue = "3") int size,
                       @RequestParam(name="keyword", defaultValue = "") String mc) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Achat> pageAchats = achatRepository.findAll(pageable);
        model.addAttribute("ListAchats", pageAchats.getContent());
        model.addAttribute("pages", new int[pageAchats.getTotalPages()]);
        model.addAttribute("currentpage", page);
        model.addAttribute("keyword", mc);
        return "ListAchats";
    }

    @GetMapping(path = "/deleteAchat")
    public String delete(Long id) {
        achatRepository.deleteById(id);
        return "redirect:/ListAchats";
    }

    @GetMapping("/editAchat")
    public String editAchat(Model model, Long id) {
        Achat achat = achatRepository.findById(id).orElseThrow();
        model.addAttribute("achat", achat);
        List<Client> clients = clientRepository.findAll();
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("articles", articles);
        return "formAchat";
    }
    

    @GetMapping("/listAchatsByClient")
    public String listAchatsByClient(
            @RequestParam Long clientId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model) {
        
        Client client = clientRepository.findById(clientId).orElseThrow();
        PageRequest pageable = PageRequest.of(page, size);
        Page<Achat> pageAchats = achatRepository.findByClient(client, pageable);

        model.addAttribute("ListAchats", pageAchats.getContent());
        model.addAttribute("pages", new int[pageAchats.getTotalPages()]);
        model.addAttribute("currentpage", page);
        model.addAttribute("client", client);

        return "ListAchatsByClient";
    }
    @GetMapping("/facture/{id}")
    public String getFactureDetails(@PathVariable Long id, Model model) {
        Achat achat = achatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid achat Id:" + id));
        model.addAttribute("achat", achat);
        return "facture";
    }

    @GetMapping("/payment/{id}")
    public String showPaymentPage(@PathVariable("id") Long achatId, Model model) {
        Achat achat = achatRepository.findById(achatId).orElseThrow();
        model.addAttribute("achat", achat);
        return "payment";
    }

    @PostMapping("/savePayment")
    public String savePayment(
            @RequestParam Long achatId,
            @RequestParam BigDecimal montantRestant,
            @RequestParam(required = false) BigDecimal avance,
            @RequestParam(required = false) BigDecimal tr1,
            @RequestParam(required = false) BigDecimal tr2,
            @RequestParam(required = false) BigDecimal tr3,
            @RequestParam(required = false) BigDecimal tr4,
            @RequestParam(required = false) BigDecimal tr5,
            @RequestParam(required = false) BigDecimal tr6,
            @RequestParam(required = false) BigDecimal tr7,
            @RequestParam(required = false) BigDecimal tr8,
            @RequestParam(required = false) BigDecimal tr9,
            @RequestParam(required = false) BigDecimal tr10,
            @RequestParam(required = false) BigDecimal avanceH,
            @RequestParam(required = false) BigDecimal tr1H,
            @RequestParam(required = false) BigDecimal tr2H,
            @RequestParam(required = false) BigDecimal tr3H,
            @RequestParam(required = false) BigDecimal tr4H,
            @RequestParam(required = false) BigDecimal tr5H,
            @RequestParam(required = false) BigDecimal tr6H,
            @RequestParam(required = false) BigDecimal tr7H,
            @RequestParam(required = false) BigDecimal tr8H,
            @RequestParam(required = false) BigDecimal tr9H,
            @RequestParam(required = false) BigDecimal tr10H) {

        Achat achat = achatRepository.findById(achatId).orElseThrow();

        achat.setMontantRestant(montantRestant);
        if (avance != null) achat.setAvance(avance);
        if (tr1 != null) achat.setTr1(tr1);
        if (tr2 != null) achat.setTr2(tr2);
        if (tr3 != null) achat.setTr3(tr3);
        if (tr4 != null) achat.setTr4(tr4);
        if (tr5 != null) achat.setTr5(tr5);
        if (tr6 != null) achat.setTr6(tr6);
        if (tr7 != null) achat.setTr7(tr7);
        if (tr8 != null) achat.setTr8(tr8);
        if (tr9 != null) achat.setTr9(tr9);
        if (tr10 != null) achat.setTr10(tr10);


        achatRepository.save(achat);
        return "redirect:/ListAchats";
    }


}
