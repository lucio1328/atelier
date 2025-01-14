<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>

<% List<PiecesDetacheesDTO> pieces = (List<PiecesDetacheesDTO>) request.getAttribute("pieces"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Pieces Recommandees</title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    Inserer une piece recommandee
                </h1>
            </div>

            <form action="/recommandes/create" method="post">
            <div class="form-group"><div class="mb-3">
                <label for="pieceDetachee" class="form-label">Pieces :</label>
                <select id="pieceDetachee" name="pieceDetachee" class="form-control" required>
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
                <label for="date" class="form-label">Date :</label>
                <input 
                    type="date" 
                    id="date" 
                    name="date" 
                    class="form-control"  
                    required>
            </div>

            <button type="submit" class="btn btn-primary">Inserer</button>

            </form>
        </div>
    </div>
</body>
</html>