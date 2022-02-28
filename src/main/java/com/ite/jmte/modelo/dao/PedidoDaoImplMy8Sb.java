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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido findPedidoById(int idPedido) {
		// TODO Auto-generated method stub
		return pedRepo.findById(idPedido).orElse(null);
	}

	@Override
	public int altaPedido(Pedido pedido) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
		return pedRepo.findPedidosByFechaAlta(fechaAlta);
	}

}
