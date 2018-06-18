package enis.backend.service;

import java.util.List;

import enis.backend.model.CiselnikVzduchovaBrzda;

public interface CiselnikVzduchovaBrzdaService {
    List<CiselnikVzduchovaBrzda> findAll();
    CiselnikVzduchovaBrzda findById(Long id);
}
