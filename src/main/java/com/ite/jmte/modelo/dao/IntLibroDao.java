package com.ite.jmte.modelo.dao;


import java.util.List;

import com.ite.jmte.modelo.beans.Libro;
import com.ite.jmte.modelo.beans.Tema;


//Aqui definimos todos los metodos que vamos a utilizar para trabajar con Libros
public interface IntLibroDao {
	
	//Buscar todos los libros
	List<Libro> findAll();
	
	//Buscar los libros por novedades (Lo realizamos usando una query)
	List<Libro> listaLibrosNovedades();
	
	//Buscar los libros por isbn 
	Libro findLibroById(long isbn);
	
	//Buscar libros por tema (lo realizamos usando una query)
	List<Libro> listaLibrosPorTema(String descTema);
	
	//Buscar libros por titulo o cadena (lo realizamos usando una query)
	List<Libro> listaLibrosPorCadena(String busqueda);
	
	//Añadir libros al carrito
	int addLibroCarrito(Libro libro, List<Libro> lista);
	
	//Dar de alta un tema 
	int altaTema(Tema tema);
	
	//Dar de alta un libro
	int altaLibro(Libro libro);
	
	//Modificar un libro
	int modificarLibro (Libro libro);
	
	//Eliminar un libro
	int eliminarLibro (Libro libro);
	
	
	

}
