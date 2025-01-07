<%@page import="java.util.List, com.gestion.atelier.DTO.*"%>
<% 
    ReparationsDTO reparation = (ReparationsDTO) request.getAttribute("reparation");
    List<PiecesDetacheesDTO> pieces = (List<PiecesDetacheesDTO>) request.getAttribute("pieces");
%>

<form action="/reparationPieces/create/<%= reparation.getId() %>" method="post">
    <h3>Ajouter des pieces pour la reparation de l'ordinateur <b><%= reparation.getOrdinateur().getNumeroSerie() %></b></h3>
    <div class="mb-3">
        <label for="piece" class="form-label">Pieces :</label>
        <select id="piece" name="piece" class="form-control" required>
            <option value="">Sélectionner une piece</option>
            <% 
                for (PiecesDetacheesDTO piece : pieces) {
            %>
                <option value="<%= piece.getId() %>">
                    <%= piece.getNomPiece() %>
                </option>
            <% } %>
        </select>
    </div>

    <div class="mb-3">
        <label for="quantite" class="form-label">Quantite :</label>
        <input 
            type="number" 
            id="quantite" 
            name="quantite" 
            class="form-control" 
            required>
    </div>
    <button type="submit">Ajouter</button>
</form>
