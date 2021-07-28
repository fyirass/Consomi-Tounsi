package tn.esprit.consomitounsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tn.esprit.consomitounsi.modal.Cagnotte;
import tn.esprit.consomitounsi.repository.CagnotteRepository;
@Service
public class CagnotteService {
	@Autowired 
	private CagnotteRepository cagnotteRepository;
	public ResponseEntity<?> getCagnottes() {
		return new ResponseEntity<> (cagnotteRepository.findAll(),HttpStatus.OK);
	}
	
	public ResponseEntity<?> getCagnotte(Long id) {
		return new ResponseEntity<>(cagnotteRepository.findById(id).get(), HttpStatus.OK);

	}

	public ResponseEntity<?> addCagnotte(Cagnotte cagnotte) {
		return new ResponseEntity<>(cagnotteRepository.save(cagnotte),HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateCagnotte(Cagnotte cagnotte) {
		return new ResponseEntity<>(cagnotteRepository.save(cagnotte),HttpStatus.OK);

	}

	public ResponseEntity<?> deleteCagnotte(Long id) {
		cagnotteRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);


	}

}
