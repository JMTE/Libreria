package com.ite.jmte.modelo.controller;

import java.math.BigDecimal;
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
import com.ite.jmte.modelo.beans.LineasPedido;
import com.ite.jmte.modelo.beans.Pedido;
import com.ite.jmte.modelo.beans.Perfile;
import com.ite.jmte.modelo.beans.Tema;
import com.ite.jmte.modelo.beans.Usuario;
import com.ite.jmte.modelo.dao.IntLibroDao;
import com.ite.jmte.modelo.dao.IntPedidoDao;
import com.ite.jmte.modelo.dao.IntPerfilesDao;
import com.ite.jmte.modelo.dao.IntTemaDao;
import com.ite.jmte.modelo.dao.IntUsuarioDao;

//Definimos que los clientes siempre trabajen bajo la url cliente
@RequestMapping("admon")
@Controller
public class AdministradorController {

	
	
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
	
	
	//Esta es la pantalla de inicio
	@GetMapping("/")
	public String inicio (Model model ) {
		
		Usuario usuario=(Usuario) misesion.getAttribute("usuario");
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}
	
	
	//Mediante esta peticion vamos a llamar a un jsp para introducir los datos de un tema y darlo de alta
	@GetMapping("/altaTema")
	
	public String altaTema (Model model) {
		
		return "altaTema";
	}
	
	//Con el postmapping nos traemos los datos del formulario para dar de alta el tema
	@PostMapping("/altaTema")
	public String altaTema(Model model, Tema tema) {
		
		
		//mediante nuestro metodo implementado en el dao, damos de alta el tema
		libDao.altaTema(tema);
		
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());

		return "index";
	}
	
	
	//Mediante esta peticion vamos a presentar un jsp con un formulario para poder dar de alta un libro
    @GetMapping("/altaLibro")
	
	public String altaLibro (Model model) {
    	model.addAttribute("listaTemas", temDao.findAll());
		return "altaLibro";
	}
	
    //Con el postmapping traemos los datos del nuevo libro y el tema al que se corresponde el libro
	@PostMapping("/altaLibro")
	public String altaLibro(Model model, Libro libro,@RequestParam int idTema) {
		
		
		//Le establecemos el tema al libro
		libro.setTema(temDao.findTemaById(idTema));
		
		//Comprobamos al dar de alta el libro que su isbn unico no existe, si existe sacamos un mensaje y volvemos al formulario de alta
		if (libDao.altaLibro(libro)==0) {
			model.addAttribute("listaTemas", temDao.findAll());
			model.addAttribute("mensaje", "Ese isbn del libro ya existe, es un valor único");
			return "altaLibro";
		}else {
			//Damos de alta el libro con el metodo que hemos creado en el dao
			libDao.altaLibro(libro);
			
			model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
			
			return "index";
		}
		
	}
	

	//Mediante postmapping vamos a llevar la fecha que escogemos en el formulario para listar los pedidos realizados en esa fecha
	@PostMapping("/verPedidos")
	
	public String verPedidos(Model model, String fechaAlta) throws ParseException {
		
		
		
		//Formateamos la fecha que nos devuelve como String para poder introducirla  como Date
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date dataFormateada = formato.parse(fechaAlta);
				//Creamos una lista de pedidos nueva 
				List<Pedido> listaPedidos=new ArrayList<Pedido>();
				//Establecemos que esta lista de pedidos la calculamos con nuestro metodo implementado en el dao, mediante el cual
				//introducimomos en una lista todos los pedidos realizados en una fecha en concreto.
				listaPedidos=pedDao.findPedidosByFechaAlta(dataFormateada);
				
				model.addAttribute("listaPedidos", listaPedidos);
				
		
		return "verPedidos";
	}
	
	
	//Mediante esta peticion vamos a ver el detalle de un pedido en la lista de pedidos
	@GetMapping("/detallePedido/{idPedido}")
	
	public String detallePedido(Model model, @PathVariable int idPedido) {
		
		//Buscamos el pedido 
		Pedido pedido = pedDao.findPedidoById(idPedido);
		
		//Presentamos el pedido en un nuevo jsp
		model.addAttribute("pedido", pedido);
		
		return "detallePedido";
	}
	
	//Mediante esta peticion vamos a ver la lista de usuarios totales
	@GetMapping("/verUsuarios")
	
	public String verUsuarios(Model model ) {
		
		List<Usuario> listaUsuarios=new ArrayList<Usuario>();
		
		listaUsuarios=usuDao.findAll();
		
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "verUsuarios";
	}
	
	//mediante esta peticion vamos a ver la lista de perfiles existentes
	@GetMapping("/verPerfiles")
	
	public String verPerfiles(Model model ) {
		
		List<Perfile> listaPerfiles=new ArrayList<Perfile>();
		
		listaPerfiles=perDao.findAll();
		
		model.addAttribute("listaPerfiles", listaPerfiles);
		return "verPerfiles";
	}

	//Mediante esta peticion vamos a ver la lista de usuarios que solamente son clientes
	@GetMapping("/verClientes")

	public String verClientes(Model model ) {
	
	List<Usuario> listaClientes=new ArrayList<Usuario>();
	
	listaClientes=usuDao.findUsuariosClientes();
	
	
	model.addAttribute("listaClientes", listaClientes);
	return "verClientes";
}

	//Mediante esta peticion vamos a ver la lista de temas de libros existentes
	@GetMapping("/verTemas")

	public String verTemas(Model model ) {
	
	List<Tema> listaTemas=new ArrayList<Tema>();
	
	listaTemas=temDao.findAll();
	
	model.addAttribute("listaTemas", listaTemas);
	return "verTemas";
}
	
	//Mediante esta peticion vamos a ver el detalle de un libro en concreto
	@GetMapping("verDetalle/{isbn}")
	
	public String verDetalle (Model model , @PathVariable long isbn) {
		
		Libro libro= new Libro();
		libro=libDao.findLibroById(isbn);
		libro.setImagen("/imagenLibro.jpg");
		model.addAttribute("libro", libro);
		
		return "verDetalle";
	}
	
	
	//Mediante esta peticion vamos a sacar un nuevo jsp para poder editar datos de un libro
	@GetMapping("/modificarLibro/{isbn}")
	public String modificarLibro(Model model, @PathVariable long isbn) {
		
		
		model.addAttribute("listaTemas", temDao.findAll());
		model.addAttribute("libro", libDao.findLibroById(isbn));
		
		
		return "modificarLibro";
		
	}
	
	
	
	//Traemos los nuevos datos del libro y lo modificamos
	@PostMapping("/modificarLibro")
	
	public String modificarLibro(Model model, Libro libro, @RequestParam int idTema) {
		
		libro.setTema(temDao.findTemaById(idTema));
		libDao.modificarLibro(libro);
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}
	
	//Con esta peticion vamos a buscar todos los libros que ha comprado un cliente y mostrarlos en un jsp
	@GetMapping("/verDetalleCliente/{username}")
	
	public String verDetalleCliente(Model model, @PathVariable String username) {
		
		//Buscamos todos los pedidos de este usuario mediante un metodo que hemos creado con una query y los introducimos en una lista de pedidos
		List<Pedido> listaPedidosUsuario=pedDao.findPedidosByUsername(username);
		//Creamos una lista de lineas de pedido para introducir todos nuestros libros de todos los pedidos
		List<LineasPedido> lineasPedidoUsuario=new ArrayList<LineasPedido>();
		//Recorremos nuestra lista de pedidos y nuestras listas de lineas de pedido dentro de un pedido e introducimos cada linea de pedido en nuestra
		//lista creada anteriormente
		for(Pedido ele: listaPedidosUsuario) {
		
			for(LineasPedido ele2 : ele.getLineasPedidos()) {
				
				lineasPedidoUsuario.add(ele2);
			}
			
		}
		
		//Recorremos nuestra nueva lista de lineas de pedido y sumamos cada precio unitario de cada libro
		BigDecimal costeLibrosCliente= new BigDecimal(0);
		for (LineasPedido ele : lineasPedidoUsuario) {
			
			costeLibrosCliente= costeLibrosCliente.add(ele.getPrecioVenta());
		}
		
		//Presentamos los datos en el jsp
		model.addAttribute("lineasPedidoUsuario", lineasPedidoUsuario);
		model.addAttribute("costeLibroCliente", costeLibrosCliente);
		model.addAttribute("username", username);
		return "verDetalleCliente";
	}
	
	//mediante esta peticion vamos a borrar un libro (No he sido capaz porque habria que borrar lineas de pedido y pedido, son claves foraneas)
	@GetMapping("borrarLibro/{isbn}")
	
	public String borrarLibro(Model model, @PathVariable int isbn) {
		
		libDao.eliminarLibro(libDao.findLibroById(isbn));
		
		model.addAttribute("listaLibros", libDao.listaLibrosNovedades());
		return "index";
	}


}

