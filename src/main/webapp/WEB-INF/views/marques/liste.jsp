<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.MarquesDTO"%>
<%@page import="java.util.List"%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Marques</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<MarquesDTO> marques = (List<MarquesDTO>) request.getAttribute("marques");
                    if (marques != null && !marques.isEmpty()) {
                        for (MarquesDTO marque : marques) {
                %>
                    <tr>
                        <td><%= marque.getId() %></td>
                        <td><%= marque.getNomMarque() %></td>
                        <td>
                            <a href="/marques/edit/<%= marque.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/marques/delete/<%= marque.getId() %>" class="bi bi-trash" style="color: red;"></a>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="3" class="text-center">Aucune marque trouvée.</td>
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
