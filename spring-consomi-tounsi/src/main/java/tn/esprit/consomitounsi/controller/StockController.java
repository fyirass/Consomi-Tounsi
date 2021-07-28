package tn.esprit.consomitounsi.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import tn.esprit.consomitounsi.modal.Rayon;
import tn.esprit.consomitounsi.modal.Stock;
import tn.esprit.consomitounsi.repository.RayonRepository;
import tn.esprit.consomitounsi.repository.StockRepository;
import tn.esprit.consomitounsi.service.SendEmailService;




@CrossOrigin("*")
@RestController
@RequestMapping("/stocks")
public class StockController {
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private RayonRepository rayonRepository;
	// create get all stocks api
	
	@GetMapping("/getall")
	public List<Stock> getAllstocks(){
		return stockRepository.findAll();
	}
	// create stock
	@PostMapping("/add")
	public Stock createStock(@Validated @RequestBody Stock stock) throws Exception {
		List<Rayon> rayons = null;
		try {
			rayons = rayonRepository.findAll();
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("No rayon exists" );
		}
		for(Rayon r : rayons) {
			String cat = r.getRayonCategory();
			if (cat.equals(stock.getStockCategory())) {
				r.getStocks().add(stock);
				stock.setRayon(r);
				rayonRepository.save(r);
				break;
				
			}
			if (stock.getProductNumber()<=2){
				String message=new String("Greetings dear supplier,\nOur store at ConsomiTounsi is running low on the product: "+stock.getStockName()+" by the id "+stock.getId()+"."+"\nWe would like to inform you that we are willing to buy the amount of 100 units at your earliest convenience,\nPlease contact our finance department with the purchase details.\nOur sincere appreciation,\nConsomiTounsi managment.");
					sendEmailService.sendEmail("firas.mansour@esprit.tn", message, "Restock request");
					
			}
		}
		
		return stockRepository.save(stock);
		
	}
	
	// get stock by id
	@GetMapping("/getone/{id}")
	public ResponseEntity<Stock> getstockById(@PathVariable(value = "id") Long stockId)
			throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("stock not found for this id :: " + stockId));
		return ResponseEntity.ok().body(stock);
	}
	// update stock
	@Autowired
	private SendEmailService sendEmailService;
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatestock(@PathVariable(value = "id") Long stockId,
			@Validated @RequestBody Stock stockDetails) throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("stock not found for this id :: " + stockId));

		stock.setProductNumber(stockDetails.getProductNumber());
		stock.setStockName(stockDetails.getStockName());
		stock.setStockType(stockDetails.getStockType());
		stock.setStockCategory(stockDetails.getStockCategory());
			if (stock.getProductNumber()<=2){
				String message=new String("Greetings dear supplier,\nOur store at ConsomiTounsi is running low on the product: "+stockDetails.getStockName()+" by the id "+stock.getId()+"."+"\nWe would like to inform you that we are willing to buy the amount of 100 units at your earliest convenience,\nPlease contact our finance department with the purchase details.\nOur sincere appreciation,\nConsomiTounsi managment.");
					sendEmailService.sendEmail("firas.mansour@esprit.tn", message, "Restock request");
					
				return new ResponseEntity<>(HttpStatus.OK); 
			}
			else {

				
				return ResponseEntity.ok(stockRepository.save(stock));		
			}
		
	}
	// delete stock 
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> deleteStock(@PathVariable(value = "id") Long stockId)
			throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("stock not found for this id :: " + stockId));

		stockRepository.delete(stock);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}