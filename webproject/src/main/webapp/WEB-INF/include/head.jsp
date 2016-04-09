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
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">Themes <span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="themes">
                                <li><a href="../default/">Default</a></li>
                                <li class="divider"></li>
                                <li><a href="../cerulean/">Cerulean</a></li>
                                <li><a href="../cosmo/">Cosmo</a></li>
                                <li><a href="../cyborg/">Cyborg</a></li>
                                <li><a href="../darkly/">Darkly</a></li>
                                <li><a href="../flatly/">Flatly</a></li>
                                <li><a href="../journal/">Journal</a></li>
                                <li><a href="../lumen/">Lumen</a></li>
                                <li><a href="../paper/">Paper</a></li>
                                <li><a href="../readable/">Readable</a></li>
                                <li><a href="../sandstone/">Sandstone</a></li>
                                <li><a href="../simplex/">Simplex</a></li>
                                <li><a href="../slate/">Slate</a></li>
                                <li><a href="../spacelab/">Spacelab</a></li>
                                <li><a href="../superhero/">Superhero</a></li>
                                <li><a href="../united/">United</a></li>
                                <li><a href="../yeti/">Yeti</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="../help/">Help</a>
                        </li>
                        <li>
                            <a href="http://news.bootswatch.com">Blog</a>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="download">Cerulean <span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="download">
                                <li><a href="http://jsfiddle.net/bootswatch/9y480qo5/">Open Sandbox</a></li>
                                <li class="divider"></li>
                                <li><a href="./bootstrap.min.css">bootstrap.min.css</a></li>
                                <li><a href="./bootstrap.css">bootstrap.css</a></li>
                                <li class="divider"></li>
                                <li><a href="./variables.less">variables.less</a></li>
                                <li><a href="./bootswatch.less">bootswatch.less</a></li>
                                <li class="divider"></li>
                                <li><a href="./_variables.scss">_variables.scss</a></li>
                                <li><a href="./_bootswatch.scss">_bootswatch.scss</a></li>
                            </ul>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="http://builtwithbootstrap.com/" target="_blank">Built With Bootstrap</a></li>
                        <li><a href="https://wrapbootstrap.com/?ref=bsw" target="_blank">WrapBootstrap</a></li>

                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="members">Espace membre<span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="members">
                                <c:choose>
                                    <c:when test="${cookie.containsKey('username')}">
                                        <li><a href="./bootstrap.min.css">Logout</a></li>
                                        </c:when>    
                                        <c:otherwise>
                                        <li><a href="./bootstrap.min.css">Login</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </li>
                    </ul>

                </div>
            </div>
        </div>

        <div class="container content">