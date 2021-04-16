<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.productos" %>
<%@page import="com.emergentes.modelo.gestor" %>
<%
    if(session.getAttribute("agenda") == null){
        gestor objeto1 = new gestor();
        
        objeto1.insertarP(new productos(1,"Coca cola",8.5,100));
        objeto1.insertarP(new productos(2,"Pepsi",11,152));
        objeto1.insertarP(new productos(3,"Frack",6,152));
        objeto1.insertarP(new productos(4,"Serranitas",2.5,80));
        session.setAttribute("agenda", objeto1);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL - Practica</title>
    </head>
    <body>
        
        <h1 align="center">Registro de Productos</h1>
         <p align="center"><a href="controller?op=nuevo" > Nuevo Producto</a></p>
        <table border="3" CELLPADDING=7 CELLSPACING=7 align="center"  >
            <tr>
                <th width="80" >Id</th>   
                <th width="150" >Producto</th>  
                <th width="80" >Precio</th>
                <th width="80" >Cantidad</th>
                <th width="80" ></th>  
                <th width="80" ></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.agenda.getLista()}">
                <tr>
                    <td width="80" >${item.id}</td>
                    <td width="150" >${item.producto}</td>
                    <td width="80" >${item.precio}</td>
                    <td width="80" >${item.cantidad}</td>
                    <td width="80" ><a href="controller?op=modificar&id=${item.id}">Modificar</a></td>
                    <td width="80" ><a href="controller?op=eliminar&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>  
            
        </table>
    </body>
</html>