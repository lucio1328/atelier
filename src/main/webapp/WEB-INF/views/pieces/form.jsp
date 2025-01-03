<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.PiecesDetacheesDTO"%>
<%
    PiecesDetacheesDTO piece = (PiecesDetacheesDTO) request.getAttribute("piece");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Piece" : "Modification Piece" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouvelle Piece" : "Modification Piece" %>
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
                        out.println("/pieces/create");
                    } 
                    else {
                        out.println("/pieces/edit/" + piece.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom piece :</label>
                        <input 
                            type="text" 
                            id="nom" 
                            name="nomPiece" 
                            class="form-control" 
                            value="<%= (piece != null) ? piece.getNomPiece() : "" %>" 
                            required>
                    </div>
                    <div class="mb-3">
                        <label for="reference" class="form-label">Reference :</label>
                        <input 
                            type="text" 
                            id="reference" 
                            name="reference" 
                            class="form-control" 
                            value="<%= (piece != null) ? piece.getReference() : "" %>" 
                            required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description :</label>
                        <input 
                            type="text" 
                            id="description" 
                            name="description" 
                            class="form-control" 
                            value="<%= (piece != null) ? piece.getDescription() : "" %>" 
                            required>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/pieces/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
