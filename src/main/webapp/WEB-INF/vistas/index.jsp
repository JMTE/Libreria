<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Principal</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<header>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand  " href="#"><i class="bi bi-shop"> Libreria</i></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
       <sec:authorize access="hasAuthority('ROL_ADMON')">
        <li class="nav-item">
          <a class="nav-link active " aria-current="page" href="/admon/verUsuarios"><i class="bi bi-people-fill"> Usuarios</i></a>
        </li>
        </sec:authorize>
         <sec:authorize access="hasAuthority('ROL_ADMON')">
        <li class="nav-item">
          <a class="nav-link" href="/admon/verPerfiles"><i class="bi bi-list-check"> Perfiles</i></a>
        </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('ROL_ADMON')">
        <li class="nav-item">
          <a class="nav-link" href="/admon/verClientes"><i class="bi bi-bag"> Clientes</i></a>
        </li>
        </sec:authorize>
         <sec:authorize access="hasAuthority('ROL_ADMON')">
         <li class="nav-item">
          <a class="nav-link" href="/admon/verTemas"><i class="bi bi-book"> Temas</i></a>
        </li>
        </sec:authorize>
        <sec:authorize access="hasAnyAuthority('ROL_CLIENTE','ROL_ADMON')">
        <form class="d-flex" action="/tema" method="post">
      <input class="form-control me-2" type="search" placeholder="Buscar por tema" aria-label="Search" name="descTema">
      <button class="btn btn-outline-success" type="submit">Search</button>
      	</form>
      	 <form class="d-flex" action="/buscar" method="post">
      <input class="form-control me-2" type="search" placeholder="Buscar por titulo" aria-label="Search" name="busqueda">
      <button class="btn btn-outline-success" type="submit">Search</button>
      	</form>
        </sec:authorize>
         <sec:authorize access="hasAuthority('ROL_CLIENTE')">
         <li class="nav-item" >
          <a class="nav-link" href="/cliente/verDatos"><i class="bi bi-person-fill">Mis datos </i></a>
        </li>
        </sec:authorize>
         <sec:authorize access="hasAuthority('ROL_CLIENTE')">
        <li class="nav-item" >
          <a class="nav-link" href="/cliente/verCarrito"><i class="bi bi-cart"> Ver Carrito </i></a>
        </li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
        <li class="nav-item" >
          <a class="nav-link" href="/index"><i class="bi bi-box-arrow-in-right"> Login</i></a>
        </li>
        </sec:authorize>
         <sec:authorize access="!isAuthenticated()">
        <li class="nav-item" >
          <a class="nav-link" href="/registro"><i class="bi bi-person-plus "> Registro</i></a>
        </li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
        <li class="nav-item" >
          <a class="nav-link" href="/salir"><i class="bi bi-box-arrow-right"> Cerrar Sesión</i></a>
        </li>
        </sec:authorize>
        
      </ul>
    </div>
  </div>
</nav>
</header>
<sec:authorize access="hasAnyAuthority('ROL_CLIENTE','ROL_ADMON')">
<div class="cuerpo bg-success p-2 text-white bg-opacity-25">
<div class="imagenhome">
<br>
<h4> <i class="bi bi-person-check"> Usuario: ${usuario.username }</i></h4>
<br>
<sec:authorize access="hasAuthority('ROL_ADMON')">
<a class="btn btn-success " href="/admon/altaLibro"><i class="bi bi-book"> Nuevo Libro</i></a>
<a class="btn btn-success " href="/admon/altaTema"><i class="bi bi-journal-plus"> Nuevo Tema</i></a>
<br><br>
 <form class="d-flex" action="/admon/verPedidos" method="post">
      <input class="form-control" type="date"   name="fechaAlta">
      <button class="btn btn-outline-success" type="submit">Ver Pedidos</button>
 </form>
</sec:authorize>

<br>

<table class="table">
  <thead>
    <tr>
      <th scope="col">Titulo</th>
      <th scope="col">Autor</th>
      <th scope="col">Precio</th>
      
      
    </tr>
  </thead>
  
  <tbody>
  
  <c:forEach var="ele" items="${listaLibros}">
  <tr>
   
      <th scope="row"><i class="bi bi-forward-fill"> ${ele.titulo }</i></th>
     <td>${ele.autor } </i></td>
      <td>${ele.precioUnitario } <i class="bi bi-currency-euro"></i></td>
     
	  
	  <sec:authorize var="loggedIn" access="hasAuthority('ROL_ADMON')" />
	   <sec:authorize var="loggedIn2" access="hasAuthority('ROL_CLIENTE')" />
	   
	<c:choose>
    <c:when test="${loggedIn}">
        <td><a class="btn btn-success " href="/admon/modificarLibro/${ele.isbn }">Editar <i class="bi bi-pencil"></i></a></td>
		<td><a class="btn btn-success " href="#">Eliminar <i class="bi bi-trash"></i></a></td>
		<td><a class="btn btn-success " href="/admon/verDetalle/${ele.isbn }">Ver Detalle <i class="bi bi-search"></i></a></td>
    </c:when>
     <c:when test="${loggedIn2}">
       <td><a class="btn btn-success " href="/cliente/addCarrito/${ele.isbn }"> Añadir Carrito  <i class="bi bi-cart-plus"></i> </a></td>
       <td><a class="btn btn-success " href="/cliente/verDetalle/${ele.isbn}">Ver Detalle <i class="bi bi-search"></i></a></td>
   	</c:when>
    <c:otherwise>
       
    </c:otherwise>
	</c:choose>
         
    </tr>
  </c:forEach>
   
  
   
  </tbody>
</table>

</div>
</div>
</sec:authorize>
</div>


</body>
</html>