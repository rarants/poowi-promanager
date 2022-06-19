<%--
  Created by IntelliJ IDEA.
  User: Raissa
  Date: 18/06/2022
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="router" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Início</title>
</head>
<body>
    <h1>Início</h1>
    <p>
        Bem-vindo ao ProManager! Aqui você pode gerenciar suas atividades diárias e realizar planejamento de tarefas
        e projetos. Faça o login e comece a usar! Ou então, cadastre-se!
    </p>
    <div class="container">
        <form action="login" method="post" class="formulario child">
            <div class="grid-container">
                <div class="email left">
                    <label for="email">Email</label>
                    <input type="email" placeholder="Digite seu email" name="email" required class="input"/>
                </div>
                <div class="password left">
                    <label for="senha">Senha</label>
                    <input type="password" placeholder="Digite sua senha" name="password" required class="input"/>
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
                <input type="submit" value="Login" name="login" class="button primary"/>
            </div>
            <br />
            <div class="center">
                <a href="register?route=register" class="primary">Ainda não possui cadastro?</a>
            </div>
        </form>
    </div>
</body>
</html>
