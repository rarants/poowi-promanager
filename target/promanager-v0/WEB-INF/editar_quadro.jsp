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
    <title>Editar quadro</title>
</head>
<body>
<h1>Editar quadro ${quadro} *** ${quadro.titulo}</h1>
<p>
    Preencha as informações abaixo
</p>
<div class="container">
    <form action="quadro" method="post" class="formulario child">
        <div class="grid-container">
            <div class="titulo left">
                <label for="titulo">Título</label>
                <input type="text" placeholder="Digite o título do quadro" name="titulo" value="${quadro.titulo}" required/>
            </div>
            <div class="descricao left">
                <label for="descricao">Descrição</label>
                <input type="textarea" placeholder="Informe uma descrição" name="descricao" value="${quadro.descricao}" required/>
            </div>
            <div class="publico left">
                <label for="publico">Público</label>

                <c:choose>
                    <c:when test="${quadro.publico == 'true'}">
                        <input type="checkbox" name="publico" checked/>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="publico"/>
                    </c:otherwise>
                </c:choose>
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
            <input type="submit" value="Atualizar" name="acao" class="button primary"/>
        </div>
        <br />
        <div class="center">
            <a href="quadro?acao=quadros" class="primary">Voltar para lista de quadros</a>
        </div>
    </form>
</div>
</body>
</html>
