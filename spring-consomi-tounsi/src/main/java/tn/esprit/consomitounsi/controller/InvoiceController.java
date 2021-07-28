package tn.esprit.consomitounsi.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.consomitounsi.exception.ResourceNotFoundException;
import tn.esprit.consomitounsi.modal.Invoice;
import tn.esprit.consomitounsi.modal.User;
import tn.esprit.consomitounsi.repository.InvoiceRepository;
import tn.esprit.consomitounsi.service.InvoiceService;
import tn.esprit.consomitounsi.service.SendEmailService;




@CrossOrigin("*")
@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	@Autowired
	private InvoiceRepository invoiceRepository;
	/*
	public List<Invoice> getAllinvoices(){
		return invoiceRepository.findAll();
	}*/
	// create invoice
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private InvoiceService invoiceService;
	@GetMapping("/getall")
	private ResponseEntity<?> getAllinvoices (){
		return invoiceService.getAllinvoices();
		}
	@PostMapping("/add")
	
	public ResponseEntity<?> addInvoice (@RequestBody Invoice invoice){
		return invoiceService.addInvoice(invoice);
	}

	/*
	public Invoice createinvoice(@Validated @RequestBody Invoice invoice) {
		String message=new String("Greetings\n" +"This email contains your invoice\n\n" +
				"Invoice Date:" + invoice.getInvoiceDate()+ "\n" +
				"Invoice number:" + invoice.getId()+ "\n" +
				"Payment mode:" + invoice.getPaymentMode()+ "\n" + "\n" +

				"Total amount excl tax:" + invoice.getTotalHT()+ "\n" +
				"Discount (%):" + invoice.getDiscount()+ "\n" +
				"Total with discount:" + invoice.getTotalDiscount()+ "\n" +
				"delivery fee:" + invoice.getDeliveryFee()+ "\n" +
				"Tax amount (%):" + invoice.getTva()+ "\n" + 
				"Total amount:" + invoice.getTotal()+ "\n" +
				 "Our sincere appreciation,\nConsomiTounsi managment."
);
		sendEmailService.sendEmail("firas.mansour@esprit.tn", message, "Invoice");
		return invoiceRepository.save(invoice);
	}
	*/
	// get invoice by id
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getinvoiceById (@PathVariable Long id) throws ResourceNotFoundException{
		return invoiceService.getinvoiceById(id);
	}
	
	/*
	public ResponseEntity<Invoice> getinvoiceById(@PathVariable(value = "id") Long invoiceId)
			throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));
		return ResponseEntity.ok().body(invoice);
	}
	*/
	// update invoice
	@PutMapping("/update/{id}")
	public ResponseEntity<Invoice> updateinvoice (@PathVariable Long id) throws ResourceNotFoundException{
		return invoiceService.updateinvoice(id, null);
	}
	/*
	public ResponseEntity<Invoice> updateinvoice(@PathVariable(value = "id") Long invoiceId,
			@Validated @RequestBody Invoice invoiceDetails) throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));

		invoice.setPaymentMode(invoiceDetails.getPaymentMode());
		invoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
		invoice.setTva(invoiceDetails.getTva());
		invoice.setTotalHT(invoiceDetails.getTotalHT());
		invoice.setDiscount(invoiceDetails.getDiscount());
		invoice.setDeliveryFee(invoiceDetails.getDeliveryFee());
		
		final Invoice updatedinvoice = invoiceRepository.save(invoice);
		return ResponseEntity.ok(updatedinvoice);
	}
	*/
	// delete invoice 
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<?> deleteinvoice (@PathVariable Long id) throws ResourceNotFoundException{
		return (ResponseEntity<?>) invoiceService.deleteinvoice(id);
		}
}
	/*
	public Map<String, Boolean> deleteinvoice(@PathVariable(value = "id") Long invoiceId)
			throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));

		invoiceRepository.delete(invoice);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
		*/