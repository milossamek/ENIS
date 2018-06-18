package enis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enis.backend.model.CiselnikRucniBrzda;

@Repository
public interface CiselnikRucniBrzdaRepository extends JpaRepository<CiselnikRucniBrzda, Long> {
    CiselnikRucniBrzda findById(Long id);
}
