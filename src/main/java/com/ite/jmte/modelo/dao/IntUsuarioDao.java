package com.ite.jmte.modelo.dao;

import java.util.List;

import com.ite.jmte.modelo.beans.Usuario;

//Aqui definimos todos los metodos que vamos a utilizar para trabajar con Usuarios
public interface IntUsuarioDao {

	//Para listar todos los usuarios existentes.
	List<Usuario> findAll();
	
	//Para buscar un usuario por su username
	Usuario findUsuarioByUsername(String username);
	
	//Para dar de alta un usuario
	int altaUsuario(Usuario usuario);
	
	//Para encontrar usuarios que solo sean clientes mediante query propia en el repositorio
	List<Usuario>findUsuariosClientes();
	
	
}
