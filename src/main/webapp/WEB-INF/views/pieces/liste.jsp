<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.PiecesDetacheesDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Pieces</title>
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
                <h1 class="h3 mb-0">Liste des Pieces</h1>
            </div>
            <div class="card-body">
                <!-- Table DataTables -->
                <table id="piecesTable" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Reference</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% 
                        List<PiecesDetacheesDTO> pieces = (List<PiecesDetacheesDTO>) request.getAttribute("pieces");
                        if (pieces != null && !pieces.isEmpty()) {
                            for (PiecesDetacheesDTO piece : pieces) {
                    %>
                        <tr>
                            <td><%= piece.getId() %></td>
                            <td><%= piece.getNomPiece() %></td>
                            <td><%= piece.getReference() %></td>
                            <td><%= piece.getDescription() %></td>
                            <td>
                                <a href="/pieces/edit/<%= piece.getId() %>" class="btn btn-sm btn-warning">Modifier</a>
                                <a href="/pieces/delete/<%= piece.getId() %>" class="btn btn-sm btn-danger">Supprimer</a>
                            </td>
                        </tr>
                    <% 
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="3" class="text-center">Aucune piece trouvée.</td>
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
            $('#piecesTable').DataTable({
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
