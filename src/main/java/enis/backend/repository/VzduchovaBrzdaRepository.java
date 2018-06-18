package enis.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import enis.backend.model.VzduchovaBrzda;

public interface VzduchovaBrzdaRepository extends JpaRepository<VzduchovaBrzda, Long> {
	List<VzduchovaBrzda> findAll();
	VzduchovaBrzda saveAndFlush(VzduchovaBrzda entity);
	void delete(VzduchovaBrzda entity);
}
