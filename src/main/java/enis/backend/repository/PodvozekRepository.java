package enis.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import enis.backend.model.Podvozek;

public interface PodvozekRepository extends JpaRepository<Podvozek, Long> {
	List<Podvozek> findAll();
	Podvozek saveAndFlush(Podvozek entity);
	void delete(Podvozek entity);
}
