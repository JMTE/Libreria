package com.ite.jmte.modelo.dao;

import java.util.List;

import com.ite.jmte.modelo.beans.Usuario;

public interface IntUsuarioDao {

	List<Usuario> findAll();
	
	Usuario findUsuarioByUsername(String username);
	
	int altaUsuario(Usuario usuario);
	
	List<Usuario>findUsuariosClientes();
	
	
}
