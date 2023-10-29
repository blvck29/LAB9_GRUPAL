<%@ page import="com.example.lab9.model.beans.Title" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Title title = (Title) request.getAttribute("title");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Editar un Title</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Editar un Title</h1>
    <form method="post" action="<%=request.getContextPath()%>/TitleServlet?action=e">
        <div class="mb-3">
            <input type="hidden" class="form-control" name="empNo" value="<%=title.getEmpNo()%>">
        </div>
        <div class="mb-3">
            <label>title</label>
            <input type="text" class="form-control" name="title" value="<%=title.getTitle()%>">
        </div>
        <div class="mb-3">
            <label>from date</label>
            <input type="text" class="form-control" name="fromDate" value="<%=title.getFromDate()%>">
        </div>
        <div class="mb-3">
            <label>to date</label>
            <input type="text" class="form-control" name="toDate" value="<%=title.getToDate()%>">
        </div>
        <a href="<%=request.getContextPath()%>/TitleServlet" class="btn btn-danger">Regresar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
