package tn.esprit.consomitounsi.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stocks")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "Product_number")
	private int productNumber;
	@Column(name = "stock_name")
	private String stockName;
	@Column(name = "stock_category")
	private String stockCategory;
	@Column(name = "stock_type")
	private String stockType;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rayon_id")
	private Rayon rayon;
	
	public Rayon getRayon() {
		return rayon;
	}

	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}
	public Stock() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getProductNumber() {
		return this.productNumber;
	}
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
	public String getStockName() {
		return stockName;
	}
	

	public Stock(long id, int productNumber, String stockName, String stockCategory, String stockType, Rayon rayon) {
		super();
		this.id = id;
		this.productNumber = productNumber;
		this.stockName = stockName;
		this.stockCategory = stockCategory;
		this.stockType = stockType;
		this.rayon = rayon;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockCategory() {
		return stockCategory;
	}
	public void setStockCategory(String stockCategory) {
		this.stockCategory = stockCategory;
	}
	public String getStockType() {
		return stockType;
	}
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
	
	
}
