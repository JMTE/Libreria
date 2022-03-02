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

//Definimos que los clientes siempre trabajen bajo la url cliente
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
		
		//Recuperamos el usuario que tenemos en la sesion
		Usuario usuario=(Usuario) misesion.getAttribute("usuario");
		
		//Presentamos la lista libros que son novedad
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}
	
	
	
	
	//Mediante esta direccion vamos a ver el detalle de un libro pasandole un isbn
	@GetMapping("/verDetalle/{isbn}")
	public String verDetalle (Model model , @PathVariable long isbn) {
		
		Libro libro= new Libro();
		libro=libDao.findLibroById(isbn);
		//Le añadimos la imagen del libro para presentarlo
		libro.setImagen("/imagenLibro.jpg");
		model.addAttribute("libro", libro);
		
		return "verDetalle";
	}
	
	
	//Aqui vamos a hacer un peticion para añadir un libro al carrito pasando un isbn del libro
	@GetMapping("/addCarrito/{isbn}")
	
	public String altaCarrito (Model model, @PathVariable long isbn) {
		
		//Rescatamos la lista que tenemos en sesion 
		List<Libro> lista=(List<Libro>) listaSesion.getAttribute("listaCarrito");
		
		
		//Añadimos el libro al carrito pasando el libro y la lista a nuestro metodo creado, 
		//la lista la pasamos para comprobar si ya existe el libro en el carrito
		libDao.addLibroCarrito(libDao.findLibroById(isbn), lista);
		
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
		
	}
	
	//Mediante esta peticion vamos a ver los articulos que tenemos en el carrito
	@GetMapping ("/verCarrito")
	
	public String verCarrito (Model model) {
		//Recuperamos la lista que tenemos en sesion con nuestros libros
		List<Libro> lista=(List<Libro>) listaSesion.getAttribute("listaCarrito");
	
		//Creamos una variable bigDecimal solo para presentar en nuestra pantalla el precio total del carrito
		BigDecimal precioTotal = new BigDecimal(0);
		
		//Calculamos el precio total de los libros que tenemos en el carrito
		for(Libro ele: lista) {
			precioTotal= precioTotal.add(ele.getPrecioUnitario());
			
		}
		
		
		model.addAttribute("listaCarrito", lista);
		model.addAttribute("precioTotal", precioTotal);
		return "verCarrito";
	}
	
	
	//Mediante esta peticion vamos a eliminar un libro del carrito
	@GetMapping("/eliminar/{isbn}")
	
	public String eliminarLibroCarrito (Model model, @PathVariable long isbn) {
		
		//Recuperamos la lista de libros que tenemos en sesion para el carrito
		List<Libro> lista=(List<Libro>) listaSesion.getAttribute("listaCarrito");
		
		//borramos el libro de la lista
		lista.remove(libDao.findLibroById(isbn));
		
		//Calculamos el nuevo precio total que tienen los libros del carrito
		BigDecimal precioTotal = new BigDecimal(0);
		
		for(Libro ele: lista) {
			
			precioTotal= precioTotal.add(ele.getPrecioUnitario());
			
		}
		
		//Volvemos a presentar los datos actualizados en nuestro jsp verCarrito
		model.addAttribute("listaCarrito", lista);
		model.addAttribute("precioTotal", precioTotal);
		return "verCarrito";
		
	}
	
	
	//Mediante esta peticion vamos a comprar los libros que tenemos en el carrito, generando un pedido y las lineas de pedidos 
	@GetMapping ("/comprar")
	public String comprarCarrito (Model model ) {
		
		//Recuperamos el usuario y la lista de libros del carrito de sesion
		Usuario usuario=(Usuario) misesion.getAttribute("usuario");
		List<Libro> lista=(List<Libro>) listaSesion.getAttribute("listaCarrito");
		
		//Creamos un nuevo pedido
		Pedido pedido=new Pedido();
		//Establecemos la fecha del pedido
		pedido.setFechaAlta(new Date());
		//Establecemos el estado del pedido
		pedido.setEstado("Terminado");
		//Establecemos el usuario del pedido (recuperado de la sesion)
		pedido.setUsuario(usuario);
		//Establecemos la direccion de entrega como la direccion que tiene el usuario
		pedido.setDireccionEntrega(usuario.getDireccion());
		
		//Creamos una nueva lista de lineas de pedido que es de tipo arrayList
		List<LineasPedido> lineas = new ArrayList<LineasPedido>();
		
		//Recorremos la lista de libros que tenemos en nuestro carrito
		for(Libro ele :lista) {
			//Creamos una nueva linea de pedido por libro
			LineasPedido lp=new LineasPedido();
			//Le establecemos el pedido que hemos creado
			lp.setPedido(pedido);
			//Establecemos el libro en el que se encuentra el indice
			lp.setLibro(ele);
			//Establecemos la cantidad que por convenio es 1
			lp.setCantidad(1);
			//establecemos el precio de venta que al ser cantidad 1, se corresponde con el precio del libro
			lp.setPrecioVenta(ele.getPrecioUnitario());
			//Establecemos la fecha de alta de esta linea
			lp.setFechaAlta(new Date());
			//Añadimos la linea de pedido a la lista de lineas de pedido
			lineas.add(lp);
		}
		
		//Añadimos a pedido la lista de lineas de pedido
		pedido.setLineasPedidos(lineas);
		//Damos de alta el pedido 
		pedDao.altaPedido(pedido);
		
		//Limpiamos la lista de libros de nuestro carrito
		lista.clear();
		
		//Volvemos a la pagina principal
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}
	
	
	//Mediante esta peticion vamos a devolver los datos del cliente para que el usuario pueda ver si son correctos
	@GetMapping("/verDatos")
	
	public String verDatos(Model model) {
		
		Usuario usuario=(Usuario) misesion.getAttribute("usuario");
		
		model.addAttribute("usuario", usuario);
		return "verDatos";
		
	}
}
