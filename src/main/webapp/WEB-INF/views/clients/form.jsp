<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.ClientsDTO"%>
<%@page import="com.gestion.atelier.DTO.GenreDTO"%>
<%@page import="java.util.List"%>
<%
    ClientsDTO client = (ClientsDTO) request.getAttribute("client");
    List<GenreDTO> genres = (List<GenreDTO>) request.getAttribute("genres");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Client" : "Modification Client" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouveau Client" : "Modification Client" %>
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
                        out.println("/clients/create");
                    } 
                    else {
                        out.println("/clients/edit/" + client.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom :</label>
                        <input 
                            type="text" 
                            id="nom" 
                            name="nom" 
                            class="form-control" 
                            value="<%= (client != null) ? client.getNom() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="prenom" class="form-label">Prenom :</label>
                        <input 
                            type="text" 
                            id="prenom" 
                            name="prenom" 
                            class="form-control" 
                            value="<%= (client != null) ? client.getPrenom() : "" %>" 
                            >
                    </div>

                    <div class="mb-3">
                        <label for="genre" class="form-label">Genre :</label>
                        <select id="genre" name="genre" class="form-control" required>
                            <option value="">Sélectionner un genre</option>
                            <% 
                                for (GenreDTO genre : genres) {
                            %>
                                <option value="<%= genre.getId() %>" <%= (client != null && client.getGenre().getId().equals(genre.getId())) ? "selected" : "" %> >
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
                            value="<%= (client != null) ? client.getDateNaissance() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email :</label>
                        <input 
                            type="text" 
                            id="email" 
                            name="email" 
                            class="form-control" 
                            value="<%= (client != null) ? client.getEmail() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="telephone" class="form-label">Telephone :</label>
                        <input 
                            type="text" 
                            id="telephone" 
                            name="telephone" 
                            class="form-control" 
                            value="<%= (client != null) ? client.getTelephone() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="adresse" class="form-label">Adresse :</label>
                        <input 
                            type="text" 
                            id="adresse" 
                            name="adresse" 
                            class="form-control" 
                            value="<%= (client != null) ? client.getAdresse() : "" %>" 
                            required>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/clients/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
