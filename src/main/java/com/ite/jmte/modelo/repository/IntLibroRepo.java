package com.ite.jmte.modelo.repository;




import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ite.jmte.modelo.beans.Libro;

public interface IntLibroRepo extends JpaRepository<Libro, Long> {
	
	@Query("select l from Libro l where l.tema.descTema like  %:descTema% ")
	List<Libro> findLibrosByTema(@Param("descTema") String descTema);
	
	@Query("select l from Libro l where l.titulo like  %:busqueda% ")
	List<Libro> findLibrosByTitulo(@Param("busqueda") String busqueda);
	
	@Query("select l from Libro l where l.isbn =?1")
	Libro findLibrosByIsbn( long isbn);

}
