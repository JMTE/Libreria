<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito Compra</title>
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
    <a class="navbar-brand  " href="/index"><i class="bi bi-bank"> Libreria</i></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
      
        
       
       
    
        
        
      <sec:authorize access="isAuthenticated()">
        <li class="nav-item" >
          <a class="nav-link" href="/cliente/"><i class="bi bi-box-arrow-right"> Volver</i></a>
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

<div class="cuerpo bg-success p-2 text-white bg-opacity-25">

<h4>CARRITO DE LA COMPRA</h4>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Titulo</th>
      <th scope="col">Autor</th>
      <th scope="col">Precio</th>
      
      
    </tr>
  </thead>
  
  <tbody>
  <c:forEach var="ele" items="${listaCarrito}">
  <tr>
      <th scope="row"><i class="bi bi-forward-fill"> ${ele.titulo }</i></th>
     <td>${ele.autor } <i class="bi bi-currency-euro"></i></td>
      <td>${ele.precioUnitario } <i class="bi bi-currency-euro"></i></td>
      
      <td><a class="btn btn-success " href="/cliente/eliminar/${ele.isbn }">Eliminar</a></td>
      
     
	  
	 
	   
	
         
    </tr>
  </c:forEach>
   <tr><td></td><td></td><td>Total: ${precioTotal} <i class="bi bi-currency-euro"></i></td></tr>
   <tr>
   
   <td> <a class= "btn btn-success" href="/cliente/comprar">Comprar <i class="bi bi-cart-check"></i></a></td>
   <td><h2>${mensaje }</h2></td>
   </tr>
  
   
  </tbody>
</table>
</div>

</div>
</body>
</html>