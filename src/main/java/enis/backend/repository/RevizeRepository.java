package enis.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import enis.backend.model.Revize;

public interface RevizeRepository extends JpaRepository<Revize, Long> {
	List<Revize> findAll();
	Revize saveAndFlush(Revize entity);
	void delete(Revize entity);
}
