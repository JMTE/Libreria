package com.ite.jmte.modelo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite.jmte.modelo.beans.Usuario;
import com.ite.jmte.modelo.dao.IntLibroDao;
import com.ite.jmte.modelo.dao.IntUsuarioDao;

@RequestMapping("admon")
@Controller
public class AdministradorController {

	//Me he quedado en que iba a empezar a introducir los casos de uso tema
	
	@Autowired
	private IntUsuarioDao usuDao;
	
	@Autowired 
	private IntLibroDao libDao;
	
	@Autowired
	private HttpSession misesion;
	
	@GetMapping("/")
	public String inicio (Model model ) {
		
		Usuario usuario=(Usuario) misesion.getAttribute("usuario");
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}
}

