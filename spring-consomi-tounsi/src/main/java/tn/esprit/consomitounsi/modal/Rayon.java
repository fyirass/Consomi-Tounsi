package tn.esprit.consomitounsi.modal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "rayons")
public class Rayon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "rayon_number")
	private int rayonNumber;
	@Column(name = "rayon_name")
	private String rayonName;
	@Column(name = "rayon_type")
	private String rayonType;
	@Column(name = "rayon_category")
	private String rayonCategory;
	
	
	@OneToMany(mappedBy = "rayon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Stock> stocks;
	
	
	public Set<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	public Rayon() {
		super();
	}
	
	
	public Rayon(long id, int rayonNumber, String rayonName, String rayonType, String rayonCategory,
			Set<Stock> stocks) {
		super();
		this.id = id;
		this.rayonNumber = rayonNumber;
		this.rayonName = rayonName;
		this.rayonType = rayonType;
		this.rayonCategory = rayonCategory;
		this.stocks = stocks;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRayonNumber() {
		return rayonNumber;
	}
	public void setRayonNumber(int rayonNumber) {
		this.rayonNumber = rayonNumber;
	}
	public String getRayonName() {
		return rayonName;
	}
	public void setRayonName(String rayonName) {
		this.rayonName = rayonName;
	}
	
	public String getRayonType() {
		return rayonType;
	}
	public void setRayonType(String rayonType) {
		this.rayonType = rayonType;
	}
	public String getRayonCategory() {
		return rayonCategory;
	}
	public void setRayonCategory(String rayonCategory) {
		this.rayonCategory = rayonCategory;
	}
	
	
	
	

}
