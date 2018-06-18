package enis.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import enis.backend.model.Uzivatel;

public interface UzivatelService {
    List<Uzivatel> findAll();
    Page<Uzivatel> findAll(int offset, int limit);
    Uzivatel findUserByUsername(String username);
    long count();
    void save(Uzivatel uzivatel);
    void delete(Uzivatel uzivatel);
}
