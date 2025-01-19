<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gestion.atelier.DTO.*"%>
<%@page import="com.gestion.atelier.utils.Formater"%>
<%@page import="java.util.List"%>

<% 
    List<TypeReparationDTO> typeReparations = (List<TypeReparationDTO>) request.getAttribute("typeReparations"); 
    List<CategoriesDTO> categories = (List<CategoriesDTO>) request.getAttribute("categories");
    List<RetourDTO> resultats = (List<RetourDTO>) request.getAttribute("resultatsRecherche");
%>

<section class="section">
    <div class="row">
    <div class="col-lg-12">

         <div class="card">
        <div class="card-body">
             <div class="mb-3">
                <form action="/retour/recherche" method="post">
                        <label for="typeReparation" class="form-label">Type de reparation :</label>
                        <select id="typeReparation" name="typeReparation" class="form-control">
                            <option value="">Sélectionner un type</option>
                            <% 
                                for (TypeReparationDTO typeReparation : typeReparations) {
                            %>
                                <option value="<%= typeReparation.getId() %>">
                                    <%= typeReparation.getLibelle() %>
                                </option>
                            <% } %>
                        </select>

                        <label for="categorie" class="form-label">Type de categories :</label>
                        <select id="categorie" name="categorie" class="form-control">
                            <option value="">Sélectionner un type</option>
                            <% 
                                for (CategoriesDTO categorie : categories) {
                            %>
                                <option value="<%= categorie.getId() %>">
                                    <%= categorie.getLibelle() %>
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
            <h5 class="card-title">Liste des Retour</h5>

            <!-- Table with stripped rows -->
            <table class="table datatable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Ordinateur</th>
                    <th scope="col">Categorie</th>
                    <th scope="col">Type de reparation</th>
                    <th scope="col">Date Retour</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<RetourDTO> retours = (List<RetourDTO>) request.getAttribute("retours");
                    if (retours != null && !retours.isEmpty()) {
                        for (RetourDTO retour : retours) {
                %>
                    <tr>
                        <td><%= retour.getId() %></td>
                        <td><%= retour.getReparations().getOrdinateur().getModele().getMarque().getNomMarque() %> <%= retour.getReparations().getOrdinateur().getModele().getNomModele() %></td>
                        <td><%= (retour.getReparations().getOrdinateur().getModele().getCategorie() != null) ? retour.getReparations().getOrdinateur().getModele().getCategorie().getLibelle() : " " %></td>
                        <td><%= retour.getReparations().getTypeReparation().getLibelle() %></td>
                        <td><%= Formater.dateEnLettres(retour.getDateRetour()) %></td>
                        <td>
                            <a href="/retour/edit/<%= retour.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/retour/delete/<%= retour.getId() %>" class="bi bi-trash" style="color: red;"></a>
                        </td>
                    </tr>
                <% 
                        }
                    } else if (resultats !=null){
                         for (RetourDTO resultat : resultats) {
                %>
                    <tr>
                        <td><%= resultat.getId() %></td>
                        <td><%= resultat.getReparations().getOrdinateur().getModele().getMarque().getNomMarque() %> <%= resultat.getReparations().getOrdinateur().getModele().getNomModele() %></td>
                        <td><%= (resultat.getReparations().getOrdinateur().getModele().getCategorie() != null) ? resultat.getReparations().getOrdinateur().getModele().getCategorie().getLibelle() : " " %></td>
                        <td><%= resultat.getReparations().getTypeReparation().getLibelle() %></td>
                        <td><%= resultat.getDateRetour() %></td>
                        <td>
                            <a href="/retour/edit/<%= resultat.getId() %>" class="bi bi-pencil-square" style="color: green; margin-right: 20px;"></a>
                            <a href="/retour/delete/<%= resultat.getId() %>" class="bi bi-trash" style="color: red;"></a>
                        </td>
                    </tr>
                <% 
                        }} %>
            </tbody>
            </table>
            <!-- End Table with stripped rows -->

        </div>
        </div>

    </div>
    </div>
</section>
