<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*, com.gestion.atelier.models.*"%>
<%@page import="java.util.*"%>

<%
    ReparationsDTO reparation = (ReparationsDTO) request.getAttribute("reparation");
    Map<PiecesDetachees, Integer> pieces = (Map<PiecesDetachees, Integer>) request.getAttribute("pieces");
%>

<section class="section">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">
                        Liste des pieces pour la reparation de l'ordinateur <b><%= reparation.getOrdinateur().getNumeroSerie() %></b>
                    </h5>
                    
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nom de la piece</th>
                                <th scope="col">Quantite</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if (pieces != null && !pieces.isEmpty()) {
                                    for (Map.Entry<PiecesDetachees, Integer> entry : pieces.entrySet()) {
                                        PiecesDetachees piece = entry.getKey();
                                        Integer quantite = entry.getValue();
                            %>
                                <tr>
                                    <td><%= piece.getId() %></td>
                                    <td><%= piece.getNomPiece() %></td>
                                    <td><%= quantite %></td>
                                </tr>
                            <%
                                    }
                                } else {
                            %>
                                <tr>
                                    <td colspan="2">Aucune piece trouvée.</td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
