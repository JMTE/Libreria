package com.ite.jmte.modelo.dao;

import java.util.List;

import com.ite.jmte.modelo.beans.Tema;

public interface IntTemaDao {

	List<Tema>findAll();
	
	Tema findTemaById(int idTema);
	
}
