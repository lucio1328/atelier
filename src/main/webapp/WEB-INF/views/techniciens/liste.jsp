<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.TechniciensDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Techniciens</title>
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
                <h1 class="h3 mb-0">Liste des Techniciens</h1>
            </div>
            <div class="card-body">
                <!-- Table DataTables -->
                <table id="techniciensTable" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prenom</th>
                        <th>Date de naissance</th>
                        <th>Genre</th>
                        <th>Email</th>
                        <th>Mot de passe</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% 
                        List<TechniciensDTO> techniciens = (List<TechniciensDTO>) request.getAttribute("techniciens");
                        if (techniciens != null && !techniciens.isEmpty()) {
                            for (TechniciensDTO client : techniciens) {
                    %>
                        <tr>
                            <td><%= client.getId() %></td>
                            <td><%= client.getNom() %></td>
                            <td><%= client.getPrenom() %></td>
                            <td><%= client.getDateNaissance() %></td>
                            <td><%= client.getGenre().getLibelle() %></td>
                            <td><%= client.getEmail() %></td>
                            <td><%= client.getMotDePasse() %></td>
                            <td>
                                <a href="/techniciens/edit/<%= client.getId() %>" class="btn btn-sm btn-warning">Modifier</a>
                                <a href="/techniciens/delete/<%= client.getId() %>" class="btn btn-sm btn-danger">Supprimer</a>
                            </td>
                        </tr>
                    <% 
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="4" class="text-center">Aucun client trouvé.</td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script>
        // Initialisation de DataTables
        $(document).ready(function () {
            $('#techniciensTable').DataTable({
                "language": {
                    "lengthMenu": "Afficher _MENU_ entrées par page",
                    "zeroRecords": "Aucun résultat trouvé",
                    "info": "Page _PAGE_ sur _PAGES_",
                    "infoEmpty": "Aucune donnée disponible",
                    "infoFiltered": "(filtré à partir de _MAX_ entrées totales)",
                    "search": "Rechercher :",
                    "paginate": {
                        "previous": "Précédent",
                        "next": "Suivant"
                    }
                },
                "paging": true,
                "searching": true,
                "ordering": true
            });
        });
    </script>
</body>
</html>
