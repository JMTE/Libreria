package com.ite.jmte.modelo.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import com.ite.jmte.modelo.beans.Usuario;

public interface IntUsuariosRepo extends JpaRepository<Usuario, String>{
	
	//Buscar usuarios que solo sean clientes con un inner join
	@Query("select DISTINCT u from Usuario u inner join u.perfiles p where p.idPerfil=2")
	List<Usuario>findUsuariosClientes();
	

}
