<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>

<% List<TypeReparationDTO> typeReparations = (List<TypeReparationDTO>) request.getAttribute("typeReparation"); 
    List<ReparationsDTO> reparations = (List<ReparationsDTO>) request.getAttribute("reparations");
    List<ReparationsDTO> recherche = (List<ReparationsDTO>) request.getAttribute("recherche");
%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

         <div class="card">
        <div class="card-body">
             <div class="mb-3">
                <form action="/reparations/recherche" method="post">
                        <label for="typeReparation" class="form-label">Type de reparation :</label>
                        <select id="typeReparation" name="typeReparation" class="form-control" required>
                            <option value="">Sélectionner un type</option>
                            <% 
                                for (TypeReparationDTO typeReparation : typeReparations) {
                            %>
                                <option value="<%= typeReparation.getId() %>">
                                    <%= typeReparation.getLibelle() %>
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        OK
                    </button>
                </form>
        </div>
        </div>

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Reparations</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Description</th>
                    <th scope="col">Date debut</th>
                    <th scope="col">Date fin</th>
                    <th scope="col">Technicien</th>
                    <th scope="col">Client</th>
                    <th scope="col">Ordinateur</th>
                    <th scope="col">Statut</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (reparations != null && !reparations.isEmpty()) {
                        for (ReparationsDTO reparation : reparations) {
                %>
                    <tr>
                        <td><%= reparation.getId() %></td>
                        <td><%= reparation.getDescription() %></td>
                        <td><%= reparation.getDateDebut() %></td>
                        <td><%= reparation.getDateFin() %></td>
                        <td><%= reparation.getTechnicien().getNom() %> <%= reparation.getTechnicien().getPrenom() %></td>
                        <td><%= reparation.getClient().getNom() %> <%= reparation.getClient().getPrenom() %></td>
                        <td><%= reparation.getOrdinateur().getNumeroSerie() %></td>
                        <td><%= reparation.getStatut().getLibelle() %></td>
                        <td>
                            <a href="/reparations/edit/<%= reparation.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/reparations/delete/<%= reparation.getId() %>" class="bi bi-trash" style="color: red; margin-right: 20px;"></a>
                            <a href="/reparationPieces/create/<%= reparation.getId() %>" class="bi bi-info-circle" style="color: blue;"></a>
                        </td>
                    </tr>
                <% 
                        }
                    }
                    else if(recherche != null) {
                    for (ReparationsDTO r : recherche) {
                %>
                <tr>
                    <td><%= r.getId() %></td>
                    <td><%= r.getDescription() %></td>
                    <td><%= r.getDateDebut() %></td>
                    <td><%= r.getDateFin() %></td>
                    <td><%= r.getTechnicien().getNom() %> <%= r.getTechnicien().getPrenom() %></td>
                    <td><%= r.getClient().getNom() %> <%= r.getClient().getPrenom() %></td>
                    <td><%= r.getOrdinateur().getNumeroSerie() %></td>
                    <td><%= r.getStatut().getLibelle() %></td>
                    <td>
                        <a href="/reparations/edit/<%= r.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                        <a href="/reparations/delete/<%= r.getId() %>" class="bi bi-trash" style="color: red; margin-right: 20px;"></a>
                        <a href="/reparationPieces/create/<%= r.getId() %>" class="bi bi-info-circle" style="color: blue;"></a>
                    </td>
                </tr>

                <%
                        }}
                %>
            </tbody>
            </table>
            <!-- End Table with stripped rows -->

        </div>
        </div>

    </div>
    </div>
</section>