<%--
  Created by IntelliJ IDEA.
  User: Raissa
  Date: 19/06/2022
  Time: 00:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Editar cartão</title>
</head>
<body>
<h1>Editar cartão ${coluna} *** ${coluna.titulo}</h1>
<p>
    Preencha as informações abaixo
</p>
<div class="container">
    <form action="cartao" method="post" class="formulario child">
        <div class="grid-container">
            <div class="titulo left">
                <label for="titulo">Título</label>
                <input type="text" placeholder="Digite o título do cartão" name="titulo" value="${cartao.titulo}" required/>
            </div>


            <div class="status left">
                <label for="status">Finalizado</label>

                <c:choose>
                    <c:when test="${cartao.status == 'true'}">
                        <input type="checkbox" name="status" checked/>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="status"/>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="descricao left">
                <label for="descricao">Descrição</label>
                <input type="textarea" placeholder="Informe uma descrição" name="descricao" value="${cartao.descricao}" />
            </div>
            <div class="data_inicio left">
                <label for="dataInicio">Data de início</label>
                <input type="date" name="dataInicio" value="${cartao.dataInicio}" />
            </div>
            <div class="data_termino left">
                <label for="dataTermino">Data de término</label>
                <input type="date" name="dataTermino" value="${cartao.dataTermino}"/>
            </div>
        </div>
        <c:if test="${not empty error}">
            <div class="container center">
                <h2 class="child error">
                    <b>${error}</b>
                </h2>
            </div>
        </c:if>
        <div class="center">
            <input type="submit" value="Atualizar cartão" name="atualizar" class="button primary"/>
            <input type="submit" value="Excluir cartão" name="excluir" class="button primary"/>
        </div>
        <br />
        ${quadro}
        <div class="center">
            <a href="quadro?acao=quadro&id-quadro=${quadro.id}" class="primary">Voltar para o quadro</a>
        </div>
    </form>
</div>
</body>
</html>
