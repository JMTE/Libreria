package com.ite.jmte.modelo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
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
		// TODO Auto-generated method stub
		return libRepo.findAll();
	}

	@Override
	public Libro findLibroById(long isbn) {
		// TODO Auto-generated method stub
	
		return libRepo.findById(isbn).orElse(null);
	}

	@Override
	public int alta(Libro libro) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Libro> listaLibrosNovedades() {
		// TODO Auto-generated method stub
		List<Libro> listaNovedades=new ArrayList<Libro>();
		for (int i=0; i<libRepo.findAll().size() ;i++) {
			if (libRepo.findAll().get(i).getNovedad().equalsIgnoreCase("s")) {
				listaNovedades.add(libRepo.findAll().get(i));
			}
		}
		return listaNovedades;
	}

	@Override
	public List<Libro> listaLibrosPorTema(String descTema) {
		// TODO Auto-generated method stub
		
		List<Libro> listaPorTema=new ArrayList<Libro>();
		listaPorTema=libRepo.findLibrosByTema(descTema);
		
		return listaPorTema;
	}

	@Override
	public List<Libro> listaLibrosPorCadena(String busqueda) {
		List<Libro> listaPorTitulo=new ArrayList<Libro>();
		listaPorTitulo=libRepo.findLibrosByTitulo(busqueda);
		return listaPorTitulo;
	}

	@Override
	public int addLibroCarrito(Libro libro, List<Libro> lista) {
		// TODO Auto-generated method stub
		
		if (lista.contains(libro)) {
			System.out.println("Lo contiene");
			return lista != null?1:0;
			
		}else {
			lista.add(libro);
			return lista !=null?1:0;
		}
		
		
	}

	@Override
	public int altaTema(Tema tema) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
				int filas=0;
				
				try {
					libRepo.save(libro);
					filas=1;
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				return filas;
	}

}
