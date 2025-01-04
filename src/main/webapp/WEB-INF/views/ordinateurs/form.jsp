<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>
<%
    OrdinateursDTO ordinateur = (OrdinateursDTO) request.getAttribute("ordinateur");
    List<ClientsDTO> clients = (List<ClientsDTO>) request.getAttribute("clients");
    List<ModelesDTO> modeles = (List<ModelesDTO>) request.getAttribute("modeles");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Création Ordinateur" : "Modification Ordinateur" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Création Nouvel Ordinateur" : "Modification Ordinateur" %>
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
                        out.println("/ordinateurs/create");
                    } 
                    else {
                        out.println("/ordinateurs/edit/" + ordinateur.getId());
                    }%>" 
                    method="post">
                    <div class="mb-3">
                        <label for="numeroSerie" class="form-label">Numero de serie :</label>
                        <input 
                            type="text" 
                            id="numeroSerie" 
                            name="numeroSerie" 
                            class="form-control" 
                            value="<%= (ordinateur != null) ? ordinateur.getNumeroSerie() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Description :</label>
                        <input 
                            type="text" 
                            id="description" 
                            name="description" 
                            class="form-control" 
                            value="<%= (ordinateur != null) ? ordinateur.getDescription() : "" %>" 
                            required>
                    </div>

                    <div class="mb-3">
                        <label for="client" class="form-label">Clients :</label>
                        <select id="client" name="client" class="form-control" required>
                            <option value="">Sélectionner un client</option>
                            <% 
                                for (ClientsDTO client : clients) {
                            %>
                                <option value="<%= client.getId() %>" <%= (ordinateur != null && ordinateur.getClient().getId().equals(client.getId())) ? "selected" : "" %> >
                                    <%= client.getNom() %> <%= client.getPrenom() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="modele" class="form-label">Modeles :</label>
                        <select id="modele" name="modele" class="form-control" required>
                            <option value="">Sélectionner un modele</option>
                            <% 
                                for (ModelesDTO modele : modeles) {
                            %>
                                <option value="<%= modele.getId() %>" <%= (ordinateur != null && ordinateur.getModele().getId().equals(modele.getId())) ? "selected" : "" %> >
                                    <%= modele.getMarque().getNomMarque() %> <%= modele.getNomModele() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
                    </button>
                    <a href="/ordinateurs/liste" class="btn btn-secondary">Retour à la liste</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
