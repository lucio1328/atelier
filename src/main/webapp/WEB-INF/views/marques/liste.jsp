<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.MarquesDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Marques</title>
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
                <h1 class="h3 mb-0">Liste des Marques</h1>
            </div>
            <div class="card-body">
                <!-- Table DataTables -->
                <table id="marquesTable" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% 
                        List<MarquesDTO> marques = (List<MarquesDTO>) request.getAttribute("marques");
                        if (marques != null && !marques.isEmpty()) {
                            for (MarquesDTO marque : marques) {
                    %>
                        <tr>
                            <td><%= marque.getId() %></td>
                            <td><%= marque.getNomMarque() %></td>
                            <td>
                                <a href="edit/<%= marque.getId() %>" class="btn btn-sm btn-warning">Modifier</a>
                                <a href="delete/<%= marque.getId() %>" class="btn btn-sm btn-danger">Supprimer</a>
                            </td>
                        </tr>
                    <% 
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="3" class="text-center">Aucune marque trouvée.</td>
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
            $('#marquesTable').DataTable({
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
