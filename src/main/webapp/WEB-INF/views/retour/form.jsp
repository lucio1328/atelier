<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Long idRep = (Long) request.getAttribute("idRep"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Creation de retours</title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h1 class="h3 mb-0">
                    Creation de retours
                </h1>
            </div>

            <form action="/retour/create" method="post">
            <div class="mb-3">

                <input 
                    type="hidden" 
                    id="idReparation" 
                    name="idReparation" 
                    value="<%= idRep %>"
                >
            </div>
            <div class="mb-3">
                <label for="dateRetour" class="form-label">Date retour :</label>
                <input 
                    type="date" 
                    id="dateRetour" 
                    name="dateRetour" 
                    class="form-control"  
                >
            </div>
            <button type="submit" class="btn btn-primary">
                Creer
            </button>
            </form>
        </div>
    </div>

</body>