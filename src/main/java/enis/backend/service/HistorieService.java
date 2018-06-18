package enis.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import enis.backend.model.Historie;

public interface HistorieService {
    List<Historie> findAll();
    Page<Historie> findAll(int offset, int limit);
    long count();
    void save(Historie historie);
    void delete(Historie historie);
}
