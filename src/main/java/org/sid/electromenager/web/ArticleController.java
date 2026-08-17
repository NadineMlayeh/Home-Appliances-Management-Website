package org.sid.electromenager.web;

import org.sid.electromenager.entities.Article;
import org.sid.electromenager.entities.Notification;
import org.sid.electromenager.dao.ArticleRepository;
import org.sid.electromenager.dao.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.sid.electromenager.dao.ClientRepository;
import org.sid.electromenager.dao.AchatRepository;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AchatRepository achatRepository;
    
    @GetMapping({"/addArticle", "/addarticle"})
    public String showArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "formArticle";
    }
    @GetMapping("/index")
    public String showIndex(Model model) {
        List<Notification> notifications = notificationRepository.findAll();
        Collections.reverse(notifications);
        long unreadCount = notifications.stream().count();

        model.addAttribute("unreadCount", unreadCount);
        model.addAttribute("notifications", notifications);
        model.addAttribute("article", new Article());

        model.addAttribute("totalClients", clientRepository.count());
        model.addAttribute("totalArticles", articleRepository.count());
        model.addAttribute("totalAchats", achatRepository.count());
        model.addAttribute("lowStockCount", articleRepository.findAll().stream().filter(a -> a.getQuantite() <= 2).count());

        return "index";
    }

    @PostMapping("/markNotificationsAsRead")
    public String markNotificationsAsRead(){
        List<Notification> notifications = notificationRepository.findAll();
        notifications.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(notifications);
        return "redirect:/index";    
    }
    @PostMapping("/saveArticle")
    @Transactional
    public String saveArticle(Article article) {
        // Convert input values to BigDecimal
        BigDecimal prixUnitaire = new BigDecimal(article.getPrixUnitaire().toString());
        BigDecimal benefice = new BigDecimal(article.getBenefice().toString());

        // Calculate taxeTVA and prixVente
        BigDecimal taxeTva = prixUnitaire.multiply(BigDecimal.valueOf(0.20));
        BigDecimal prixVente = prixUnitaire.add(taxeTva).add(benefice);
        
        article.setTaxeTVA(taxeTva);
        article.setPrixVente(prixVente);
        articleRepository.save(article);

        // Check quantity and add notification if needed
        if (article.getQuantite() <= 2) {
            String message = String.format("La quantite du %s est faible !(il reste : %d)", article.getName(), article.getQuantite());
            List<Notification> existingNotifications = notificationRepository.findByArticleId(article.getId());
            if (existingNotifications == null || existingNotifications.isEmpty()) {
                Notification notification = new Notification(message);
                notification.setArticle(article);
                notificationRepository.save(notification);
            }
        }
        if (article.getQuantite() > 2) {
            List<Notification> notificationsToRemove = notificationRepository.findByArticleId(article.getId());
            if (notificationsToRemove != null && !notificationsToRemove.isEmpty()) {
                notificationRepository.deleteAll(notificationsToRemove);
            }
        }
        return "redirect:/ListArticles?msg=saved_article";
    }

    @GetMapping("/ListArticles")
    public String list(Model model,
                       @RequestParam(name="page", defaultValue = "0") int page,
                       @RequestParam(name="size", defaultValue = "3") int size,
                       @RequestParam(name="keyword", defaultValue = "") String mc) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Article> pageArticles = articleRepository.findByNameContains(mc, pageable);
        model.addAttribute("ListArticles", pageArticles.getContent());
        model.addAttribute("pages", new int[pageArticles.getTotalPages()]);
        model.addAttribute("currentpage", page);
        model.addAttribute("keyword", mc);
        return "ListArticles"; 
    }


    @GetMapping(path = "/deleteArticle")
    public String delete (Long id) {
        try {
            List<Notification> notifications = notificationRepository.findByArticleId(id);
            if (notifications != null && !notifications.isEmpty()) {
                notificationRepository.deleteAll(notifications);
            }
            articleRepository.deleteById(id);
            return "redirect:/ListArticles?msg=deleted_article";
        } catch (Exception e) {
            return "redirect:/ListArticles?error=Impossible+de+supprimer+cet+article+car+il+est+lie+a+des+ventes+existantes+!";
        }
    }
    @GetMapping("/editArticle")
    public String editPatient(Model model, Long id) {
    Article a=articleRepository.findById(id).get();
    model.addAttribute("article", a);
    return "formArticle";
    }
    
    @GetMapping("/addqte")
    public String showAddQuantityPage(@RequestParam Long articleId, Model model) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + articleId));
        model.addAttribute("article", article);
        return "addqte";
    }

    @PostMapping("/updateQuantity")
    public String updateQuantity(@RequestParam Long articleId, @RequestParam int quantity) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + articleId));
        
        article.setQuantite(quantity);
        articleRepository.save(article);
        if (quantity > 2) {
            // Find notifications related to this article
            List<Notification> notifications = notificationRepository.findByArticleId(articleId);
            
            // Delete the related notifications
            notificationRepository.deleteAll(notifications);
        }        
        return "redirect:/ListArticles?msg=updated_quantity";
    }

}
