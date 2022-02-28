package com.ite.jmte.modelo.dao;

import java.util.Date;
import java.util.List;

import com.ite.jmte.modelo.beans.Pedido;

public interface IntPedidoDao {

	
	List<Pedido> findAll();
	
	Pedido findPedidoById(int idPedido);
	
	int altaPedido(Pedido pedido);
	
	List<Pedido> findPedidosByFechaAlta(Date fechaAlta);
	
}
