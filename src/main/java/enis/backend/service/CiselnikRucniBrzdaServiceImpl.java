package enis.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enis.backend.model.CiselnikRucniBrzda;
import enis.backend.repository.CiselnikRucniBrzdaRepository;

@Transactional
@Service
public class CiselnikRucniBrzdaServiceImpl implements CiselnikRucniBrzdaService {
    @Autowired
    private CiselnikRucniBrzdaRepository repository;

    @Override
    public List<CiselnikRucniBrzda> findAll() {
        return repository.findAll();
    }

    @Override
    public CiselnikRucniBrzda findById(Long id) {
        return repository.findById(id);
    }
}
