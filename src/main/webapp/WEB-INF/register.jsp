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
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
    <div class="p-4">
        <div class="text-center">
            <h1>Cadastro</h1>
            <p>
                Bem-vindo ao ProManager! Aqui você pode gerenciar suas atividades diárias e realizar planejamento de tarefas
                e projetos. Faça o cadastre-se e comece a usar! Ou então, faça login!
            </p>
        </div>
        <div class="mx-auto card p-2" style="width: 25rem;">
            <form action="register" method="post" class="card-body">
                <div class="card-title text-center h3">Cadastro</div>
                <div class="row">
                    <div class="col-12 mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" placeholder="Digite seu nome" name="nome" required/>
                    </div>
                    <div class="col-12 mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control"  placeholder="Digite seu email" name="email" required/>
                    </div>
                    <div class="col-12 mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" class="form-control" placeholder="Digite sua senha" name="password" required/>
                    </div>
                    <div class="text-center">
                        <div class="row">
                            <router:if test="${not empty error}">
                                <div class="alert alert-danger" role="alert">
                                    <b>${error}</b>
                                </div>
                            </router:if>
                        </div>
                        <div class="row">
                            <div>
                                <input type="submit" value="Register" name="register" class="btn btn-primary btn-sm mb-3"/>
                            </div>
                        </div>
                        <div class="row">
                            <a href="register?route=login" class="mb-3">Já possui cadastro?</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
