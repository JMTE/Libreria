package com.ite.jmte.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Perfile;
import com.ite.jmte.modelo.repository.IntPerfilesRepo;

@Service
public class PerfileDaoImplMy8Sb implements IntPerfilesDao{
	
	@Autowired
	private IntPerfilesRepo perRepo;

	@Override
	public List<Perfile> findAll() {
		// TODO Auto-generated method stub
		return perRepo.findAll();
	}
	
	

}
