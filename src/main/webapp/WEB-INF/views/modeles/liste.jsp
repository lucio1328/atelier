<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.ModelesDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Modèles</title>
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
                <h1 class="h3 mb-0">Liste des Modèles</h1>
            </div>
            <div class="card-body">
                <!-- Table DataTables -->
                <table id="modelesTable" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Marque</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% 
                        List<ModelesDTO> modeles = (List<ModelesDTO>) request.getAttribute("modeles");
                        if (modeles != null && !modeles.isEmpty()) {
                            for (ModelesDTO modele : modeles) {
                    %>
                        <tr>
                            <td><%= modele.getId() %></td>
                            <td><%= modele.getNomModele() %></td>
                            <td><%= modele.getMarque().getNomMarque() %></td>
                            <td>
                                <a href="/modeles/edit/<%= modele.getId() %>" class="btn btn-sm btn-warning">Modifier</a>
                                <a href="/modeles/delete/<%= modele.getId() %>" class="btn btn-sm btn-danger">Supprimer</a>
                            </td>
                        </tr>
                    <% 
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="4" class="text-center">Aucun modèle trouvé.</td>
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
            $('#modelesTable').DataTable({
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
