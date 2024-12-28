<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.MarquesDTO"%>
<%
    MarquesDTO marque = (MarquesDTO) request.getAttribute("marque");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Marque" : "Modification Marque" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouvelle Marque" : "Modification Marque" %>
                </h1>
            </div>
            <div class="card-body">
                <% 
                    String errorMessage = (String) request.getAttribute("error");
                    if (errorMessage != null) {
                %>
                    <div class="alert alert-danger">
                        <%= errorMessage %>
                    </div>
                <% } %>

                <form action="<% if("create".equals(request.getAttribute("action"))){
                        out.println("/marques/create");
                    } 
                    else {
                        out.println("/marques/edit/" + marque.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom :</label>
                        <input 
                            type="text" 
                            id="nom" 
                            name="nomMarque" 
                            class="form-control" 
                            value="<%= (marque != null) ? marque.getNomMarque() : "" %>" 
                            required>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/marques/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
