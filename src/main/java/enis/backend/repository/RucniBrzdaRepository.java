package enis.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import enis.backend.model.RucniBrzda;

public interface RucniBrzdaRepository extends JpaRepository<RucniBrzda, Long> {
	List<RucniBrzda> findAll();
	RucniBrzda saveAndFlush(RucniBrzda entity);
	void delete(RucniBrzda entity);
}
