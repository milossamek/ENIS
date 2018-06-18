package enis.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enis.backend.model.Role;
import enis.backend.repository.RoleRepository;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    public List<Role> findAll() {
        return repository.findAll();
    }
}
