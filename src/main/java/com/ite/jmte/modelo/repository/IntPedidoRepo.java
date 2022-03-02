package com.ite.jmte.modelo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ite.jmte.modelo.beans.Pedido;

public interface IntPedidoRepo extends JpaRepository<Pedido, Integer> {

	//Query para buscar una lista de pedidos que tengan por fecha de alta la fecha introducida
	@Query("select p from Pedido p where p.fechaAlta =?1")
	List<Pedido> findPedidosByFechaAlta(@Param("fechaAlta") Date FechaAlta);
	
	//Query para buscar los pedidos por username
	@Query("select p from Pedido p where p.usuario.username =?1")
	List<Pedido> findPedidosbyUsuario(@Param("username") String username);
}
