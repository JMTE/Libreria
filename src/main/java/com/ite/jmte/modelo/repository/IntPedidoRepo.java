package com.ite.jmte.modelo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ite.jmte.modelo.beans.Pedido;

public interface IntPedidoRepo extends JpaRepository<Pedido, Integer> {

	
	@Query("select p from Pedido p where p.fechaAlta =?1")
	List<Pedido> findPedidosByFechaAlta(@Param("fechaAlta") Date FechaAlta);
}
