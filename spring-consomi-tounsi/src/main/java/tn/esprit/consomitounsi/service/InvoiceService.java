package tn.esprit.consomitounsi.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.consomitounsi.exception.ResourceNotFoundException;
import tn.esprit.consomitounsi.modal.Invoice;
import tn.esprit.consomitounsi.repository.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	// create invoice
	@Autowired
	private SendEmailService sendEmailService;
	
	public ResponseEntity<?> getAllinvoices(){
		return new ResponseEntity<>(invoiceRepository.findAll(), HttpStatus.OK);
		
	}
	public ResponseEntity<?> addInvoice(Invoice invoice){
		String message=new String("Greetings\n" +"This email contains your invoice\n\n" +
				"Product number :12 , Product name :T-shirt619 , Product quantity :1,Product price : 40 "+ "\n" +
				"Product number :13 , Product name :shoes619 , Product quantity :1,Product price : 60 "+ "\n" +
				"Invoice Date :" + invoice.getInvoiceDate()+ "\n" +
				"Invoice number :" + invoice.getId()+ "\n" +
				"Payment mode :" + invoice.getPaymentMode()+ "\n" + "\n" +

				"Total amount excl tax :" + invoice.getTotalHT()+ "\n" +
				"Discount (%) :" + invoice.getDiscount()+ "\n" +
				"Total with discount :" + invoice.getTotalDiscount()+ "\n" +
				"delivery fee :" + invoice.getDeliveryFee()+ "\n" +
				"Tax amount (%) :" + invoice.getTva()+ "\n" + 
				"Total amount :" + invoice.getTotal()+ "\n" +
				 "Our sincere appreciation,\nConsomiTounsi managment."
);
		sendEmailService.sendEmail("firas.mansour@esprit.tn", message, "Invoice");
		return new ResponseEntity<>(invoiceRepository.save(invoice), HttpStatus.OK);
	}
			
	public ResponseEntity<Invoice> getinvoiceById(@PathVariable(value = "id") Long invoiceId)
			throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));
		return ResponseEntity.ok().body(invoice);

	}
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
