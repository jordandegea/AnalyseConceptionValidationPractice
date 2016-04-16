<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <title><%= request.getParameter("title")%></title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/3.3.6/cerulean/bootstrap.css" >
        <script type="text/javascript" src="http://jquery.com/download/"></script>
        <style>
            .content{
                margin-top: 50px;
            }
            .footer {
                position: absolute;
                bottom: 0;
                width: 100%;
                /* Set the fixed height of the footer here */
                height: 60px;
                background-color: #f5f5f5;
            }

            .container .text-muted {
                margin: 20px 0;
            }
        </style>

    </head>
    <body>

        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a  class="navbar-brand" href="joueur?action=SHOW" >RolePlay Manager</a>
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
                                <li><a href="login">Connexion</a></li>
                                <li><a href="register">Inscription</a></li>
                                    <%
                                    } else {
                                    %>
                                <li><a href="logout">Deconnexion</a></li>
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