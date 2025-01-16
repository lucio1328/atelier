<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.ComposantRecommandeDTO"%>
<%@page import="java.util.List"%>

<% List<String> mois = (List<String>) request.getAttribute("mois");
    List<Integer> annees = (List<Integer>) request.getAttribute("annees"); 
    String unMois = (String) request.getAttribute("unMois");
    String uneAnnee = (String) request.getAttribute("uneAnnee");%>
    

<section class="section">
    <div class="row">
    <div class="col-lg-12">

        <div class="card">
        <div class="card-body">
            <form action="/recommandes/recherche" method="post">
                        <label for="mois" class="form-label">Mois :</label>
                        <select id="mois" name="mois" class="form-control">
                            <option value="">Sélectionner un mois</option>
                            <% 
                                for (int i=0; i<mois.size(); i++) {
                            %>
                                <option value="<%= i+1 %>">
                                    <%= mois.get(i) %>
                                </option>
                            <% } %>
                        </select>

                        <label for="annee" class="form-label">Annee :</label>
                        <select id="annee" name="annee" class="form-control">
                            <option value="">Sélectionner une annee</option>
                            <% 
                                for (int j=0; j<annees.size(); j++) {
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
            <h5 class="card-title">Liste des composants recommandes <% if(unMois!=null && uneAnnee!=null){out.println("le "+unMois+" "+uneAnnee);}else{out.println("du mois");}%></h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Description</th>
                    <th scope="col">Nom piece</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<ComposantRecommandeDTO> pieces = (List<ComposantRecommandeDTO>) request.getAttribute("composants");
                    if (pieces != null && !pieces.isEmpty()) {
                        for (ComposantRecommandeDTO piece : pieces) {
                %>
                    <tr>
                        <td><%= piece.getId() %></td>
                        <td><%= piece.getPieceDetachee().getDescription() %></td>
                        <td><%= piece.getPieceDetachee().getNomPiece() %></td>
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
