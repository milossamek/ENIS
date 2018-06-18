package enis.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enis.backend.model.CiselnikVzduchovaBrzda;
import enis.backend.repository.CiselnikVzduchovaBrzdaRepository;

@Transactional
@Service
public class CiselnikvzduchovabrzdaServiceImpl implements CiselnikVzduchovaBrzdaService {
    @Autowired
    private CiselnikVzduchovaBrzdaRepository repository;

    @Override
    public List<CiselnikVzduchovaBrzda> findAll() {
        return repository.findAll();
    }

    @Override
    public CiselnikVzduchovaBrzda findById(Long id) {
        return repository.findById(id);
    }
}
