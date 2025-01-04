<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Connection</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="/img/favicon.png" rel="icon">
  <link href="/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jul 27 2023 with Bootstrap v5.3.1
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
<style>
    /* Fond général */
    body {
        background-color: #f4f6f9; /* Couleur de fond douce */
        font-family: 'Poppins', sans-serif;
        margin: 0;
        padding: 0;
        height: 100vh;
    }

    /* Section d'inscription avec dégradé */
    .section.register {
        background: linear-gradient(to bottom right, #4e73df, #224abe); /* Gradient doux */
        color: #fff;
        min-height: 100vh; /* Hauteur pleine de l'écran */
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px;
    }

    /* Carte de connexion avec ombre et bordure arrondie */
    .card {
        border-radius: 12px;
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1); /* Ombre plus marquée */
        padding: 30px;
        width: 100%;
        max-width: 450px; /* Limite la taille de la carte */
        background: #fff; /* Fond de la carte */
        border: 1px solid #ddd; /* Bordure claire autour de la carte */
    }

    /* Logo */
    .logo img {
        max-height: 50px;
        margin-bottom: 20px;
    }

    /* Formulaire et champs de texte */
    .form-label {
        font-weight: bold;
        margin-bottom: 10px;
    }

    .form-control {
        border-radius: 10px;
        padding: 12px;
        font-size: 1rem;
        border: 1px solid #ccc;
        transition: border 0.3s, box-shadow 0.3s;
    }

    /* Effet de focus sur les champs */
    .form-control:focus {
        border-color: #4e73df;
        box-shadow: 0 0 5px rgba(78, 115, 223, 0.5); /* Légère ombre bleue lors du focus */
        outline: none;
    }

    /* Bouton de soumission */
    .btn-primary {
        background-color: #007bff;
        border-color: #007bff;
        transition: background-color 0.3s;
        width: 100%;
        padding: 12px;
        border-radius: 8px;
    }

    .btn-primary:hover {
        background-color: #0056b3;
    }

    /* Message d'erreur */
    .alert-danger {
        margin-top: 15px;
        font-size: 1rem;
        border-radius: 8px;
    }

    /* Liens en bas */
    .footer-link {
        text-align: center;
        margin-top: 20px;
    }

    .footer-link a {
        color: #ffffff;
        text-decoration: none;
    }

    .footer-link a:hover {
        text-decoration: underline;
    }

    /* Réduire la taille de la carte sur mobile */
    @media (max-width: 576px) {
        .card {
            width: 90%;
        }
    }
</style>

<body>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center py-4">
                <a href="/index" class="logo d-flex align-items-center w-auto">
                  <img src="/img/atelier.png" alt="">
                  <span class="d-none d-lg-block">Atelier de reparation d'ordinateur</span>
                </a>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">Se connecter a votre compte</h5>
                    <p class="text-center small">Entrer votre email & mot de passe pour se connecter</p>
                  </div>
                  </div>

                  <% 
                     String errorMessage = (String) request.getAttribute("error");
                     if (errorMessage != null) { 
                  %>
                  <div class="alert alert-danger">
                    <%= errorMessage %>
                  </div>
                  <% } %>
                  <form class="row g-3 needs-validation" action="/connection/verification" method="post">

                    <div class="col-12">
                      <label for="yourUsername" class="form-label">Email</label>
                      <div class="input-group has-validation">
                        <span class="input-group-text" id="inputGroupPrepend">@</span>
                        <input type="text" name="email" class="form-control" id="yourUsername" required>
                        <div class="invalid-feedback">L'email est obligatoire.</div>
                      </div>
                    </div>

                    <div class="col-12">
                      <label for="yourPassword" class="form-label">Password</label>
                      <input type="password" name="mdp" class="form-control" id="yourPassword" required>
                      <div class="invalid-feedback">Le mot de passe est obligatoire!</div>
                    </div>

                    <div class="col-12">
                      <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="remember" value="true" id="rememberMe">
                        <label class="form-check-label" for="rememberMe">Remember me</label>
                      </div>
                    </div>
                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="submit">Se connecter</button>
                    </div>
                    <%-- <div class="col-12">
                      <p class="small mb-0">Don't have account? <a href="pages-register.html">Create an account</a></p>
                    </div> --%>
                  </form>

                </div>
              </div>

            </div>
          </div>
        </div>
        <div>
          <a href="/connection/inscription">Creer un compte admin</a>
        </div>

      </section>

    </div>
  </main><!-- End #main -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="/vendor/chart.js/chart.umd.js"></script>
  <script src="/vendor/echarts/echarts.min.js"></script>
  <script src="/vendor/quill/quill.min.js"></script>
  <script src="/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="/vendor/tinymce/tinymce.min.js"></script>
  <script src="/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="/js/main.js"></script>

</body>

</html>