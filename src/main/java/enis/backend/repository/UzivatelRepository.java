package enis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enis.backend.model.Uzivatel;

@Repository
public interface UzivatelRepository extends JpaRepository<Uzivatel, Long> {
	Uzivatel findByJmeno(String username);	
}
