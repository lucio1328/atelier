<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.ModelesDTO"%>
<%@page import="java.util.List"%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Modèles</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Marque</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<ModelesDTO> modeles = (List<ModelesDTO>) request.getAttribute("modeles");
                    if (modeles != null && !modeles.isEmpty()) {
                        for (ModelesDTO modele : modeles) {
                %>
                    <tr>
                        <td><%= modele.getId() %></td>
                        <td><%= modele.getNomModele() %></td>
                        <td><%= modele.getMarque().getNomMarque() %></td>
                        <td>
                            <a href="/modeles/edit/<%= modele.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/modeles/delete/<%= modele.getId() %>" class="bi bi-trash" style="color: red;"></a>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4" class="text-center">Aucun modèle trouvé.</td>
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
