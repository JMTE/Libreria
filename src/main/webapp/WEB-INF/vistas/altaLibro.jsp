<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta Libro</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/styles.css">
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
          <a class="nav-link" href="/admon/"><i class="bi bi-box-arrow-right"> Volver</i></a>
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
</div>
<div class="container" id="contenedorAlta">

<h1> Se va a proceder a dar de alta un nuevo libro</h1>
<form action="/admon/altaLibro" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Isbn</label>
    <input type="number" required class="form-control" id="exampleInputEmail1"  placeholder="isbn" name="isbn">
   
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Titulo</label>
    <input type="text" required class="form-control" id="exampleInputPassword1" placeholder="Titulo" name="titulo">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Autor</label>
    <input type="text"  required class="form-control" id="exampleInputPassword1" placeholder="Autor" name="autor">
  </div>
  <div class="form-group">
    <label >Novedad</label> 
    <select  class="form-select" aria-label="Default select example" name="novedad" required>
    <option value="s" selected>Escoge si el libro es novedad (si por defecto)</option>
    <option value="s" >Si</option>
    <option value="n" >No</option>
    
    </select>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Paginas</label>
    <input  required type="number" class="form-control" id="exampleInputPassword1" placeholder="Paginas" name="paginas">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Precio Unitario</label>
    <input required type="number"  step="0.01" min="1" class="form-control" id="exampleInputPassword1" placeholder="Precio Unitario" name="precioUnitario">
  </div>
  
  
  <div class="form-group">
    <label >Tema</label>
    <select  class="form-select" aria-label="Default select example"  name="idTema" required>
    <option value="1" selected>Escoge el tema del libro (Literatura por defecto)</option>
    <c:forEach var="ele" items="${listaTemas }">
    <option value="${ele.idTema }" >${ele.descTema }</option>
    </c:forEach>
    </select>
  </div>
  
  
  
  
  <br>
  
  <h2>${mensaje }</h2>
  <button type="submit" class="btn btn-success">Alta libro</button>
</form>
</div>

</body>
</html>