package com.ite.jmte.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ite.jmte.modelo.beans.Tema;

public interface IntTemasRepo extends JpaRepository<Tema, Integer> {

}
