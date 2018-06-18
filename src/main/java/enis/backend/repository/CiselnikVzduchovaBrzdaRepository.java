package enis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enis.backend.model.CiselnikVzduchovaBrzda;

@Repository
public interface CiselnikVzduchovaBrzdaRepository extends JpaRepository<CiselnikVzduchovaBrzda, Long> {
    CiselnikVzduchovaBrzda findById(Long id);
}
