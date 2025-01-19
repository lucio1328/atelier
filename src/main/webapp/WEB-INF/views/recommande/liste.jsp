<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.ComposantRecommandeDTO"%>
<%@page import="com.gestion.atelier.utils.Formater"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Map" %>

<%
    List<String> mois = (List<String>) request.getAttribute("mois");
    List<Integer> annees = (List<Integer>) request.getAttribute("annees"); 
    String unMois = (String) request.getAttribute("unMois");
    String uneAnnee = (String) request.getAttribute("uneAnnee");
%>
    

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <form action="/recommandes/recherche" method="post">
                <div>
                    <label for="mois" class="form-label">Mois :</label>
                    <select id="mois" name="mois" class="form-control">
                        <option value="">Tous</option>
                        <%
                            for (int i=0; i < mois.size(); i++) {
                        %>
                        <option value="<%= i+1 %>">
                                <%= mois.get(i) %>
                            </option>
                        <% } %>
                    </select>

                    <label for="annee" class="form-label">Annee :</label>
                    <select id="annee" name="annee" class="form-control">
                        <option value="">Tous</option>
                        <%
                            for (int j=0; j < annees.size(); j++) {
                        %>
                            <option value="<%= annees.get(j) %>">
                                <%= annees.get(j) %>
                            </option>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">
                    OK
                </button>
            </form>
        </div>
        </div>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">
                <%
                    if (unMois != null && uneAnnee != null) {
                        if (!unMois.isEmpty() && !uneAnnee.isEmpty()) {
                            out.println("Liste des composants recommandés pour " + unMois + " " + uneAnnee);
                        }
                        else if (!unMois.isEmpty()) {
                            out.println("Liste des composants recommandés pour le mois de : " + unMois);
                        }
                        else if (!uneAnnee.isEmpty()) {
                            out.println("Liste des composants recommandés pour l'année : " + uneAnnee);
                        }
                        else {
                            out.println("Liste de tous les composants recommandés");
                        }
                    }
                    else {
                        out.println("Liste des composants recommandés du mois");
                    }
                %>
            </h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">ID</th>
                    <th scope="col">Description</th>
                    <th scope="col">Nom piece</th>
                    <th scope="col">Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<ComposantRecommandeDTO> pieces = (List<ComposantRecommandeDTO>) request.getAttribute("composants");
                    if ((unMois == null || unMois.isEmpty()) && uneAnnee != null && !uneAnnee.isEmpty()) {
                        Map<String, List<ComposantRecommandeDTO>> groupedByMonth = (Map<String, List<ComposantRecommandeDTO>>) request.getAttribute("groupedByMonth");
                        if (groupedByMonth != null) {
                            for (String month : groupedByMonth.keySet()) {
                        %>
                                <tr>
                                    <td colspan="4" class="font-weight-bold"><%= month %></td>
                                </tr>
                                <%
                                    for (ComposantRecommandeDTO piece : groupedByMonth.get(month)) {
                                %>
                                <tr>
                                    <td></td>
                                    <td><%= piece.getId() %></td>
                                    <td><%= piece.getPieceDetachee().getDescription() %></td>
                                    <td><%= piece.getPieceDetachee().getNomPiece() %></td>
                                    <td><%= Formater.dateEnLettres(piece.getDate()) %></td>
                                </tr>
                        <%
                                }
                            }
                        }
                    }
                    else {
                %>
                <%-- Logic for normal display when not grouping --%>
                <% if (pieces != null && !pieces.isEmpty()) {
                    for (ComposantRecommandeDTO piece : pieces) { %>
                        <tr>
                            <td></td>
                            <td><%= piece.getId() %></td>
                            <td><%= piece.getPieceDetachee().getDescription() %></td>
                            <td><%= piece.getPieceDetachee().getNomPiece() %></td>
                            <td><%= Formater.dateEnLettres(piece.getDate()) %></td>
                        </tr>
                    <% } } %>
                <% } %>
            </tbody>
            </table>
            <!-- End Table with stripped rows -->

        </div>
        </div>

    </div>
    </div>
</section>
