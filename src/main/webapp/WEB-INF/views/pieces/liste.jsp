<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.PiecesDetacheesDTO"%>
<%@page import="java.util.List"%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Pieces</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Reference</th>
                    <th scope="col">Description</th>
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
                    } %>
            </tbody>
            </table>
            <!-- End Table with stripped rows -->

        </div>
        </div>

    </div>
    </div>
</section>
