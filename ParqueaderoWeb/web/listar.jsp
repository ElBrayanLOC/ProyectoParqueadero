<%@page import="com.parqueadero.entidades.Bahia"%>
<%@page import="com.parqueadero.entidades.BahiaE"%>
<%@page import="java.util.List"%>
<%
    List<BahiaE> Bahias = (List<BahiaE>) request.getAttribute("mapa");
    int pos = 0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
    </head>
    <META HTTP-EQUIV="REFRESH" CONTENT="3;URL=http://localhost:8083/ParqueaderoWeb/MapaUnicaucaServlet?accion=listar"> 
    </head>
    <body style = "background-image: url(https://www.solofondos.com/wp-content/uploads/2015/11/new-fondo1lateral.jpg)">
        <h1 align="center" style="color: white">Mapa unicauca!</h1>
        <div style="position: fixed; top: 10%;left: 30%; background-image: url(https://1.bp.blogspot.com/-RmwFULptdvM/XmwKF635HbI/AAAAAAAAFcA/H_r6YFo1RewQgMxYz-qp0N7kVlAPafGhwCLcBGAsYHQ/s640/Mapa.png);
            background-repeat:no-repeat;
            width:600px;
            height:600px">
            <style>
                #absoluto .green {
                width: 20px;
                height: 30px;
                border: 3px solid green;
                margin: 0;
                padding: 1em;
                background-color: green;
               }
               #absoluto .yellow {
                width: 20px;
                height: 30px;
                border: 3px solid yellow;
                margin: 0;
                padding: 1em;
                background-color: yellow;
               }
               #absoluto .estandar {
                width: 20px;
                height: 30px;
                border: 3px solid white;
                margin: 0;
                padding: 1em;
                background-color: white;
               }
            </style>
            <div id="absoluto"
                <% if (Bahias != null) { %>
                    <% for (BahiaE Bah : Bahias) {%>
                        <h6 class="m-0 font-weight-bold text-primary"></h6>
                        <% if (Bah.getBahEstado().equals("0")) { %>
                            <% if (pos == 0) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 60%;left: 56%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 1) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 60%;left: 62%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 2) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 60%;left: 68%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 3) { %>
                            <div style="text-align: center;">
                            <button style="position: absolute;top: 66%;left: 56%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 4) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 66%;left: 62%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 5) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 66%;left: 68%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 6) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 72%;left: 56%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 7) { %>
                                <div style="text-align: center;">
                                <button style="position: absolute;top: 72%;left: 62%;background-color: green">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 8) { %>
                                <div style="text-align: center;">
                                <button style="position: absolute;top: 72%;left: 68%;background-color: green">*</button>
                                </div>
                            <% pos = pos + 1; }
                           else if (pos == 9) { %>
                                <div style="text-align: center;">
                                <button style="position: absolute;top: 78%;left: 56%;background-color: green">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 10) { %>
                                <div style="text-align: center;">
                                <button style="position: absolute;top: 78%;left: 62%;background-color: green">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 11) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 78%;left: 68%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 12) { %>
                            <div style="text-align: center;">
                                 <button style="position: absolute;top: 28%;left: 56%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 13) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 28%;left: 62%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 14) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 28%;left: 68%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; 
                            } else if (pos == 15) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 56%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 16) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 62%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 17) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 68%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 18) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 56%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 19) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 62%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 20) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 68%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 21) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 56%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 22) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 62%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 23) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 68%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 24) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 28%;left: 16%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 25) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 28%;left: 22%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 26) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 16%;background-color: green">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 27) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 22%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 28) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 16%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 29) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 22%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 30) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 16%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 31) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 22%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 32) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 56%;left: 16%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 33) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 56%;left: 22%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 34) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 68%;left: 16%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 35) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 68%;left: 22%;background-color: green">*</button>
                            </div>
                            <% pos = pos + 1; }%>
                        <% }%> 
                        
                        <% if (Bah.getBahEstado().equals("1")) { %>
                            <% if (pos == 0) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 60%;left: 56%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 1) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 60%;left: 62%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 2) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 60%;left: 68%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 3) { %>
                            <div style="text-align: center;">
                            <button style="position: absolute;top: 66%;left: 56%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 4) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 66%;left: 62%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 5) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 66%;left: 68%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 6) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 72%;left: 56%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 7) { %>
                                <div style="text-align: center;">
                                <button style="position: absolute;top: 72%;left: 62%;background-color: yellow">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 8) { %>
                                <div style="text-align: center;">
                                <button style="position: absolute;top: 72%;left: 68%;background-color: yellow">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 9) { %>
                                <div style="text-align: center;">
                                <button style="position: absolute;top: 78%;left: 56%;background-color: yellow">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 10) { %>
                                <div style="text-align: center;">
                                <button style="position: absolute;top: 78%;left: 62%;background-color: yellow">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 11) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 78%;left: 68%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 12) { %>
                            <div style="text-align: center;">
                                 <button style="position: absolute;top: 28%;left: 56%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 13) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 28%;left: 62%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 14) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 28%;left: 68%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 15) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 56%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 16) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 62%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 17) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 68%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 18) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 56%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 19) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 62%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 20) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 68%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 21) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 56%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 22) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 62%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 23) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 68%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 24) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 28%;left: 16%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 25) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 28%;left: 22%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 26) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 16%;background-color: yellow">*</button>
                                </div>
                            <% pos = pos + 1; }
                            else if (pos == 27) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 34%;left: 22%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 28) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 16%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 29) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 40%;left: 22%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 30) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 16%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 31) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 46%;left: 22%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 32) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 56%;left: 16%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 33) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 56%;left: 22%;background-color: yellow">*</button>
                            </div>
                             <% pos = pos + 1; }
                            else if (pos == 34) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 68%;left: 16%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; }
                            else if (pos == 35) { %>
                            <div style="text-align: center;">
                                <button style="position: absolute;top: 68%;left: 22%;background-color: yellow">*</button>
                            </div>
                            <% pos = pos + 1; } %>
                        <% }%>
                    <% }%>
                <% }%>  
            </div>
        </div>
    </body>
</html>