<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.ClientsDTO"%>
<%@page import="java.util.List"%>
<%
    List<ClientsDTO> rechercheClients = (List<ClientsDTO>) request.getAttribute("rechercheClients");
%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">
        <div class="card">
            <div class="card-body">
                <form action="/clients/recherche" method="post">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom :</label>
                        <input
                            type="text"
                            id="nom"
                            name="nom"
                            class="form-control"
                        >
                    </div>
                    <div class="mb-3">
                        <label for="prenom" class="form-label">Prenom :</label>
                        <input
                            type="text"
                            id="prenom"
                            name="prenom"
                            class="form-control"
                        >
                    </div>
                    <div class="mb-3">
                        <label for="dateDebut" class="form-label">Date de naissance entre :</label>
                        <input
                            type="date"
                            id="dateDebut"
                            name="dateDebut"
                            class="form-control"
                        >
                    </div>
                    <div></div>
                    <div class="mb-3">
                        <label for="dateFin" class="form-label">Et :</label>
                        <input
                            type="date"
                            id="dateFin"
                            name="dateFin"
                            class="form-control"
                        >
                    </div>
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
                    if (clients != null && rechercheClients == null) {
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
                        <td>
                            <a href="/clients/edit/<%= client.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/clients/delete/<%= client.getId() %>" class="bi bi-trash" style="color: red;"></a>
                        </td>
                    </tr>
                <%      }
                    }
                    else if(clients == null && rechercheClients != null) {
                        for (ClientsDTO rechercheClient : rechercheClients) {
                %>
                        <tr>
                            <td><%= rechercheClient.getId() %></td>
                            <td><%= rechercheClient.getNom() %></td>
                            <td><%= rechercheClient.getPrenom() %></td>
                            <td><%= rechercheClient.getDateNaissance() %></td>
                            <td><%= rechercheClient.getGenre().getLibelle() %></td>
                            <td><%= rechercheClient.getEmail() %></td>
                            <td><%= rechercheClient.getTelephone() %></td>
                            <td><%= rechercheClient.getAdresse() %></td>
                            <td>
                                <a href="/clients/edit/<%= rechercheClient.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                                <a href="/clients/delete/<%= rechercheClient.getId() %>" class="bi bi-trash" style="color: red;"></a>
                            </td>
                        </tr>
                <%    }
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
