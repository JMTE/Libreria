package com.ite.jmte.modelo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ite.jmte.modelo.beans.Libro;
import com.ite.jmte.modelo.beans.Perfile;
import com.ite.jmte.modelo.beans.Usuario;
import com.ite.jmte.modelo.dao.IntLibroDao;
import com.ite.jmte.modelo.dao.IntUsuarioDao;

@Controller
public class HomeController {
	
	
		@Autowired
		IntLibroDao libDao;
		@Autowired
		IntUsuarioDao usuDao;
		
		//Creamos un objeto de PasswordEncoder definido en la clase de DatSecurity para encriptar las contraseñas cuando registramos un usuario
		@Autowired
		private PasswordEncoder pwenco;
		
		//Creamos una listaCarrito que la introduciremos en sesion.
		List<Libro>listaCarrito=new ArrayList<Libro>();
		
		//Creamos una lista para el numero que va a aparecer al lado del ver Carrito en el index
		List<String>numeroCarrito=new ArrayList<String>();
	
		
		//Esto lo utilizamos para encriptar las contraseñas que teniamos sin encriptar en la BBDD
		@GetMapping("/pwd")
		@ResponseBody
		public String generarEncriptado() {
		String password = "perico";
		String encriptado = pwenco.encode(password);
		return encriptado;
		}
	
		
		
		
		@GetMapping("/index")
		
		
		public String listaLibrosNovedades(Authentication aut, Model model, HttpSession misesion, HttpSession listaSesion, HttpSession sesionNumeroCarrito) {
			
			//Introducimos el usuario autentificado en un objeto de la clase usuario que lo buscamos con nuestro metodo de buscar usuario por username
			Usuario usuario = usuDao.findUsuarioByUsername(aut.getName());
			
			//Creamos un string rol para definir en que direccion entraremos segun el usuario que introduzcamos (ROL_CLIENTE o ROL_ADMON)
			String rol =null;
			
				//Introducimos en sesion el usuario , la listaCarrito y  la lista que utilizamos para representar el numero al lado de ver Carrito.
				misesion.setAttribute("usuario", usuario);
				listaSesion.setAttribute("listaCarrito", listaCarrito);
				sesionNumeroCarrito.setAttribute("numeroCarrito", numeroCarrito);
				
			//Buscamos el rol que esta autentificado y lo guardamos en nuestra variable rol
			for (GrantedAuthority ele: aut.getAuthorities()) {
				
				rol=ele.getAuthority();
			}
				
			
			//model.addAttribute("mensaje", aut.getAuthorities());
			
			
			//Dependiendo del usuario y su rol, entraremos en una dirección u otra.
			if (rol.equalsIgnoreCase("ROL_CLIENTE")) {
				
				return "redirect:cliente/";	
				
			}else if (rol.equalsIgnoreCase("ROL_ADMON")) {
				
				return "redirect:admon/";
				
			}else {
				//y vamos a index con la lista de libros que son novedad
				model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
				return "index";
			}
			
		
				
	}
		
		
		//Aqui personalizamos nuestra forma de cerrar sesion para volver a la pantalla de inicio de registrar o loguear despues de hacer logout
		@GetMapping("/salir")
		
		public String logout(HttpServletRequest request){
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request,null,null);
		//Borramos los items de la lista que contiene los libros del carrito y la lista que tenemos para representar el numero al lado del ver Carrito
		listaCarrito.clear();
		numeroCarrito.clear();
		return "redirect:/";
		}
		
		
		//Si pulsamos en registro nos lleva a un formulario para registrarnos
		@GetMapping("/registro")
		public String registrar(Model model) {
			
			
			return "registroUsuario";
			 
			
		}
		
		//Mediante el postmapping traemos los datos del usuario que queremos registrar
		@PostMapping("/registro")
		public String proregistrar(Model model, Usuario usuario) {
			
			//Ponemos el enabled del usuario a 1
			usuario.setEnabled(1);
			
			//Ahora vamos a indicar los perfiles que tiene el nuevo usuario, que como es un registro publico solo podrá tener el perfil cliente
			List<Perfile> listaPerfilesUsuario= new ArrayList<Perfile>();
			
			Perfile perfilUsuario=new Perfile();
			//Le asignamos perfil cliente
			perfilUsuario.setIdPerfil(2);
			
			listaPerfilesUsuario.add(perfilUsuario);
			
			//Le introducimos al usuario la lista de perfiles que tiene (solo cliente en esta lista)
			usuario.setPerfiles(listaPerfilesUsuario);
			
			
			/*La contraseña encriptada, en tiempo de registrar al usuario, se almacena 
			  también encriptada en la base de datos. */
			usuario.setPassword(pwenco.encode(usuario.getPassword()));
			
			
			//Añadimos la fecha de alta 
			usuario.setFechaAlta(new Date());
			
			
			if(usuDao.altaUsuario(usuario)==0) {
				model.addAttribute("mensaje", "Este username ya existe");
				return "registroUsuario";
			}else {
				//Añadimos el usuario a la BBDD
				usuDao.altaUsuario(usuario);
				
			
				
				return "index";
			}
			
			
			
			 
			
		}
		
		
		
		/*Como buscar por tema y por titulo es algo comun en los dos perfiles, los defino aqui en vez de en su controllador correspondiente y 
		 * añado estas url para que solo sean accesibles por estos dos perfiles en la clase DatSecurity*/
		@PostMapping("/tema")
		public String BuscarPorTema(Model model, @RequestParam ("descTema") String descTema) {
			
		
			model.addAttribute("listaLibros", libDao.listaLibrosPorTema(descTema));
			model.addAttribute("volver", "Volver a inicio");
			model.addAttribute("numeroCarrito", numeroCarrito.size());
			return "index";
		}
		
		@PostMapping("/buscar")
		public String BuscarPorTitulo(Model model, @RequestParam ("busqueda") String busqueda) {
			
			
			model.addAttribute("listaLibros", libDao.listaLibrosPorCadena(busqueda));
			model.addAttribute("volver", "Volver a inicio");
			model.addAttribute("numeroCarrito", numeroCarrito.size());
			return "index";
		}
		
		
		
}		



	



