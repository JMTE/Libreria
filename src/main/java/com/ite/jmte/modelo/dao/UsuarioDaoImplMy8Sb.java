package com.ite.jmte.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Usuario;
import com.ite.jmte.modelo.repository.IntUsuariosRepo;
@Service
public class UsuarioDaoImplMy8Sb implements IntUsuarioDao{

	@Autowired
	private IntUsuariosRepo usuRepo;
	
	
	@Override
	public List<Usuario> findAll() {
		// Todos los usuarios mediante el metodo que nos proporciona JPA
		return usuRepo.findAll();
	}

	@Override
	public Usuario findUsuarioByUsername(String username) {
		// Buscar un usuario por id mediante el metodo que nos proporciona JPA
		
		return usuRepo.findById(username).orElse(null);
	}

	@Override
	public int altaUsuario(Usuario usuario) {
		// Dar de alta un usuario
		
		int filas=0;
		
		if (usuRepo.findAll().contains(usuario)) {
			filas=0;
			return filas;
		}else {
			try {
				usuRepo.save(usuario);
				filas=1;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return filas;
		}
		
		
	}

	@Override
	public List<Usuario> findUsuariosClientes() {
		// Buscar la lista de usuarios que sean clientes mediante una query propia implementada en el repository
		return usuRepo.findUsuariosClientes();
	}

}
