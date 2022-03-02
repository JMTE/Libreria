package com.ite.jmte.modelo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Pedido;
import com.ite.jmte.modelo.repository.IntPedidoRepo;

@Service
public class PedidoDaoImplMy8Sb  implements IntPedidoDao{

	
	@Autowired
	private IntPedidoRepo pedRepo;
	
	@Override
	public List<Pedido> findAll() {
		// Lista de pedidos completa, no la utilizamos en nuestro ejemplo pero puede ser util en un futuro
		return pedRepo.findAll();
	}

	@Override
	public Pedido findPedidoById(int idPedido) {
		// Buscar pedido por id
		return pedRepo.findById(idPedido).orElse(null);
	}

	@Override
	public int altaPedido(Pedido pedido) {
		// Alta de pedido en la lista de pedidos
		int filas=0;
		
		try {
			pedRepo.save(pedido);
			filas=1;
		}catch (Exception e ){
			e.printStackTrace();
		}
		return filas;
		
		
	}

	@Override
	public List<Pedido> findPedidosByFechaAlta(Date fechaAlta) {
		// Lista de pedidos en una fecha en concreto que conseguimos mediante una query en el repository
		
		return pedRepo.findPedidosByFechaAlta(fechaAlta);
	}

	@Override
	public List<Pedido> findPedidosByUsername(String username) {
		// TODO Auto-generated method stub
		return pedRepo.findPedidosbyUsuario(username);
	}

}
