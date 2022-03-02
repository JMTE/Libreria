package com.ite.jmte.modelo.dao;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Libro;
import com.ite.jmte.modelo.beans.Tema;
import com.ite.jmte.modelo.repository.IntLibroRepo;
import com.ite.jmte.modelo.repository.IntTemaRepo;

@Service
public class LibroDaoImplMy8Sb implements IntLibroDao {

	
	@Autowired
	private IntLibroRepo libRepo;
	
	@Autowired
	private IntTemaRepo temRepo;
	
	@Override
	public List<Libro> findAll() {
		// Para listar todos los libros utilizamos el metodo que nos proporciona JPA 
		return libRepo.findAll();
	}

	@Override
	public Libro findLibroById(long isbn) {
		// Para buscar un libro por id o isbn utilizamos el metodo que nos proporciona JPA
	
		return libRepo.findById(isbn).orElse(null);
	}

	
	@Override
	public List<Libro> listaLibrosNovedades() {
		// Para listar los libros por novedades utilizamos el metodo que hemos conseguido mediante query en el repository
		
		return libRepo.findLibrosByNovedades();
	}

	@Override
	public List<Libro> listaLibrosPorTema(String descTema) {
		// Para listar los libros por tema utilizamos el metodo que hemos conseguido mediante query en el repository
		
		
		return libRepo.findLibrosByTema(descTema);
	}

	@Override
	public List<Libro> listaLibrosPorCadena(String busqueda) {
		// Para listar los libros por titulo utilizamos el metodo que hemos conseguido mediante query en el repository
		
		return libRepo.findLibrosByTitulo(busqueda);
	}

	@Override
	public int addLibroCarrito(Libro libro, List<Libro> lista) {
		// Para añadir un libro al carrito, traemos la lista que tenemos actualizada del carrito y el libro a añadir
		
		//Comprobamos si ese libro ya lo contiene la lista, si lo contiene salimos sin añadirlo, si no lo contiene, lo añadimos al carrito
		if (lista.contains(libro)) {
			
			return lista != null?1:0;
			
		}else {
			lista.add(libro);
			return lista !=null?1:0;
		}
		
		
	}

	@Override
	public int altaTema(Tema tema) {
		// Aqui añadimos un tema nuevo de libros
		int filas=0;
		
		try {
			temRepo.save(tema);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}

	@Override
	public int altaLibro(Libro libro) {
		// Aqui añadimos un libro nuevo a la lista de libros
				int filas=0;
				
				try {
					libRepo.save(libro);
					filas=1;
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				return filas;
	}

	
	//Con el siguiente metodo permitimos que el administrador modifique los datos de los libros
	@Override
	public int modificarLibro(Libro libro) {
		int filas=0;
		//Si no existe no lo añadimos
		if (libRepo.findAll().indexOf(libro)==-1) {
		return filas;	
		//Si existe lo modificamos
		}else {
			try {
				libRepo.save(libro);
				filas=1;
			}catch (Exception e) {
				e.printStackTrace();
				
			}
			return filas;
		}
		
	}

	
	//Con este metodo permitimos que el administrador borre un libro de la lista
	@Override
	public int eliminarLibro(Libro libro) {
		// TODO Auto-generated method stub
		int filas=0;
		
		System.out.println(libro);
		try {
			libRepo.deleteById(libro.getIsbn());
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return filas;
	}

}
