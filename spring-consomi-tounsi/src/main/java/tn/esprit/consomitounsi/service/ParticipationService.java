package tn.esprit.consomitounsi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tn.esprit.consomitounsi.modal.Participations;
import tn.esprit.consomitounsi.repository.ParticipationsRepository;

@Service
public class ParticipationService {
	@Autowired
	private ParticipationsRepository participationsRepository;

	public ResponseEntity<?> getParticipations() {
		return new ResponseEntity<> (participationsRepository.findAll(),HttpStatus.OK);
	}
	
	public ResponseEntity<?> getParticipation(Long id) {
		return new ResponseEntity<>(participationsRepository.findById(id).get(), HttpStatus.OK);

	}

	public ResponseEntity<?> addParticipations(Participations participations) {
		
		participations.setParticipationDate(new Date(System.currentTimeMillis()));
		return new ResponseEntity<>(participationsRepository.save(participations),HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateParticipations(Participations participations) {
		return new ResponseEntity<>(participationsRepository.save(participations),HttpStatus.OK);

	}
	public ResponseEntity<?> deleteParticipations(Long id) {
		participationsRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);


	}
}
