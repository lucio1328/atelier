<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>
<%
    List<StatutDTO> statuts = (List<StatutDTO>) request.getAttribute("statuts");
    List<OrdinateursDTO> ordinateurs = (List<OrdinateursDTO>) request.getAttribute("ordinateurs");
    List<TechniciensDTO> techniciens = (List<TechniciensDTO>) request.getAttribute("techniciens");
    ReparationsDTO reparation = (ReparationsDTO) request.getAttribute("reparation");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Reparation" : "Modification Reparation" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouvelle Reparation" : "Modification Reparation" %>
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
                        out.println("/reparations/create");
                    } 
                    else {
                        out.println("/reparations/edit/" + reparation.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="description" class="form-label">Description :</label>
                        <input 
                            type="text" 
                            id="description" 
                            name="description" 
                            class="form-control" 
                            value="<%= (reparation != null) ? reparation.getDescription() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="dateDebut" class="form-label">Date debut :</label>
                        <input 
                            type="date" 
                            id="dateDebut" 
                            name="dateDebut" 
                            class="form-control" 
                            value="<%= (reparation != null) ? reparation.getDateDebut() : "" %>" 
                            >
                    </div>
                    <% if(reparation != null) { %>
                        <div class="mb-3">
                            <label for="dateFin" class="form-label">Date fin :</label>
                            <input 
                                type="date" 
                                id="dateFin" 
                                name="dateFin" 
                                class="form-control"  
                                >
                        </div>
                    <% } %>

                    <div class="mb-3">
                        <label for="technicien" class="form-label">Techniciens :</label>
                        <select id="technicien" name="technicien" class="form-control" required>
                            <option value="">Sélectionner un technicien</option>
                            <% 
                                for (TechniciensDTO technicien : techniciens) {
                            %>
                                <option value="<%= technicien.getId() %>" <%= (reparation != null && reparation.getTechnicien().getId().equals(technicien.getId())) ? "selected" : "" %> >
                                    <%= technicien.getNom() %> <%= technicien.getPrenom() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="ordinateur" class="form-label">Ordinateurs :</label>
                        <select id="ordinateur" name="ordinateur" class="form-control" required>
                            <option value="">Sélectionner un ordinateur</option>
                            <% 
                                for (OrdinateursDTO ordinateur : ordinateurs) {
                            %>
                                <option value="<%= ordinateur.getId() %>" <%= (reparation != null && reparation.getOrdinateur().getId().equals(ordinateur.getId())) ? "selected" : "" %> >
                                    <%= ordinateur.getNumeroSerie() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="statut" class="form-label">Statuts :</label>
                        <select id="statut" name="statut" class="form-control" required>
                            <option value="">Sélectionner un statut</option>
                            <% 
                                for (StatutDTO statut : statuts) {
                            %>
                                <option value="<%= statut.getId() %>" <%= (reparation != null && reparation.getStatut().getId().equals(statut.getId())) ? "selected" : "" %> >
                                    <%= statut.getLibelle() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/reparations/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
