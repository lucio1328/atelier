<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gestion.atelier.DTO.SpecialitesDTO" %>
<% 
    String action = (String) request.getAttribute("action");
    SpecialitesDTO specialite = (SpecialitesDTO) request.getAttribute("specialite"); 
    String error = (String) request.getAttribute("error");
%>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire Spécialité</title>
    <style>
        body {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <% if ("create".equals(action)) { %>Créer une spécialité<% } else { %>Modifier la spécialité<% } %>
                </h1>
            </div>
            <div class="card-body">
                <form action="<% if ("create".equals(action)) { 
                    out.println("/specialites/create");
                } else { 
                    out.println("/specialites/edit/" + specialite.getId());
                } %>" method="POST">
                    <div class="mb-3">
                        <label for="libelle" class="form-label">Libellé :</label>
                        <input type="text" id="libelle" name="libelle" class="form-control" value="<%= specialite != null ? specialite.getLibelle() : "" %>" required>
                    </div>
                    
                    <% if (error != null) { %>
                        <div class="mb-3">
                            <span class="text-danger"><%= error %></span>
                        </div>
                    <% } %>

                    <div>
                        <button type="submit" class="btn btn-primary">
                            <% if ("create".equals(action)) { %>Créer<% } else { %>Mettre à jour<% } %>
                        </button>
                        <a href="/specialites/liste" class="btn btn-secondary">Retour à la liste</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
