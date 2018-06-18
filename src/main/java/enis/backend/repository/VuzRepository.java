package enis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enis.backend.model.Vuz;

@Repository
public interface VuzRepository extends JpaRepository<Vuz, Long> {
    Vuz findByVuzCis(Long vuzCis);
}
