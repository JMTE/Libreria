package com.ite.jmte.modelo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite.jmte.modelo.beans.Libro;
import com.ite.jmte.modelo.beans.Usuario;
import com.ite.jmte.modelo.dao.IntLibroDao;
import com.ite.jmte.modelo.dao.IntUsuarioDao;

@Controller
public class HomeController {
	
	
		@Autowired
		IntLibroDao libDao;
		@Autowired
		IntUsuarioDao usuDao;
		
		
	
		
		
		@GetMapping("/")
		
		
		public String listaLibrosNovedades(Authentication aut, Model model, HttpSession misesion, HttpSession listaSesion) {
			System.out.println("usuario : " + aut.getName());
			Usuario usuario = usuDao.findUsuarioByUsername(aut.getName());
			
			List<Libro>listaCarrito=new ArrayList<Libro>();
			
			String rol =null;
			
				misesion.setAttribute("usuario", usuario);
				listaSesion.setAttribute("listaCarrito", listaCarrito);
				
			
			for (GrantedAuthority ele: aut.getAuthorities()) {
				System.out.println("ROL : " + ele.getAuthority());
				
				rol=ele.getAuthority();
			}
				
			
			model.addAttribute("mensaje", aut.getAuthorities());
			
			if (rol.equalsIgnoreCase("ROL_CLIENTE")) {
				return "redirect:cliente/";	
				
			}else if (rol.equalsIgnoreCase("ROL_ADMON")) {
			
				return "redirect:admon/";
				
			}else {
				model.addAttribute("listaLibrosNovedades",libDao.listaLibrosNovedades() );
				return "inicio";
			}
			
			
				
	}

			 
}		
		
		/*@GetMapping("/registro")
		public String registrar(Model model) {
			
			
			model.addAttribute("mensaje", "registrando");
			
			return "pruebas";
			 
			
		}
		@PostMapping("/registro")
		public String proregistrar(Model model) {
			
			System.out.println("entrando en postmapping");
			model.addAttribute("mensaje", "registrando");
			
			return "pruebas";
			 
			
		}*/
		
		/*@GetMapping("/error")
		public String procesarError() {
			
			 
			System.out.println("procesar error");
			
			return "pruebas";
		}*/
		
		/*@GetMapping("/index")
		public String procesarLogin(Authentication aut, Model model, HttpSession misesion) {
			
			System.out.println("usuario : " + aut.getName());
			Usuario usuario = usuDao.findUsuarioByUsername(aut.getName());
			
			if (misesion.getAttribute("usuario") == null)
				misesion.setAttribute("usuario", usuario);
			
			System.out.println();
			
			for (GrantedAuthority ele: aut.getAuthorities())
				System.out.println("ROL : " + ele.getAuthority());
			
			model.addAttribute("mensaje", aut.getAuthorities());
			
			
			return "redirect:/";
		}*/
		
		

	



