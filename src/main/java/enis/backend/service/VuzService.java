package enis.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import enis.backend.model.Vuz;

public interface VuzService {
	Page<Vuz> findAll(int offset, int limit);
	List<Vuz> findAll();
	Vuz saveVuz(Vuz vuz);
	void delete(Vuz vuz);
	long count();
	Vuz getVuz(long cisVozu);
}
