<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="com.gestion.atelier.services.*"%>
<%@page import="com.gestion.atelier.utils.Formater"%>
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
                <form action="/reparations/rechercheDaty" method="post">
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
            <h5 class="card-title">Liste des commissions par technicien</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Technicien</th>
                    <th scope="col">Commission (Ar)</th>
                    <th scope="col">Date</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (reparations != null) {
                        for (ReparationsDTO reparation : reparations) {
                %>
                    <tr>
                        <td><%= reparation.getId() %></td>
                        <td><%= reparation.getTechnicien().getNom() %> <%= reparation.getTechnicien().getPrenom() %></td>
                        <td><%= TechniciensService.getPourcentage(reparation.getCommission()) %></td>
                        <td><%= Formater.dateEnLettres(reparation.getDateFin()) %></td>
                     
                    </tr>
                <%      }
                    }
                    else {
                %>
                    <tr>
                        <td colspan="4" class="text-center">Aucune reparation trouvée.</td>
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