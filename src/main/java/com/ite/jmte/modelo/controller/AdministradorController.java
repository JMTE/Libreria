package com.ite.jmte.modelo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ite.jmte.modelo.beans.Libro;
import com.ite.jmte.modelo.beans.Pedido;
import com.ite.jmte.modelo.beans.Perfile;
import com.ite.jmte.modelo.beans.Tema;
import com.ite.jmte.modelo.beans.Usuario;
import com.ite.jmte.modelo.dao.IntLibroDao;
import com.ite.jmte.modelo.dao.IntPedidoDao;
import com.ite.jmte.modelo.dao.IntPerfilesDao;
import com.ite.jmte.modelo.dao.IntTemaDao;
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
	
	@Autowired
	private IntTemaDao temDao;
	
	@Autowired
	private IntPedidoDao pedDao;
	
	@Autowired
	private IntPerfilesDao perDao;
	
	@GetMapping("/")
	public String inicio (Model model ) {
		
		Usuario usuario=(Usuario) misesion.getAttribute("usuario");
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}
	
	@GetMapping("/altaTema")
	
	public String altaTema (Model model) {
		
		return "altaTema";
	}
	
	@PostMapping("/altaTema")
	public String altaTema(Model model, Tema tema) {
		
		libDao.altaTema(tema);
		
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());

		return "index";
	}
	
@GetMapping("/altaLibro")
	
	public String altaLibro (Model model) {
		
		return "altaLibro";
	}
	
	@PostMapping("/altaLibro")
	public String altaLibro(Model model, Libro libro,@RequestParam int idTema) {
		
		libro.setTema(temDao.findTemaById(idTema));
		System.out.println(libro);
		libDao.altaLibro(libro);
		
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());

		return "index";
	}
	

	@PostMapping("/verPedidos")
	
	public String verPedidos(Model model, String fechaAlta) throws ParseException {
		
		System.out.println(fechaAlta);
		
		//Formateamos la fecha que nos devuelve como String para poder introducirla en nuestro proyecto como Date
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date dataFormateada = formato.parse(fechaAlta);
				System.out.println(dataFormateada);
				List<Pedido> listaPedidos=new ArrayList<Pedido>();
				listaPedidos=pedDao.findPedidosByFechaAlta(dataFormateada);
				
				model.addAttribute("listaPedidos", listaPedidos);
				
		
		return "verPedidos";
	}
	
	@GetMapping("/detallePedido/{idPedido}")
	
	public String detallePedido(Model model, @PathVariable int idPedido) {
		
		Pedido pedido = pedDao.findPedidoById(idPedido);
		
		model.addAttribute("pedido", pedido);
		
		return "detallePedido";
	}
	
	@GetMapping("/verUsuarios")
	
	public String verUsuarios(Model model ) {
		
		List<Usuario> listaUsuarios=new ArrayList<Usuario>();
		
		listaUsuarios=usuDao.findAll();
		
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "verUsuarios";
	}
	
@GetMapping("/verPerfiles")
	
	public String verPerfiles(Model model ) {
		
		List<Perfile> listaPerfiles=new ArrayList<Perfile>();
		
		listaPerfiles=perDao.findAll();
		
		model.addAttribute("listaPerfiles", listaPerfiles);
		return "verPerfiles";
	}

@GetMapping("/verClientes")

public String verClientes(Model model ) {
	
	List<Usuario> listaClientes=new ArrayList<Usuario>();
	
	listaClientes=usuDao.findUsuariosClientes();
	
	model.addAttribute("listaClientes", listaClientes);
	return "verClientes";
}

@GetMapping("/verTemas")

public String verTemas(Model model ) {
	
	List<Tema> listaTemas=new ArrayList<Tema>();
	
	listaTemas=temDao.findAll();
	
	model.addAttribute("listaTemas", listaTemas);
	return "verTemas";
}
}

