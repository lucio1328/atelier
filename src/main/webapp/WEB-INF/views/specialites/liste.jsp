<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gestion.atelier.DTO.SpecialitesDTO,java.util.List" %>
<% List<SpecialitesDTO> specialites = (List<SpecialitesDTO>) request.getAttribute("specialites"); %>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Spécialités</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Libellé</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% if (specialites != null && !specialites.isEmpty()) {
                    for (SpecialitesDTO specialite : specialites) { %>
                        <tr>
                            <td><%= specialite.getId() %></td>
                            <td><%= specialite.getLibelle() %></td>
                            <td>
                                <a href="<%= "/specialites/edit/" + specialite.getId() %>" class="btn btn-sm btn-warning">Modifier</a> |
                                <a href="<%= "/specialites/delete/" + specialite.getId() %>" class="btn btn-sm btn-danger" 
                                    onclick="return confirm('Voulez-vous vraiment supprimer cette spécialité ?')">Supprimer</a>
                            </td>
                        </tr>
                    <% } 
                    } %>
            </tbody>
            </table>
            <!-- End Table with stripped rows -->

        </div>
        </div>

    </div>
    </div>
</section>
