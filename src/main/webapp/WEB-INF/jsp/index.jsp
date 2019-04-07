<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<html lang="en">
<head>
    <spring:url value="js/jquery-3.2.1.min.js" var="jQuery"/>
    <spring:url value="js/index.js" var="pageJS"/>
    <spring:url value="/css/index.css" var="mainPageCss" />

    <link href="${mainPageCss}" rel="stylesheet" />
    <title>Bastion checker</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>
<div id="navbar" class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <h2>Habitat Supervisor Explorer</h2>
    </div>
    <div class="navbar-collapse collapse">
        <h4>Bastion base host: ${environment}</h4>
    </div>

</div>
<div class="jumbotron">
    <h1>Services information</h1>
    <div class="infostring"></div>
    <div class="resultInfo" style="width: 95%"></div>
</div>

<script type="text/javascript" src="${jQuery}"></script>
<script type="text/javascript" src="${pageJS}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        applyStyles();
    });
</script>
</body>
</html>