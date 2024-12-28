<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gestion.atelier.DTO.SpecialitesDTO,java.util.List" %>
<% List<SpecialitesDTO> specialites = (List<SpecialitesDTO>) request.getAttribute("specialites"); %>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Spécialités</title>
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
                <h1 class="h3 mb-0">Liste des Spécialités</h1>
            </div>
            <div class="card-body">
                <% if (specialites != null && !specialites.isEmpty()) { %>
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Libellé</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (SpecialitesDTO specialite : specialites) { %>
                                <tr>
                                    <td><%= specialite.getId() %></td>
                                    <td><%= specialite.getLibelle() %></td>
                                    <td>
                                        <a href="<%= "/specialites/edit/" + specialite.getId() %>" class="btn btn-sm btn-warning">Modifier</a> |
                                        <a href="<%= "/specialites/delete/" + specialite.getId() %>" class="btn btn-sm btn-danger" 
                                           onclick="return confirm('Voulez-vous vraiment supprimer cette spécialité ?')">Supprimer</a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } else { %>
                    <p>Aucune spécialité trouvée.</p>
                <% } %>

                <div>
                    <a href="/specialites/create" class="btn btn-primary">Créer une nouvelle spécialité</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
