<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Achats</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Quantite</th>
                    <th scope="col">Quantite disponible</th>
                    <th scope="col">Prix unitaire</th>
                    <th scope="col">Date achat</th>
                    <th scope="col">Piece</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<AchatPiecesDTO> achats = (List<AchatPiecesDTO>) request.getAttribute("achats");
                    if (achats != null && !achats.isEmpty()) {
                        for (AchatPiecesDTO achat : achats) {
                %>
                    <tr>
                        <td><%= achat.getId() %></td>
                        <td><%= achat.getQuantite() %></td>
                        <td><%= achat.getQuantiteDisponible() %></td>
                        <td><%= achat.getPrixUnitaire() %></td>
                        <td><%= achat.getDateAchat() %></td>
                        <td><%= achat.getPieceDetachee().getNomPiece() %></td>
                        <td>
                            <a href="/achats/edit/<%= achat.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/achats/delete/<%= achat.getId() %>" class="bi bi-trash" style="color: red;"></a>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4" class="text-center">Aucun achat trouvé.</td>
                    </tr>
                <% } %>
            </tbody>
            </table>
            <!-- End Table with stripped rows -->

        </div>
        </div>

    </div>
    </div>
</section>
