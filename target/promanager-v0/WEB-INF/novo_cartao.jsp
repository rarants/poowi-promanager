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
    <title>Nova cartão</title>
</head>
<body>
<h1>Nova cartão</h1>
<p>
    Preencha as informações abaixo
</p>
<div class="container">
    <form action="cartao" method="post" class="formulario child">
        <div class="grid-container">
            <div class="titulo left">
                <label for="titulo">Título</label>
                <input type="text" placeholder="Digite o título do cartão" name="titulo" required/>
            </div>
            <div class="status left">
                <label for="status">Finalizado</label>
                <input type="checkbox" name="status"/>
            </div>
            <div class="descricao left">
                <label for="descricao">Descrição</label>
                <input type="textarea" placeholder="Informe uma descrição" name="descricao" />
            </div>
            <div class="data_inicio left">
                <label for="dataInicio">Data de início</label>
                <input type="date" name="data_inicio" />
            </div>
            <div class="data_termino left">
                <label for="dataTermino">Data de término</label>
                <input type="date" name="data_termino" />
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
            <input type="submit" value="Adicionar cartão" name="cartao" class="button primary"/>
        </div>
        <br />
        <div class="center">
            <a href="quadro?acao=quadro&id-quadro=${quadro.id}" class="primary">Voltar para o quadro</a>
        </div>
    </form>
</div>
</body>
</html>
