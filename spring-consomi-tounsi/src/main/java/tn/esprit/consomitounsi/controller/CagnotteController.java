package tn.esprit.consomitounsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.consomitounsi.modal.Cagnotte;
import tn.esprit.consomitounsi.service.CagnotteService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path="cagnotte")
public class CagnotteController {
	@Autowired
	private CagnotteService cagnotteService;
	
	@GetMapping(value = "/getcagnottes")
	public ResponseEntity<?> getCagnottes(){
		return cagnotteService.getCagnottes();
	}
	@GetMapping(value="/getcagnotte/{id}")
	public ResponseEntity<?> getCagnotte(@PathVariable Long id){
		return cagnotteService.getCagnotte(id);
	}
	@PostMapping(value="/addcagnotte")
	public ResponseEntity<?> addCagnotte(@RequestBody Cagnotte cagnotte){
		return cagnotteService.addCagnotte(cagnotte);
	}
	@PutMapping(value="/updatecagnotte")
	public ResponseEntity<?> updateCagnotte(@RequestBody Cagnotte cagnotte){
		return cagnotteService.updateCagnotte(cagnotte);
	}
	@DeleteMapping(value="/deletecagnotte/{id}")
	public ResponseEntity<?> deleteCagnotte(@PathVariable Long id){
		return cagnotteService.deleteCagnotte(id);
	}
}
