package com.ite.jmte.modelo.dao;

import java.util.List;

import com.ite.jmte.modelo.beans.Tema;

//Aqui definimos nuestros metodos para tema
public interface IntTemaDao {

	//Buscar todos los temas existentes
	List<Tema>findAll();
	
	//Buscar tema por id de Tema
	Tema findTemaById(int idTema);
	
}
