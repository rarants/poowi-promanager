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
    <title>Cadastro</title>
</head>
<body>
    <h1>Cadastro</h1>
    <p>
        Bem-vindo ao ProManager! Aqui você pode gerenciar suas atividades diárias e realizar planejamento de tarefas
        e projetos. Faça o cadastre-se e comece a usar! Ou então, faça login!
    </p>
    <div class="container">
        <form action="register" method="post" class="formulario child">
            <div class="grid-container">
                <div class="nome left">
                    <label for="nome">Nome</label>
                    <input type="text" placeholder="Digite seu nome" name="nome" required class="input"/>
                </div>
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
                <input type="submit" value="Register" name="register" class="button primary"/>
            </div>
            <br />
            <div class="center">
                <a href="register?route=login" class="primary">Já possui cadastro?</a>
            </div>
        </form>
    </div>
</body>
</html>
