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
</head>
<body>
${quadro}
<h1>${quadro.titulo}</h1>
<h3>${quadro.descricao}</h3>

<div class="center">
    <a href="quadro?acao=quadros" class="primary">Voltar para lista de quadros</a>
</div>

<div class="center">
    <a href="router?acao=nova-coluna" class="primary">Nova coluna</a>
</div>
<c:if test="${quadro.colunaArrayList.size() == 0}">
    <div class="container center">
        <h2 class="child error">
            <b>Ainda não foram adicionadas colunas ao quadro.</b>
        </h2>
    </div>
</c:if>

</body>
</html>
