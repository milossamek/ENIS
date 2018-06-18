package enis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enis.backend.model.Historie;

@Repository
public interface HistorieRepository extends JpaRepository<Historie, Long> {
}
