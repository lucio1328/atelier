<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>
<%
    TypeReparationDTO typeReparations = (TypeReparationDTO) request.getAttribute("typeReparation");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Reparation" : "Modification Reparation" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouvelle Reparation" : "Modification Reparation" %>
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
                        out.println("/typeReparation/create");
                    } 
                    else {
                        out.println("/typeReparation/edit/" + typeReparations.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="libelle" class="form-label">Libelle :</label>
                        <input 
                            type="text" 
                            id="libelle" 
                            name="libelle" 
                            class="form-control" 
                            value="<%= (typeReparations != null) ? typeReparations.getLibelle() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="commission" class="form-label">Commission :</label>
                        <input 
                            type="number" 
                            id="commission" 
                            name="commission" 
                            class="form-control" 
                            value="<%= (typeReparations != null) ? typeReparations.getCommission() : "" %>" 
                            >
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/typeReparation/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
