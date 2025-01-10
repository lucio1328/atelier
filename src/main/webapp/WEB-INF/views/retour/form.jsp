<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="com.gestion.atelier.DTO.*"%>
<% 
    Long idRep = (Long) request.getAttribute("idRep"); 
    RetourDTO retour = (RetourDTO) request.getAttribute("retour");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= ("create".equals(request.getAttribute("action"))) ? "Creation de retours" : "Modification de retours" %></title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    <%= ("create".equals(request.getAttribute("action"))) ? "Creation de retours" : "Modification de retours" %>
                </h1>
            </div>

            <% 
                String errorMessage = (String) request.getAttribute("error");
                if (errorMessage != null) {
            %>
                <div class="alert alert-danger">
                    <%= errorMessage %>
                </div>
            <% } %>

            <form action="<% if("create".equals(request.getAttribute("action"))){
                    out.println("/retour/create");
                } 
                else {
                    out.println("/retour/edit/" + retour.getId());
                }%>" method="post">
            <div class="mb-3">

                <input 
                    type="hidden" 
                    id="idReparation" 
                    name="idReparation" 
                    value="<%= (retour != null) ? retour.getReparations().getId() : idRep %>"
                >
            </div>
            <div class="mb-3">
                <label for="dateRetour" class="form-label">Date retour :</label>
                <input 
                    type="date" 
                    id="dateRetour" 
                    name="dateRetour" 
                    class="form-control" 
                    value="<%= (retour != null) ? retour.getDateRetour() : "" %>" 
                >
            </div>
            <button type="submit" class="btn btn-primary">
                <%= ("create".equals(request.getAttribute("action"))) ? "Créer" : "Modifier" %>
            </button>
            </form>
        </div>
    </div>

</body>