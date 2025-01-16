<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.ModelesDTO"%>
<%@page import="com.gestion.atelier.DTO.MarquesDTO"%>
<%@page import="com.gestion.atelier.DTO.CategoriesDTO"%>
<%@page import="java.util.List"%>
<%
    ModelesDTO modele = (ModelesDTO) request.getAttribute("modele");
    List<MarquesDTO> marques = (List<MarquesDTO>) request.getAttribute("marques");
    List<CategoriesDTO> categories = (List<CategoriesDTO>) request.getAttribute("categories");
    
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Modèle" : "Modification Modèle" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouveau Modèle" : "Modification Modèle" %>
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
                        out.println("/modeles/create");
                    } 
                    else {
                        out.println("/modeles/edit/" + modele.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom du Modèle :</label>
                        <input 
                            type="text" 
                            id="nom" 
                            name="nomModele" 
                            class="form-control" 
                            value="<%= (modele != null) ? modele.getNomModele() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="marque" class="form-label">Marque :</label>
                        <select id="marque" name="marque" class="form-control" required>
                            <option value="">Sélectionner une marque</option>
                            <% 
                                for (MarquesDTO marque : marques) {
                            %>
                                <option value="<%= marque.getId() %>" <%= (modele != null && modele.getMarque().getId().equals(marque.getId())) ? "selected" : "" %> >
                                    <%= marque.getNomMarque() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="categorie" class="form-label">Categorie :</label>
                        <select id="categorie" name="categorie" class="form-control" required>
                            <option value="">Sélectionner une categorie</option>
                            <% 
                                for (CategoriesDTO categorie : categories) {
                            %>
                                <option value="<%= categorie.getId() %>" <%= (modele != null && modele.getCategorie().getId().equals(categorie.getId())) ? "selected" : "" %> >
                                    <%= categorie.getLibelle() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/modeles/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
