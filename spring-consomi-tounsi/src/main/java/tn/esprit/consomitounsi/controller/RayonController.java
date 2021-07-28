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
import tn.esprit.consomitounsi.modal.Rayon;
import tn.esprit.consomitounsi.repository.RayonRepository;


@CrossOrigin("*")
@RestController
@RequestMapping(value="/rayons")
public class RayonController {
	@Autowired
	private RayonRepository rayonRepository;
	
	// create get all rayons api
	
	@GetMapping(value="/getall")
	public List<Rayon> getAllRayons(){
		return rayonRepository.findAll();
	}
	// create rayon
	@PostMapping("/add")
	public Rayon createrayon(@Validated @RequestBody Rayon rayon) {
		return rayonRepository.save(rayon);
	}
	
	
	// get rayon by id
	@GetMapping("/getone/{id}")
	public ResponseEntity<Rayon> getRayonById(@PathVariable(value = "id") Long rayonId)
			throws ResourceNotFoundException {
		Rayon rayon = rayonRepository.findById(rayonId)
				.orElseThrow(() -> new ResourceNotFoundException("Rayon not found for this id :: " + rayonId));
		return ResponseEntity.ok().body(rayon);
	}
	// update rayon
	@PutMapping("/update/{id}")
	public ResponseEntity<Rayon> updateRayon(@PathVariable(value = "id") Long rayonId,
			@Validated @RequestBody Rayon rayonDetails) throws ResourceNotFoundException {
		Rayon rayon = rayonRepository.findById(rayonId)
				.orElseThrow(() -> new ResourceNotFoundException("rayon not found for this id :: " + rayonId));

		rayon.setRayonNumber(rayonDetails.getRayonNumber());
		rayon.setRayonName(rayonDetails.getRayonName());
		rayon.setRayonType(rayonDetails.getRayonType());
		rayon.setRayonCategory(rayonDetails.getRayonCategory());
		rayon.setStocks(rayonDetails.getStocks());
		final Rayon updatedRayon = rayonRepository.save(rayon);
		return ResponseEntity.ok(updatedRayon);
	}
	// delete rayon 
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> deleterayon(@PathVariable(value = "id") Long rayonId)
			throws ResourceNotFoundException {
		Rayon rayon = rayonRepository.findById(rayonId)
				.orElseThrow(() -> new ResourceNotFoundException("rayon not found for this id :: " + rayonId));

		rayonRepository.delete(rayon);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
