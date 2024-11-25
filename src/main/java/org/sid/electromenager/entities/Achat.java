package org.sid.electromenager.entities;

import jakarta.persistence.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Achat implements Serializable{

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "client_id", nullable = false)
	    private Client client;

	    @ManyToOne
	    @JoinColumn(name = "article_id", nullable = false)
	    private Article article;

	    @Temporal(TemporalType.DATE)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    @Column(name = "date_achat")
	    private Date dateAchat;
	    private int quantite;
	    private BigDecimal montant;
	    private BigDecimal montantRestant;
	    private String modePayment;
	    private BigDecimal avance = BigDecimal.ZERO;
	    private BigDecimal tr1 = BigDecimal.ZERO;
	    private BigDecimal tr2 = BigDecimal.ZERO;
	    private BigDecimal tr3 = BigDecimal.ZERO;
	    private BigDecimal tr4 = BigDecimal.ZERO;
	    private BigDecimal tr5 = BigDecimal.ZERO;
	    private BigDecimal tr6 = BigDecimal.ZERO;
	    private BigDecimal tr7 = BigDecimal.ZERO;
	    private BigDecimal tr8 = BigDecimal.ZERO;
	    private BigDecimal tr9 = BigDecimal.ZERO;
	    private BigDecimal tr10 = BigDecimal.ZERO;
	    private BigDecimal avanceH ;
	    private BigDecimal tr1H ;
	    private BigDecimal tr2H ;
	    private BigDecimal tr3H ;
	    private BigDecimal tr4H ;
	    private BigDecimal tr5H ;
	    private BigDecimal tr6H ;
	    private BigDecimal tr7H ;
	    private BigDecimal tr8H ;
	    private BigDecimal tr9H ;
	    private BigDecimal tr10H ;
	    
	    // Getters and setters
	    
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Client getClient() {
	        return client;
	    }

	    public void setClient(Client client) {
	        this.client = client;
	    }

	    public Article getArticle() {
	        return article;
	    }

	    public void setArticle(Article article) {
	        this.article = article;
	    }

	    public Date getDateAchat() {
	        return dateAchat;
	    }

	    public void setDateAchat(Date dateAchat) {
	        this.dateAchat = dateAchat;
	    }

	    public int getQuantite() {
	        return quantite;
	    }

	    public void setQuantite(int quantite) {
	        this.quantite = quantite;
	    }

	    public BigDecimal getMontant() {
	        return montant;
	    }	    
	    public void setMontant(BigDecimal montant) {
	        this.montant = montant;
	        this.montantRestant = montant; // Initialize montantRestant when montant is set
	    }
	    public BigDecimal getMontantRestant() {
	        return montantRestant;
	    }

	    public void setMontantRestant(BigDecimal montantRestant) {
	        this.montantRestant = montantRestant;
	    }

	    public String getModePayment() {
	        return modePayment;
	    }

	    public void setModePayment(String modePayment) {
	        this.modePayment = modePayment;
	    }
	    public BigDecimal getAvance() {
	        return avance;
	    }

	    public void setAvance(BigDecimal avance) {
	        this.avance = avance;
	    }
	    public BigDecimal getAvanceH() {
	        return avanceH;
	    }

	    public void setAvanceH(BigDecimal avance) {
	        this.avanceH = avance;
	    }
	    public BigDecimal getTr1() {
	        return tr1;
	    }

	    public void setTr1(BigDecimal tr1) {
	        this.tr1 = tr1;
	    }

	    public BigDecimal getTr2() {
	        return tr2;
	    }

	    public void setTr2(BigDecimal tr2) {
	        this.tr2 = tr2;
	    }

	    public BigDecimal getTr3() {
	        return tr3;
	    }

	    public void setTr3(BigDecimal tr3) {
	        this.tr3 = tr3;
	    }

	    public BigDecimal getTr4() {
	        return tr4;
	    }

	    public void setTr4(BigDecimal tr4) {
	        this.tr4 = tr4;
	    }

	    public BigDecimal getTr5() {
	        return tr5;
	    }

	    public void setTr5(BigDecimal tr5) {
	        this.tr5 = tr5;
	    }

	    public BigDecimal getTr6() {
	        return tr6;
	    }

	    public void setTr6(BigDecimal tr6) {
	        this.tr6 = tr6;
	    }

	    public BigDecimal getTr7() {
	        return tr7;
	    }

	    public void setTr7(BigDecimal tr7) {
	        this.tr7 = tr7;
	    }

	    public BigDecimal getTr8() {
	        return tr8;
	    }

	    public void setTr8(BigDecimal tr8) {
	        this.tr8 = tr8;
	    }

	    public BigDecimal getTr9() {
	        return tr9;
	    }

	    public void setTr9(BigDecimal tr9) {
	        this.tr9 = tr9;
	    }

	    public BigDecimal getTr10() {
	        return tr10;
	    }

	    public void setTr10(BigDecimal tr10) {
	        this.tr10 = tr10;
	    }
	    
	    public BigDecimal getTr1H() {
	        return tr1H;
	    }

	    public void setTr1H(BigDecimal tr1) {
	        this.tr1H = tr1;
	    }

	    public BigDecimal getTr2H() {
	        return tr2H;
	    }

	    public void setTr2H(BigDecimal tr2) {
	        this.tr2H = tr2;
	    }

	    public BigDecimal getTr3H() {
	        return tr3H;
	    }

	    public void setTr3H(BigDecimal tr3) {
	        this.tr3H = tr3;
	    }

	    public BigDecimal getTr4H() {
	        return tr4H;
	    }

	    public void setTr4H(BigDecimal tr4) {
	        this.tr4H = tr4;
	    }

	    public BigDecimal getTr5H() {
	        return tr5H;
	    }

	    public void setTr5H(BigDecimal tr5) {
	        this.tr5H = tr5;
	    }

	    public BigDecimal getTr6H() {
	        return tr6H;
	    }

	    public void setTr6H(BigDecimal tr6) {
	        this.tr6H = tr6;
	    }

	    public BigDecimal getTr7H() {
	        return tr7H;
	    }

	    public void setTr7H(BigDecimal tr7) {
	        this.tr7H = tr7;
	    }

	    public BigDecimal getTr8H() {
	        return tr8H;
	    }

	    public void setTr8H(BigDecimal tr8) {
	        this.tr8H = tr8;
	    }

	    public BigDecimal getTr9H() {
	        return tr9H;
	    }

	    public void setTr9H(BigDecimal tr9) {
	        this.tr9H = tr9;
	    }

	    public BigDecimal getTr10H() {
	        return tr10H;
	    }

	    public void setTr10H(BigDecimal tr10) {
	        this.tr10H = tr10;
	    }

	    //constructors
	    public Achat() {
	        // Initialize montantRestant to montant when the object is created
	        this.montantRestant = this.montant;

	    }

	    public Achat(Long id, Client client, Article article, Date dateAchat, int quantite, BigDecimal montant, String modePayment) {
	        this.id = id;
	        this.client = client;
	        this.article = article;
	        this.dateAchat = dateAchat;
	        this.quantite = quantite;
	        this.montant = montant;
	        this.modePayment = modePayment;
	        this.montantRestant = montant;


	    }
	}

