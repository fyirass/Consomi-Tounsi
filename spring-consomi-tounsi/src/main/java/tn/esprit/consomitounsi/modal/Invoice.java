package tn.esprit.consomitounsi.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Invoice {

	
	
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Id
		private long id;
		private String paymentMode;
		private String invoiceDate;
		private double tva;
		private double totalHT;
		private double total;
		private double discount;
		private double totalDiscount;
		private double deliveryFee;
		
		
		
		public Invoice() {
			super();
		}


		public Invoice(double totalDiscount) {
			super();
			this.totalDiscount = totalDiscount;
		}


	

		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


		public String getPaymentMode() {
			return paymentMode;
		}


		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}


		public String getInvoiceDate() {
			return invoiceDate;
		}


		public void setInvoiceDate(String invoiceDate) {
			this.invoiceDate = invoiceDate;
		}


		public double getTva() {
			return tva;
		}


		public void setTva(double tva) {
			this.tva = tva;
		}


		public double getTotalHT() {
			return totalHT;
		}


		public void setTotalHT(double totalHT) {
			this.totalHT = totalHT;
		}


		public double getTotalDiscount() {
			 totalDiscount = this.totalHT * (1-(this.discount/100));
			 return totalDiscount;
		}
		public double getTotal() {
			 total =(this.deliveryFee + getTotalDiscount())+(((this.deliveryFee + getTotalDiscount()) * (this.tva))/100);
			 return total;
		}


		public double getDiscount() {
			return discount ;
		}


		public void setDiscount(double discount) {
			this.discount = discount;
		}
		


		public double getDeliveryFee() {
			return deliveryFee;
		}


		public void setDeliveryFee(double deliveryFee) {
			this.deliveryFee = deliveryFee;
		}
		
}
