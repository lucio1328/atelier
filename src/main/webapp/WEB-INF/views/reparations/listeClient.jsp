<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>
<%
    List<ReparationsDTO> reparations = (List<ReparationsDTO>) request.getAttribute("reparations");
    String erreur = (String) request.getAttribute("erreur");
%>
<section class="section">
    <div class="row">
    <div class="col-lg-12">
        <div class="card">
            <div>
                <span style="color: red"><%= (erreur != null) ? erreur : "" %></span>
            </div>
            <div class="card-body">
                <form action="/reparations/rechercheDate" method="post">
                    <div class="mb-3">
                        <label for="dateMin" class="form-label">Date de reparation entre :</label>
                        <input
                            type="date"
                            id="dateMin"
                            name="dateMin"
                            class="form-control"
                        >
                    </div>
                    <div class="mb-3">
                        <label for="dateMax" class="form-label">Et:</label>
                        <input
                            type="date"
                            id="dateMax"
                            name="dateMax"
                            class="form-control"
                        >
                    </div>
                    <div></div>
                    <button type="submit" class="btn btn-primary">
                        Rechercher
                    </button>
                </form>
            </div>
        </div>

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Clients</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prenom</th>
                    <th scope="col">Date de naissance</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Email</th>
                    <th scope="col">Telephone</th>
                    <th scope="col">Adresse</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<ClientsDTO> clients = (List<ClientsDTO>) request.getAttribute("clients");
                    if (clients != null) {
                        for (ClientsDTO client : clients) {
                %>
                    <tr>
                        <td><%= client.getId() %></td>
                        <td><%= client.getNom() %></td>
                        <td><%= client.getPrenom() %></td>
                        <td><%= client.getDateNaissance() %></td>
                        <td><%= client.getGenre().getLibelle() %></td>
                        <td><%= client.getEmail() %></td>
                        <td><%= client.getTelephone() %></td>
                        <td><%= client.getAdresse() %></td>
                        <% for(ReparationsDTO reparation : reparations) {
                            if(reparation.getClient().getId() == client.getId()) {
                            %>
                            <td>
                                <a href="/reparations/details/<%= reparation.getId() %>">Details reparation</a>
                            </td>
                        <% } } %>
                    </tr>
                <%      }
                    }
                    else {
                %>
                    <tr>
                        <td colspan="4" class="text-center">Aucun client trouvé.</td>
                    </tr>
                <% } %>
            </tbody>
            </table>
            <!-- End Table with stripped rows -->

        </div>
        </div>
    </div>
    </div>
</section>