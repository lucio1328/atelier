<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="com.gestion.atelier.utils.Formater"%>
<%@page import="java.util.List"%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <h5 class="card-title">Liste des Types</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Libelle</th>
                    <th scope="col">Commission</th>
                    <th scope="col">Action</th>

                </tr>
            </thead>
            <tbody>
                <% 
                    List<TypeReparationDTO> type = (List<TypeReparationDTO>) request.getAttribute("typeReparation");
                    if (type != null && !type.isEmpty()) {
                        for (TypeReparationDTO typee : type) {
                %>
                    <tr>
                        <td><%= typee.getId() %></td>
                        <td><%= typee.getLibelle()%></td>
                        <td><%= typee.getCommission()%></td>
                        <td>
                            <a href="/typeReparation/edit/<%= typee.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/typeReparation/create/<%= typee.getId() %>" class="bi bi-info-circle" style="color: blue;"></a>
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
