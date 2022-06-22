<%--
  Created by IntelliJ IDEA.
  User: Raissa
  Date: 19/06/2022
  Time: 00:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Ver quadro</title>
    <style>
        .card {
            border: 2px solid black;
        }
    </style>
</head>
<body>
${quadro}
<h1>${quadro.titulo}</h1>
<h3>${quadro.descricao}</h3>
${quadro.colunaArrayList}

<div class="center">
    <a href="quadro?acao=quadros" class="primary">Voltar para lista de quadros</a>
</div>

<div class="center">
    <a href="router?acao=nova-coluna" class="primary">Nova coluna</a>
</div
<c:forEach items="${quadro.colunaArrayList}" var="coluna">
    <div class="card">
        <h3>${coluna.titulo}</h3>
        <c:forEach items="${coluna.cartaoArrayList}" var="cartao">
            <div class="card">
                <h3>${cartao.titulo}</h3>
                <p>${cartao.data_inicio}</p>
                <p>${cartao.data_termino}</p>
                <c:choose>
                    <c:when test="${cartao.status == 'true'}">
                        <p>Finalizado</p>
                    </c:when>
                    <c:otherwise>
                        <p>Pendente</p>
                    </c:otherwise>
                </c:choose>
                <a href="router?acao=excluir-cartao&id-cartao=${quadro.id}" class="primary">Excluir cartao</a>
                <a href="router?acao=editar-cartao&id-cartao=${quadro.id}" class="primary">Editar cartao</a>
            </div>
        </c:forEach>
        <a href="router?acao=excluir-coluna&id-coluna=${coluna.id}" class="primary">Excluir coluna</a>
        <a href="router?acao=editar-coluna&id-coluna=${coluna.id}" class="primary">Editar coluna</a>
    </div>
</c:forEach>
</body>
</html>
