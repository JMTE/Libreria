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
</head>
<body>

<div class="container">

<form action="/admon/altaLibro" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Isbn</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="isbn" name="isbn">
   
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Titulo</label>
    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="abreviatura" name="titulo">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Autor</label>
    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="abreviatura" name="autor">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Novedad</label>
    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="abreviatura" name="novedad">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Paginas</label>
    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="abreviatura" name="paginas">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Precio Unitario</label>
    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="abreviatura" name="precioUnitario">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Tema(ID)</label>
    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="abreviatura" name="idTema">
  </div>
  
  
  
  
  <button type="submit" class="btn btn-primary">Alta libro</button>
</form>
</div>

</body>
</html>