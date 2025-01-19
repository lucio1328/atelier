<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@page import="com.gestion.atelier.DTO.MouvementsStockDTO"%>
<%@page import="com.gestion.atelier.utils.Formater"%>
<!DOCTYPE html>
<html>
<head>
    <title>Historique des Mouvements de Stock</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
</head>
<body>
<section class="section">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Historique de mouvement stock</h5>
                    <!-- Table with stripped rows -->
                    <table class="table datatable">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Quantité</th>
                            <th scope="col">Date Mouvement</th>
                            <th scope="col">Type Mouvement</th>
                            <th scope="col">Nom Pièce</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<MouvementsStockDTO> stocks = (List<MouvementsStockDTO>) request.getAttribute("stocks");
                            if (stocks != null) {
                                for (MouvementsStockDTO stock : stocks) {
                                    String nomPiece = "N/A";
                                    if (stock.getAchatPiece() != null && stock.getAchatPiece().getPieceDetachee() != null) {
                                        nomPiece = stock.getAchatPiece().getPieceDetachee().getNomPiece();
                                    }
                        %>
                        <tr>
                            <td><%= stock.getId() %></td>
                            <td><%= stock.getQuantite() %></td>
                            <td><%= Formater.dateEnLettres(stock.getDateMouvement()) %></td>
                            <td><%= stock.getTypeMouvement() != null ? stock.getTypeMouvement().getLibelle() : "N/A" %></td>
                            <td><%= nomPiece %></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
                    <!-- End Table with stripped rows -->
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
