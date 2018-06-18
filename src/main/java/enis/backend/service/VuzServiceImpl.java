package enis.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import enis.backend.model.Vuz;
import enis.backend.repository.VuzRepository;
import enis.backend.utils.OffsetBasedPageRequest;

@Transactional
@Service
public class VuzServiceImpl implements VuzService {

    @Autowired
    private VuzRepository repository;

    public Page<Vuz> findAll(int offset, int limit) {
        return repository.findAll(new OffsetBasedPageRequest(offset, limit));
    }
    
    @Override
    public List<Vuz> findAll() {
    	return repository.findAll();
    }
    
    public Vuz saveVuz(Vuz vuz) {
    	return  repository.saveAndFlush(vuz);
    }

	@Override
	public void delete(Vuz vuz) {
		repository.delete(vuz);
	}
	
	public long count(){
		return repository.count();
	}

    @Override
    public Vuz getVuz(long cisVozu) {
      return repository.findByVuzCis(cisVozu);
    }


}
