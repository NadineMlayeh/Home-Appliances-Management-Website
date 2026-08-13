package org.sid.electromenager.entities;

import java.util.Date;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private boolean isRead = false; // Indicates if the notification has been read
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // Add getter and setter methods
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        this.isRead = read;
    }

    // Constructors, getters, and setters    
    public Notification() {
    }
    public Notification(String message) {
        this.message = message;
    }  
    public Notification(Long id, String msg, boolean read) {
        this.id = id;
        this.message = msg;
        this.isRead = read;
    }
}
