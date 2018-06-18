package enis.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import enis.backend.model.StavKm;

public interface StavKmRepository extends JpaRepository<StavKm, Long> {
	List<StavKm> findAll();
	StavKm saveAndFlush(StavKm entity);
	void delete(StavKm entity);
}
