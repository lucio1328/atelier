<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>
<%
    AchatPiecesDTO achat = (AchatPiecesDTO) request.getAttribute("achat");
    List<PiecesDetacheesDTO> pieces = (List<PiecesDetacheesDTO>) request.getAttribute("pieces");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Achat" : "Modification Achat" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouvel Achat" : "Modification Achat" %>
                </h1>
            </div>
            <div class="card-body">
                <% 
                    String errorMessage = (String) request.getAttribute("error");
                    if (errorMessage != null) {
                %>
                    <div class="alert alert-danger">
                        <%= errorMessage %>
                    </div>
                <% } %>

                <form action="<% if("create".equals(request.getAttribute("action"))){
                        out.println("/achats/create");
                    } 
                    else {
                        out.println("/achats/edit/" + achat.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="quantite" class="form-label">Quantite :</label>
                        <input 
                            type="number" 
                            id="quantite" 
                            name="quantite" 
                            class="form-control" 
                            value="<%= (achat != null) ? achat.getQuantite() : "" %>" 
                            required>
                    </div>

                    <% if(achat != null) {%>
                        <div class="mb-3">
                            <label for="quantiteDisponible" class="form-label">Quantite disponible :</label>
                            <input 
                                type="number" 
                                id="quantiteDisponible" 
                                name="quantiteDisponible" 
                                class="form-control" 
                                value="<%= (achat != null) ? achat.getQuantiteDisponible() : "" %>" 
                                required>
                        </div>
                    <% } %>

                    <div class="mb-3">
                        <label for="prixUnitaire" class="form-label">Prix unitaire :</label>
                        <input 
                            type="text" 
                            id="prixUnitaire" 
                            name="prixUnitaire" 
                            class="form-control" 
                            value="<%= (achat != null) ? achat.getPrixUnitaire() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="dateAchat" class="form-label">Date achat :</label>
                        <input 
                            type="date" 
                            id="dateAchat" 
                            name="dateAchat" 
                            class="form-control" 
                            value="<%= (achat != null) ? achat.getDateAchat() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="pieceDetachee" class="form-label">Pieces :</label>
                        <select id="pieceDetachee" name="pieceDetachee" class="form-control" required>
                            <option value="">Sélectionner une piece</option>
                            <% 
                                for (PiecesDetacheesDTO piece : pieces) {
                            %>
                                <option value="<%= piece.getId() %>" <%= (achat != null && achat.getPieceDetachee().getId().equals(piece.getId())) ? "selected" : "" %> >
                                    <%= piece.getNomPiece() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/achats/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
