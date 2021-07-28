package tn.esprit.consomitounsi.modal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Participations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;
	@JsonFormat(pattern="MM/dd/yyyy")
	private Date participationDate;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name="cagnotte_id",nullable = false)
	private Cagnotte cagnotte;
	public Cagnotte getCagnotte() {
		return cagnotte;
	}
	public void setCagnotte(Cagnotte cagnotte) {
		this.cagnotte = cagnotte;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getParticipationDate() {
		return participationDate;
	}
	public void setParticipationDate(Date participationDate) {
		this.participationDate = participationDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
