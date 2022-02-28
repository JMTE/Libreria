package com.ite.jmte.modelo.controller;

import java.math.BigDecimal;
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
import com.ite.jmte.modelo.beans.LineasPedido;
import com.ite.jmte.modelo.beans.Pedido;
import com.ite.jmte.modelo.beans.Usuario;
import com.ite.jmte.modelo.dao.IntLibroDao;
import com.ite.jmte.modelo.dao.IntPedidoDao;
import com.ite.jmte.modelo.dao.IntUsuarioDao;
@RequestMapping("cliente")
@Controller
public class ClienteController {

	
	@Autowired
	private IntUsuarioDao usuDao;
	
	@Autowired 
	private IntLibroDao libDao;
	
	@Autowired
	private IntPedidoDao pedDao;
	
	@Autowired
	private HttpSession misesion;
	
	@Autowired
	private HttpSession listaSesion;
	
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
	
	@GetMapping("/addCarrito/{isbn}")
	
	public String altaCarrito (Model model, @PathVariable long isbn) {
		
		List<Libro> lista=(List<Libro>) listaSesion.getAttribute("listaCarrito");
		
		
		
		libDao.addLibroCarrito(libDao.findLibroById(isbn), lista);
		System.out.println(lista);
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
		
	}
	
	@GetMapping ("/verCarrito")
	
	public String verCarrito (Model model) {
		List<Libro> lista=(List<Libro>) listaSesion.getAttribute("listaCarrito");
		System.out.println(lista);
		BigDecimal precioTotal = new BigDecimal(0);
		
		for(Libro ele: lista) {
			
			
			
			precioTotal= precioTotal.add(ele.getPrecioUnitario());
			
		}
		
		
		model.addAttribute("listaCarrito", lista);
		model.addAttribute("precioTotal", precioTotal);
		return "verCarrito";
	}
	
	@GetMapping("/eliminar/{isbn}")
	
	public String eliminarLibroCarrito (Model model, @PathVariable long isbn) {
		List<Libro> lista=(List<Libro>) listaSesion.getAttribute("listaCarrito");
		
		lista.remove(libDao.findLibroById(isbn));
		BigDecimal precioTotal = new BigDecimal(0);
		
		for(Libro ele: lista) {
			
			
			
			precioTotal= precioTotal.add(ele.getPrecioUnitario());
			
		}
		
		model.addAttribute("listaCarrito", lista);
		model.addAttribute("precioTotal", precioTotal);
		return "verCarrito";
		
	}
	
	@GetMapping ("/comprar")
	public String comprarCarrito (Model model ) {
		Usuario usuario=(Usuario) misesion.getAttribute("usuario");
		List<Libro> lista=(List<Libro>) listaSesion.getAttribute("listaCarrito");
		
		System.out.println(lista);
		Pedido pedido=new Pedido();
		pedido.setFechaAlta(new Date());
		pedido.setEstado("Terminado");
		pedido.setUsuario(usuario);
		pedido.setDireccionEntrega(usuario.getDireccion());
		
		
		List<LineasPedido> lineas = new ArrayList<LineasPedido>();
		
		for(Libro ele :lista) {
			LineasPedido lp=new LineasPedido();
			lp.setPedido(pedido);
			lp.setLibro(ele);
			lp.setCantidad(1);
			lp.setPrecioVenta(ele.getPrecioUnitario());
			lp.setFechaAlta(new Date());
			lineas.add(lp);
		}
		
		pedido.setLineasPedidos(lineas);
		pedDao.altaPedido(pedido);
		
		lista.clear();
		
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}
}
