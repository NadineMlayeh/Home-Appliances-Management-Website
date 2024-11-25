package org.sid.electromenager;


import org.sid.electromenager.entities.Client;
import org.sid.electromenager.entities.Article;
import org.sid.electromenager.dao.ClientRepository;
import org.sid.electromenager.dao.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ElectromenagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectromenagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, ArticleRepository articleRepository) {
        return args -> {
            // Insert data into Client table
           /* clientRepository.save(new Client(null, "Dupont", "Jean", new SimpleDateFormat("yyyy-MM-dd").parse("1980-05-15"), "12 rue de la République, Paris", "0123456789"));
            clientRepository.save(new Client(null, "Martin", "Claire", new SimpleDateFormat("yyyy-MM-dd").parse("1992-07-22"), "45 avenue Victor Hugo, Lyon", "0987654321"));
            clientRepository.save(new Client(null, "Durand", "Paul", new SimpleDateFormat("yyyy-MM-dd").parse("1985-11-30"), "78 boulevard Saint-Germain, Paris", "0147258369"));
            clientRepository.save(new Client(null, "Leroy", "Sophie", new SimpleDateFormat("yyyy-MM-dd").parse("1990-03-10"), "23 rue des Champs-Élysées, Paris", "0165783921"));
            clientRepository.save(new Client(null, "Dupont", "Jean", new SimpleDateFormat("yyyy-MM-dd").parse("1980-05-15"), "12 rue de la République, Paris", "0123456789"));
            clientRepository.save(new Client(null, "Martin", "Claire", new SimpleDateFormat("yyyy-MM-dd").parse("1992-07-22"), "45 avenue Victor Hugo, Lyon", "0987654321"));
            clientRepository.save(new Client(null, "Durand", "Paul", new SimpleDateFormat("yyyy-MM-dd").parse("1985-11-30"), "78 boulevard Saint-Germain, Paris", "0147258369"));
            clientRepository.save(new Client(null, "Leroy", "Sophie", new SimpleDateFormat("yyyy-MM-dd").parse("1990-03-10"), "23 rue des Champs-Élysées, Paris", "0165783921"));
            clientRepository.save(new Client(null, "Dupont", "Jean", new SimpleDateFormat("yyyy-MM-dd").parse("1980-05-15"), "12 rue de la République, Paris", "0123456789"));
            clientRepository.save(new Client(null, "Martin", "Claire", new SimpleDateFormat("yyyy-MM-dd").parse("1992-07-22"), "45 avenue Victor Hugo, Lyon", "0987654321"));
            clientRepository.save(new Client(null, "Durand", "Paul", new SimpleDateFormat("yyyy-MM-dd").parse("1985-11-30"), "78 boulevard Saint-Germain, Paris", "0147258369"));
            clientRepository.save(new Client(null, "Leroy", "Sophie", new SimpleDateFormat("yyyy-MM-dd").parse("1990-03-10"), "23 rue des Champs-Élysées, Paris", "0165783921"));
*/
            // Insert data into Article table
           /* articleRepository.save(new Article(null, "Fridge", BigDecimal.valueOf(500.00), BigDecimal.valueOf(100.00), BigDecimal.valueOf(50.00), BigDecimal.valueOf(650.00), 10));
            articleRepository.save(new Article(null, "Washing Machine", BigDecimal.valueOf(300.00), BigDecimal.valueOf(60.00), BigDecimal.valueOf(30.00), BigDecimal.valueOf(390.00), 15));
            articleRepository.save(new Article(null, "Oven", BigDecimal.valueOf(200.00), BigDecimal.valueOf(40.00), BigDecimal.valueOf(20.00), BigDecimal.valueOf(260.00), 20));
            articleRepository.save(new Article(null, "Microwave", BigDecimal.valueOf(100.00), BigDecimal.valueOf(20.00), BigDecimal.valueOf(10.00), BigDecimal.valueOf(130.00), 25));
            articleRepository.save(new Article(null, "Fridge", BigDecimal.valueOf(500.00), BigDecimal.valueOf(100.00), BigDecimal.valueOf(50.00), BigDecimal.valueOf(650.00), 10));
            articleRepository.save(new Article(null, "Washing Machine", BigDecimal.valueOf(300.00), BigDecimal.valueOf(60.00), BigDecimal.valueOf(30.00), BigDecimal.valueOf(390.00), 15));
            articleRepository.save(new Article(null, "Oven", BigDecimal.valueOf(200.00), BigDecimal.valueOf(40.00), BigDecimal.valueOf(20.00), BigDecimal.valueOf(260.00), 20));
            articleRepository.save(new Article(null, "Microwave", BigDecimal.valueOf(100.00), BigDecimal.valueOf(20.00), BigDecimal.valueOf(10.00), BigDecimal.valueOf(130.00), 25));*/
        };
    }
}
