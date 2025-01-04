<%@page import="java.util.List, com.gestion.atelier.DTO.*"%>
<% 
    TechniciensDTO technicien = (TechniciensDTO) request.getAttribute("technicien");
%>

<form action="/specialiteTechnicien/create/<%= technicien.getId() %>" method="post">
    <h3>Ajouter des specialites pour <b><%= technicien.getNom() %></b></h3>
    <div>
        <% 
            List<SpecialitesDTO> specialites = (List<SpecialitesDTO>) request.getAttribute("specialites");
            for (SpecialitesDTO specialite : specialites) {
        %>
            <div>
                <input type="checkbox" name="specialiteIds" value="<%= specialite.getId() %>" id="specialite-<%= specialite.getId() %>">
                <label for="specialite-<%= specialite.getId() %>"><%= specialite.getLibelle() %></label>
            </div>
        <% 
            }
        %>
    </div>
    <button type="submit">Ajouter</button>
</form>
