package tn.esprit.consomitounsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.consomitounsi.modal.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>  {

}





