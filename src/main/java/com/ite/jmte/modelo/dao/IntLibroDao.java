package com.ite.jmte.modelo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ite.jmte.modelo.beans.Libro;
import com.ite.jmte.modelo.beans.Tema;

public interface IntLibroDao {
	
	List<Libro> findAll();
	
	List<Libro> listaLibrosNovedades();
	
	Libro findLibroById(long isbn);
	
	int alta (Libro libro);
	
	List<Libro> listaLibrosPorTema(String descTema);
	
	List<Libro> listaLibrosPorCadena(String busqueda);
	
	int addLibroCarrito(Libro libro, List<Libro> lista);
	
	int altaTema(Tema tema);
	
	int altaLibro(Libro libro);
	
	
	

}
