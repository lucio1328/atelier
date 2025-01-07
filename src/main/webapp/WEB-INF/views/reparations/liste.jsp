<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.ReparationsDTO"%>
<%@page import="java.util.List"%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

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
                    List<ReparationsDTO> reparations = (List<ReparationsDTO>) request.getAttribute("reparations");
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
                    } %>
            </tbody>
            </table>
            <!-- End Table with stripped rows -->

        </div>
        </div>

    </div>
    </div>
</section>
