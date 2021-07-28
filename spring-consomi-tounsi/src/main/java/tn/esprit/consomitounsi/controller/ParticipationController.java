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

import tn.esprit.consomitounsi.modal.Participations;
import tn.esprit.consomitounsi.service.ParticipationService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path="participations")
public class ParticipationController {
	@Autowired
	private ParticipationService participationsService;
	@GetMapping(value = "/getparticipations")
	public ResponseEntity<?> getParticipations(){
		return participationsService.getParticipations();
	}
	@GetMapping(value="/getparticipation/{id}")
	public ResponseEntity<?> getParticipation(@PathVariable Long id){
		return participationsService.getParticipation(id);
	}
	@PostMapping(value="/addparticipation")
	public ResponseEntity<?> addParticipation(@RequestBody Participations participationss){
		return participationsService.addParticipations(participationss);
	}
	@PutMapping(value="/updateparticipation")
	public ResponseEntity<?> updateParticipation(@RequestBody Participations participationss){
		return participationsService.updateParticipations(participationss);
	}
	@DeleteMapping(value="/deleteparticipation/{id}")
	public ResponseEntity<?> deleteParticipation(@PathVariable Long id){
		return participationsService.deleteParticipations(id);
	}
	
	
}
