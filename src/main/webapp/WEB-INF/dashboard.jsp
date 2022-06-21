<%--
  Created by IntelliJ IDEA.
  User: Raissa
  Date: 18/06/2022
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Meus quadros</title>
    <style>
        .card {
            border: 2px solid black;
        }
    </style>
</head>
<body>
    <h1>Bem vindo! ${usuario_logado.nome}</h1>
    <p>Quadros</p>
    <div class="center">
        <a href="router?acao=novo-quadro" class="primary">Novo quadro</a>
    </div>
    <div class="center">
        <a href="router?acao=sair" class="primary">Sair</a>
    </div>
    <c:forEach items="${quadros}" var="quadro">
        <div class="card">
            <h3>${quadro.titulo}</h3>
            <p>${quadro.descricao}</p>
            <c:choose>
                <c:when test="${quadro.publico == 'true'}">
                    <p>Público</p>
                </c:when>
                <c:otherwise>
                    <p>Privado</p>
                </c:otherwise>
            </c:choose>
            <a href="router?acao=quadro&id=${quadro.id}" class="primary">Ver quadro</a>
            <a href="router?acao=excluir-quadro&id=${quadro.id}" class="primary">Excluir quadro</a>
            <a href="router?acao=editar-quadro&id=${quadro.id}" class="primary">Editar quadro</a>
        </div>
    </c:forEach>

</body>
</html>
