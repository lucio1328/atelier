<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="java.util.List"%>

<%
    TechniciensDTO technicien = (TechniciensDTO) request.getAttribute("technicien");
    List<SpecialitesDTO> specialites = (List<SpecialitesDTO>) request.getAttribute("specialites");
%>

<section class="section">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">
                        Liste des Specialites pour le technicien <b><%= technicien.getNom() %> <%= technicien.getPrenom() %></b>
                    </h5>
                    
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nom de la spécialité</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if (specialites != null && !specialites.isEmpty()) {
                                    for (SpecialitesDTO specialite : specialites) {
                            %>
                                <tr>
                                    <td><%= specialite.getId() %></td>
                                    <td><%= specialite.getLibelle() %></td>
                                </tr>
                            <%
                                    }
                                } else {
                            %>
                                <tr>
                                    <td colspan="2">Aucune spécialité trouvée.</td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
