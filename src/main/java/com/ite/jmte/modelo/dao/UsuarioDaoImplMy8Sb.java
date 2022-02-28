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
		// TODO Auto-generated method stub
		return usuRepo.findAll();
	}

	@Override
	public Usuario findUsuarioByUsername(String username) {
		// TODO Auto-generated method stub
		
		return usuRepo.findById(username).orElse(null);
	}

	@Override
	public int altaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Usuario> findUsuariosClientes() {
		// TODO Auto-generated method stub
		return null;
	}

}
