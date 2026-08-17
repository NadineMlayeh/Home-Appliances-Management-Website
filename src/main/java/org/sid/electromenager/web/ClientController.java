package org.sid.electromenager.web;

import java.math.BigDecimal;

import org.sid.electromenager.dao.ClientRepository;
import org.sid.electromenager.entities.Client;
import org.sid.electromenager.dao.AchatRepository;
import org.sid.electromenager.entities.Achat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    private AchatRepository achatRepository;
    
    @GetMapping({"/addClient", "/addclient"})
    public String showArticleForm(Model model) {
        model.addAttribute("client", new Client());
        return "formClient";
    }

    @PostMapping("/saveClient")
    public String saveClient(Client client) {
        clientRepository.save(client);
        return "redirect:/ListClients?msg=saved_client";
    }

    @GetMapping("/ListClients")
    public String list(Model model,
                       @RequestParam(name="page", defaultValue = "0") int page,
                       @RequestParam(name="size", defaultValue = "3") int size,
                       @RequestParam(name="keyword", defaultValue = "") String mc) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Client> pageArticles = clientRepository.findByNomContains(mc, pageable);
        model.addAttribute("ListClients", pageArticles.getContent());
        model.addAttribute("pages", new int[pageArticles.getTotalPages()]);
        model.addAttribute("currentpage", page);
        model.addAttribute("keyword", mc);
        return "ListClients"; 
    }



    @GetMapping(path = "/deleteClient")
    public String delete (Long id) {
        try {
            clientRepository.deleteById(id);
            return "redirect:/ListClients?msg=deleted_client";
        } catch (Exception e) {
            return "redirect:/ListClients?error=Impossible+de+supprimer+ce+client+car+il+est+lie+a+des+ventes+existantes+!";
        }
    }
    @GetMapping("/editClient")
    public String editClient(Model model, Long id) {
    Client c=clientRepository.findById(id).get();
    model.addAttribute("client", c);
    return "formClient";
    }
    

}

