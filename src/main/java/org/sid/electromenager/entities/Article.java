package org.sid.electromenager.entities;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

	@Entity
	public class Article implements Serializable{

	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private BigDecimal prixUnitaire;
	    private BigDecimal taxeTVA;
	    private BigDecimal benefice;
	    private BigDecimal prixVente;
	    private int quantite;

	    // Getters and setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public BigDecimal getPrixUnitaire() {
	        return prixUnitaire;
	    }

	    public void setPrixUnitaire(BigDecimal prixUnitaire) {
	        this.prixUnitaire = prixUnitaire;
	    }

	    public BigDecimal getTaxeTVA() {
	        return taxeTVA;
	    }
	    
	    public void setTaxeTVA(BigDecimal TaxeTVA) {
	        this.taxeTVA = TaxeTVA;
	    }

	    public BigDecimal getBenefice() {
	        return benefice;
	    }

	    public void setBenefice(BigDecimal benefice) {
	        this.benefice = benefice;
	    }

	    public BigDecimal getPrixVente() {
	        return prixVente;
	    }
	    public void setPrixVente(BigDecimal prixVente) {
	        this.prixVente = prixVente;
	    }	    
	    public int getQuantite() {
	        return quantite;
	    }

	    public void setQuantite(int quantite) {
	        this.quantite = quantite;
	    }

	    //constructors
	    public Article() {
	    }

	    public Article(Long id, String name, BigDecimal prixUnitaire, BigDecimal taxeTva, BigDecimal benefice, BigDecimal prixVente, int quantite) {
	        this.id = id;
	        this.name = name;
	        this.prixUnitaire = prixUnitaire;
	        this.taxeTVA = taxeTva;
	        this.benefice = benefice;
	        this.prixVente = prixVente;
	        this.quantite = quantite;
	    }
	

}
