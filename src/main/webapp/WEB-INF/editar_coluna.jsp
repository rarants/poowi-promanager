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
    <title>Editar coluna</title>
</head>
<body>
<h1>Editar coluna ${coluna} *** ${coluna.titulo}</h1>
<p>
    Preencha as informações abaixo
</p>
<div class="container">
    <form action="coluna" method="post" class="formulario child">
        <div class="grid-container">
            <div class="titulo left">
                <label for="titulo">Título</label>
                <input type="text" placeholder="Digite o título do quadro" name="titulo" value="${coluna.titulo}" required/>
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
            <input type="submit" value="Atualizar coluna" name="atualizar" class="button primary"/>
            <input type="submit" value="Excluir coluna" name="excluir" class="button primary"/>
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
