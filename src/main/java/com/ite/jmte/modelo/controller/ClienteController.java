package com.ite.jmte.modelo.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ite.jmte.modelo.beans.Usuario;
import com.ite.jmte.modelo.dao.IntLibroDao;
import com.ite.jmte.modelo.dao.IntUsuarioDao;
@RequestMapping("cliente")
@Controller
public class ClienteController {

	
	@Autowired
	private IntUsuarioDao usuDao;
	
	@Autowired 
	private IntLibroDao libDao;
	
	@Autowired
	private HttpSession misesion;
	
	@GetMapping("/")
	public String inicio (Model model) {
		
		Usuario usuario=(Usuario) misesion.getAttribute("usuario");
		
		
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}
	
	@PostMapping("/tema")
	public String BuscarPorTema(Model model, @RequestParam ("descTema") String descTema) {
		
		System.out.println(descTema);
		model.addAttribute("listaLibros", libDao.listaLibrosPorTema(descTema));
		
		
		return "index";
	}
	
	@PostMapping("/buscar")
	public String BuscarPorTitulo(Model model, @RequestParam ("busqueda") String busqueda) {
		
		
		model.addAttribute("listaLibros", libDao.listaLibrosPorCadena(busqueda));
		
		
		return "index";
	}
	
	@GetMapping("/verDetalle/{isbn}")
	public String verDetalle (Model model , @PathVariable long isbn) {
		
		System.out.println(isbn);
		model.addAttribute("libro", libDao.findLibroById(isbn));
		
		return "verDetalle";
	}
}
