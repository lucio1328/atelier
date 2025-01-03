<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.TechniciensDTO"%>
<%@page import="java.util.List"%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Techniciens</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prenom</th>
                    <th scope="col">Date de naissance</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Email</th>
                    <th scope="col">Mot de passe</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<TechniciensDTO> techniciens = (List<TechniciensDTO>) request.getAttribute("techniciens");
                    if (techniciens != null && !techniciens.isEmpty()) {
                        for (TechniciensDTO client : techniciens) {
                %>
                    <tr>
                        <td><%= client.getId() %></td>
                        <td><%= client.getNom() %></td>
                        <td><%= client.getPrenom() %></td>
                        <td><%= client.getDateNaissance() %></td>
                        <td><%= client.getGenre().getLibelle() %></td>
                        <td><%= client.getEmail() %></td>
                        <td><%= client.getMotDePasse() %></td>
                        <td>
                            <a href="/techniciens/edit/<%= client.getId() %>" class="btn btn-sm btn-warning">Modifier</a>
                            <a href="/techniciens/delete/<%= client.getId() %>" class="btn btn-sm btn-danger">Supprimer</a>
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
