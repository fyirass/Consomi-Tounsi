package tn.esprit.consomitounsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.consomitounsi.modal.Participations;

@Repository
public interface ParticipationsRepository extends JpaRepository<Participations, Long>{

}
