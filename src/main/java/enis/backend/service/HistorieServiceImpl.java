package enis.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import enis.backend.model.Historie;
import enis.backend.repository.HistorieRepository;
import enis.backend.utils.OffsetBasedPageRequest;

@Transactional
@Service
public class HistorieServiceImpl implements HistorieService {
    @Autowired
    private HistorieRepository repository;


    @Override
    public List<Historie> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Historie> findAll(int offset, int limit) {
        return repository.findAll(new OffsetBasedPageRequest(offset, limit));
    }

    @Override
    public long count() {
            return repository.count();
    }

    @Override
    public void save(Historie historie) {
        repository.saveAndFlush(historie);
    }

    @Override
    public void delete(Historie historie) {
            repository.delete(historie);
    }
}
