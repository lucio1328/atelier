<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.TechniciensDTO"%>
<%@page import="com.gestion.atelier.DTO.GenreDTO"%>
<%@page import="java.util.List"%>
<%
    TechniciensDTO technicien = (TechniciensDTO) request.getAttribute("technicien");
    List<GenreDTO> genres = (List<GenreDTO>) request.getAttribute("genres");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Technicien" : "Modification Technicien" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouveau Technicien" : "Modification Technicien" %>
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
                        out.println("/techniciens/create");
                    } 
                    else {
                        out.println("/techniciens/edit/" + technicien.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom :</label>
                        <input 
                            type="text" 
                            id="nom" 
                            name="nom" 
                            class="form-control" 
                            value="<%= (technicien != null) ? technicien.getNom() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="prenom" class="form-label">Prenom :</label>
                        <input 
                            type="text" 
                            id="prenom" 
                            name="prenom" 
                            class="form-control" 
                            value="<%= (technicien != null) ? technicien.getPrenom() : "" %>" 
                            >
                    </div>

                    <div class="mb-3">
                        <label for="genre" class="form-label">Genre :</label>
                        <select id="genre" name="genre" class="form-control" required>
                            <option value="">Sélectionner un genre</option>
                            <% 
                                for (GenreDTO genre : genres) {
                            %>
                                <option value="<%= genre.getId() %>" <%= (technicien != null && technicien.getGenre().getId().equals(genre.getId())) ? "selected" : "" %> >
                                    <%= genre.getLibelle() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="dateNaissance" class="form-label">Date de naissance :</label>
                        <input 
                            type="date" 
                            id="dateNaissance" 
                            name="dateNaissance" 
                            class="form-control" 
                            value="<%= (technicien != null) ? technicien.getDateNaissance() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email :</label>
                        <input 
                            type="text" 
                            id="email" 
                            name="email" 
                            class="form-control" 
                            value="<%= (technicien != null) ? technicien.getEmail() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="motDePasse" class="form-label">Mot de passe :</label>
                        <input 
                            type="text" 
                            id="motDePasse" 
                            name="motDePasse" 
                            class="form-control" 
                            value="<%= (technicien != null) ? technicien.getMotDePasse() : "" %>" 
                            required>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/techniciens/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
