package tn.esprit.consomitounsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.consomitounsi.modal.Rayon;



@Repository
public interface RayonRepository extends JpaRepository<Rayon, Long> {

}
