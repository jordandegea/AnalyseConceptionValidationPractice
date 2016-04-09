<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <title><%= request.getParameter("title")%></title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/3.3.6/cerulean/bootstrap.css" >

        <style>
            .content{
                margin-top: 50px;
            }
        </style>

    </head>
    <body>

        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a href="../" class="navbar-brand">ACVLRoles - FTG</a>
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse" id="navbar-main">
                    <ul class="nav navbar-nav">
                        <%
                            if (null == session.getAttribute("idUser")) {
                        %>

                        <%
                        } else {
                        %>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">Themes <span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="themes">
                                <li><a href="../default/">Default</a></li>
                                <li class="divider"></li>
                                <li><a href="../cerulean/">Cerulean</a></li>
                                <li><a href="../cosmo/">Cosmo</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="../help/">Help</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="members">Espace membre<span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="members">
                                <%
                                    if (null == session.getAttribute("idUser")) {
                                %>
                                <li><a href="login">Login</a></li>
                                    <%
                                    } else {
                                    %>
                                <li><a href="logout">Logout</a></li>
                                    <%
                                        }
                                    %>

                            </ul>
                        </li>
                    </ul>

                </div>
            </div>
        </div>

        <div class="container content">