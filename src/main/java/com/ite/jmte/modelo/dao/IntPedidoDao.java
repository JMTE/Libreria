package com.ite.jmte.modelo.dao;

import java.util.Date;
import java.util.List;

import com.ite.jmte.modelo.beans.Pedido;


//Aqui definimos todos los metodos que vamos a utilizar para trabajar con Pedidos
public interface IntPedidoDao {

	//Listar todos los pedidos
	List<Pedido> findAll();
	
	//Buscar un pedido por su idPedido
	Pedido findPedidoById(int idPedido);
	
	//Dar de alta un pedido
	int altaPedido(Pedido pedido);
	
	//Buscar lista de pedidos  según una fecha introducida
	List<Pedido> findPedidosByFechaAlta(Date fechaAlta);
	
	List<Pedido> findPedidosByUsername(String username);
	
}
