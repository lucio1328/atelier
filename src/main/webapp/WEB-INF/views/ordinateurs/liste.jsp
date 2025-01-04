<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Ordinateurs</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Numero de serie</th>
                    <th scope="col">Description</th>
                    <th scope="col">Client</th>
                    <th scope="col">Modele</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<OrdinateursDTO> ordinateurs = (List<OrdinateursDTO>) request.getAttribute("ordinateurs");
                    if (ordinateurs != null && !ordinateurs.isEmpty()) {
                        for (OrdinateursDTO ordinateur : ordinateurs) {
                %>
                    <tr>
                        <td><%= ordinateur.getId() %></td>
                        <td><%= ordinateur.getNumeroSerie() %></td>
                        <td><%= ordinateur.getDescription() %></td>
                        <td><%= ordinateur.getClient().getNom() %> <%= ordinateur.getClient().getPrenom() %></td>
                        <td><%= ordinateur.getModele().getNomModele() %></td>
                        <td>
                            <a href="/ordinateurs/edit/<%= ordinateur.getId() %>" class="btn btn-sm btn-warning">Modifier</a>
                            <a href="/ordinateurs/delete/<%= ordinateur.getId() %>" class="btn btn-sm btn-danger">Supprimer</a>
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
