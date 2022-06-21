<%--
  Created by IntelliJ IDEA.
  User: Raissa
  Date: 19/06/2022
  Time: 00:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="router" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Nova coluna</title>
</head>
<body>
<h1>Nova coluna</h1>
<p>
    Preencha as informações abaixo
</p>
<div class="container">
    <form action="coluna" method="post" class="formulario child">
        <div class="grid-container">
            <div class="titulo left">
                <label for="titulo">Título</label>
                <input type="text" placeholder="Digite o título da coluna" name="titulo" required/>
            </div>
        </div>
        <router:if test="${not empty error}">
            <div class="container center">
                <h2 class="child error">
                    <b>${error}</b>
                </h2>
            </div>
        </router:if>
        <div class="center">
            <input type="submit" value="Adicionar coluna" name="coluna" class="button primary"/>
        </div>
        <br />
        <div class="center">
            <a href="quadro?acao=quadro" class="primary">Voltar para o quadro</a>
        </div>
    </form>
</div>
</body>
</html>
