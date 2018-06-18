package enis.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import enis.backend.model.Rozmery;

public interface RozmeryRepository extends JpaRepository<Rozmery, Long> {
	List<Rozmery> findAll();
	Rozmery saveAndFlush(Rozmery entity);
	void delete(Rozmery entity);
}
