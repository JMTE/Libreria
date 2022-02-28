package com.ite.jmte.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Tema;
import com.ite.jmte.modelo.repository.IntTemaRepo;

@Service
public class TemaDaoImplMy8Sb implements IntTemaDao{

	
	@Autowired
	private IntTemaRepo temRepo;
	@Override
	public List<Tema> findAll() {
		// TODO Auto-generated method stub
		return temRepo.findAll();
	}

	@Override
	public Tema findTemaById(int idTema) {
		// TODO Auto-generated method stub
		
		
		return temRepo.findById(idTema).orElse(null);
	}

	
	
}
