<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.TechniciensDTO"%>
<%@page import="com.gestion.atelier.utils.Formater"%>
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
                        for (TechniciensDTO technicien : techniciens) {
                %>
                    <tr>
                        <td><%= technicien.getId() %></td>
                        <td><a href="/specialiteTechnicien/liste/<%= technicien.getId() %>"><%= technicien.getNom() %></a></td>
                        <td><a href="/specialiteTechnicien/liste/<%= technicien.getId() %>"><%= technicien.getPrenom() %></a></td>
                        <td><%= Formater.dateEnLettres(technicien.getDateNaissance()) %></td>
                        <td><%= technicien.getGenre().getLibelle() %></td>7
                        <td><%= technicien.getEmail() %></td>
                        <td><%= technicien.getMotDePasse() %></td>
                        <td>
                            <a href="/techniciens/edit/<%= technicien.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/techniciens/delete/<%= technicien.getId() %>" class="bi bi-trash" style="color: red; margin-right: 20px;"></a>
                            <a href="/specialiteTechnicien/create/<%= technicien.getId() %>" class="bi bi-info-circle" style="color: blue;"></a>
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
