<%@page import="com.parqueadero.service.BahiaFacadeREST"%>
<%@page import="com.parqueadero.entidades.Bahia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style = "background-image: url(https://www.solofondos.com/wp-content/uploads/2015/11/new-fondo1lateral.jpg)">
        <h1 align="center" style="color: white">Mapa unicauca!</h1>
        <div style="position: absolute">
            <a href="<%= request.getContextPath() %>/MapaUnicaucaServlet?accion=listar" style="color: lightgrey">Ver Mapa Unicacua</a>
        </div>
    </body>
</html>
