package com.ite.jmte.modelo.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.ite.jmte.modelo.beans.Usuario;

public interface IntUsuariosRepo extends JpaRepository<Usuario, String>{

}
