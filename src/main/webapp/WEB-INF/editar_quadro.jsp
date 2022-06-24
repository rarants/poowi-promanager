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
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar bg-dark text-white">
        <div class="container-fluid">
            <a class="navbar-brand text-white">Editar quadro</a>
            <div class="d-flex">
                <a href="router?acao=sair" class="btn btn-danger btn-sm mb-3">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
                        <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                    </svg>
                    Sair
                </a>
            </div>
        </div>
    </nav>
    <div class="p-4">
        <div class="text-center">
            <h1>Editar quadro <span class="text-primary">${quadro.titulo}</span></h1>
            <p>
                Preencha as informações abaixo
            </p>
        </div>
        <div class="text-left mb-4">
            <a href="quadro?acao=quadros">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                </svg>
                Voltar para lista de quadros
            </a>
        </div>
        <div class="mx-auto card p-2" style="width: 25rem;">
            <div class="card-body">
                <div class="card-title">Editar quadro</div>
                <form action="quadro" method="post" class="formulario child">
                <div class="row">
                    <div class="col-6 mb-3">
                        <label for="titulo" class="form-label">Título</label>
                        <input type="text" class="form-control" placeholder="Digite o título do quadro" name="titulo" value="${quadro.titulo}" required/>
                    </div>
                    <div class="col-6 mb-3">
                        <label for="publico" class="form-label">Público</label>
                        <br />
                        <c:choose>
                            <c:when test="${quadro.publico == 'true'}">
                                <input type="checkbox" class="form-check-input" name="publico" checked/>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" class="form-check-input" name="publico"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-12 mb-3">
                        <label for="descricao" class="form-label">Descrição</label>
                        <input type="textarea" class="form-control" placeholder="Informe uma descrição" name="descricao" value="${quadro.descricao}" />
                    </div>
                </div>
                <div class="text-center mt-3">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <b>${error}</b>
                        </div>
                    </c:if>
                </div>
                <div class="text-center mt-3">
                    <input type="submit" value="Atualizar quadro" name="atualizar" class="btn btn-primary btn-sm mt-4"/>
                </div>
            </form>
            </div>
        </div>
    </div>
</body>
</html>
