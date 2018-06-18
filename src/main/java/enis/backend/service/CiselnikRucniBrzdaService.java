package enis.backend.service;

import java.util.List;

import enis.backend.model.CiselnikRucniBrzda;

public interface CiselnikRucniBrzdaService {
    List<CiselnikRucniBrzda> findAll();
    CiselnikRucniBrzda findById(Long id);
}
