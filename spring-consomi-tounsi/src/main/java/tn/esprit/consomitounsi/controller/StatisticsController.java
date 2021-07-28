package tn.esprit.consomitounsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.consomitounsi.service.StatisticsService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path = "stats")
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;
	@PostMapping(value = "/gain")
	private ResponseEntity<?> gain(){
		return null;
	}
	static class ObjHandler{
		
	}
}
