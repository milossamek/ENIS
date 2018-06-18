package enis.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import enis.backend.model.Uzivatel;
import enis.backend.repository.UzivatelRepository;
import enis.backend.utils.OffsetBasedPageRequest;

@Transactional
@Service
public class UzivatelServiceImpl implements UzivatelService {
    @Autowired
    private UzivatelRepository repository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Uzivatel> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Uzivatel> findAll(int offset, int limit) {
        return repository.findAll(new OffsetBasedPageRequest(offset, limit));
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void save(Uzivatel uzivatel) {
    	Uzivatel existingUser = findUserByUsername(uzivatel.getJmeno());
    	if(uzivatel.getId() == null){
    		uzivatel.setHeslo(bCryptPasswordEncoder.encode(uzivatel.getHeslo()));    		
    	} else if(existingUser != null && !existingUser.getHeslo().equals(uzivatel.getHeslo())){
    		uzivatel.setHeslo(bCryptPasswordEncoder.encode(uzivatel.getHeslo()));
    	}
        repository.saveAndFlush(uzivatel);
    }

    @Override
    public void delete(Uzivatel uzivatel) {
        repository.delete(uzivatel);
    }

	@Override
	public Uzivatel findUserByUsername(String username) {
		return repository.findByJmeno(username);
	}
}
