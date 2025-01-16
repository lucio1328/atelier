<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>

<% 
    // Récupération de l'objet reparation passé par le contrôleur
    ReparationsDTO reparation = (ReparationsDTO) request.getAttribute("reparation");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de la Réparation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
        <h2>Détails de la Réparation - ID: <%= reparation.getId() %></h2>

        <!-- Affichage des détails de la réparation -->
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Description</h5>
                <p class="card-text"><%= reparation.getDescription() %></p>
                
                <h5 class="card-title">Date Début</h5>
                <p class="card-text"><%= reparation.getDateDebut() %></p>

                <h5 class="card-title">Date Fin</h5>
                <p class="card-text"><%= reparation.getDateFin() %></p>

                <h5 class="card-title">Technicien</h5>
                <p class="card-text"><%= reparation.getTechnicien().getNom() %> <%= reparation.getTechnicien().getPrenom() %></p>

                <h5 class="card-title">Client</h5>
                <p class="card-text"><%= reparation.getClient().getNom() %> <%= reparation.getClient().getPrenom() %></p>

                <h5 class="card-title">Ordinateur</h5>
                <p class="card-text"><%= reparation.getOrdinateur().getModele().getNomModele() %></p>

                <h5 class="card-title">Statut</h5>
                <p class="card-text"><%= reparation.getStatut().getLibelle() %></p>
            </div>
        </div>

        <div class="mt-3">
            <a href="/reparations/edit/<%= reparation.getId() %>" class="btn btn-warning">Modifier</a>
            <a href="/reparations/liste" class="btn btn-secondary">Retour à la liste</a>
        </div>
    </div>

</body>
</html>
