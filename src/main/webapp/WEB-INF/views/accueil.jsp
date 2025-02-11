<%@page import="com.gestion.atelier.DTO.AdminsDTO"%>
<%
    AdminsDTO admin = (AdminsDTO) session.getAttribute("admin");
    String view = (String) request.getAttribute("view");
    if (view == null) {
        view = "home.jsp";
    }
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Atelier</title>
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

    <body>

        <!-- ======= Header ======= -->
        <header id="header" class="header fixed-top d-flex align-items-center">

            <div class="d-flex align-items-center justify-content-between">
                <a class="logo d-flex align-items-center">
                    <span><img src="/img/atelier.png"><span style="padding-bottom:-2px">Atelier</span></img></span>
                </a>
                <i class="bi bi-list toggle-sidebar-btn"></i>
            </div><!-- End Logo -->

            <!-- End Search Bar -->

            <nav class="header-nav ms-auto">
                <ul class="d-flex align-items-center">
                    <li class="nav-item dropdown pe-3">

                    <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                        <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle">
                        <span class="d-none d-md-block dropdown-toggle ps-2"><% if(admin != null) {
                                out.println(admin.getNom() + " " + admin.getPrenom());
                            } %>
                        </span>
                    </a><!-- End Profile Iamge Icon -->

                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                        <li class="dropdown-header">
                            <h6><% if(admin != null) {
                                out.println(admin.getNom() + " " + admin.getPrenom());
                            } %></h6>
                            <span>Admin</span>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>

                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                                <i class="bi bi-person"></i>
                                <span>My Profile</span>
                            </a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>

                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <i class="bi bi-gear"></i>
                                <span>Account Settings</span>
                            </a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>

                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <i class="bi bi-question-circle"></i>
                                <span>Need Help?</span>
                            </a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>

                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="/connection/deconnexion">
                                <i class="bi bi-box-arrow-right"></i>
                                <span>Sign Out</span>
                            </a>
                        </li>

                    </ul><!-- End Profile Dropdown Items -->
                    </li><!-- End Profile Nav -->

                </ul>
            </nav><!-- End Icons Navigation -->

        </header><!-- End Header -->

        <!-- ======= Sidebar ======= -->
        <aside id="sidebar" class="sidebar">

            <ul class="sidebar-nav" id="sidebar-nav">

                <li class="nav-item">
                    <a class="nav-link collapsed" href="/accueil">
                        <i class="bi bi-grid"></i>
                        Tableau de bord
                    </a>
                </li><!-- End Dashboard Nav -->

                <!-- Section Marques -->
                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#marques-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-tag"></i><span>Marques</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="marques-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/marques/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/marques/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Marques Nav -->

                <!-- Section Modèles -->
                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#modeles-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-box"></i><span>Modeles</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="modeles-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/modeles/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/modeles/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Modèles Nav -->

                <!-- Section Ordinateurs -->
                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#ordinateurs-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-laptop"></i><span>Ordinateurs</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="ordinateurs-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/ordinateurs/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/ordinateurs/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Ordinateurs Nav -->

                <!-- Section Specialites -->
                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#specialites-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-award"></i><span>Specialites</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="specialites-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/specialites/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/specialites/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Specialites Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#piece-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-pc-display"></i><span>Composant</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="piece-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/pieces/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/pieces/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                        <li>
                            <a href="/pieces/stock">
                                <i class="bi bi-circle"></i><span>Stock</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Composant Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#achat-piece-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-cart"></i><span>Achat Composant</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="achat-piece-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/achats/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/achats/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Composant Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#client-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-person"></i><span>Client</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="client-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/clients/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/clients/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Client Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#technicien-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-wrench"></i><span>Technicien</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="technicien-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/techniciens/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/techniciens/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Technicien Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#retour-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-wrench"></i><span>Retour</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="retour-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/retour/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Retour Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#mvtStock-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-arrow-down-up"></i><span>Mouvement stock</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="mvtStock-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/mouvements/historique">
                                <i class="ri-copper-diamond-fill"></i><span>Historique</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End mvt stock Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#reparation-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-gear"></i><span>Reparation</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="reparation-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/reparations/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/reparations/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                        <li>
                            <a href="/reparations/rechercheClient">
                                <i class="bi bi-circle"></i><span>Clients</span>
                            </a>
                        </li>
                        <li>
                            <a href="/reparations/rechercheCommission">
                                <i class="bi bi-circle"></i><span>Commission</span>
                            </a>
                        </li>
                    </ul>
                </li><!-- End Reparation Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#recommande-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-gear"></i><span>Recommande</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="recommande-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/recommandes/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/recommandes/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#typeReparation-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-gear"></i><span>Type Reparation</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="typeReparation-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="/typeReparation/create">
                                <i class="ri-copper-diamond-fill"></i><span>Insertion</span>
                            </a>
                        </li>
                        <li>
                            <a href="/typeReparation/liste">
                                <i class="bi bi-circle"></i><span>Liste</span>
                            </a>
                        </li>
                    </ul>
                </li>

            </ul>

        </aside><!-- End Sidebar -->

        <main id="main" class="main">

            <jsp:include page="<%=view%>"/>



        </main><!-- End #main -->

        <!-- ======= Footer ======= -->
        <footer id="footer" class="footer">
            <div class="copyright">
                &copy; Copyright <strong><span>Atelier de reparation</span></strong>. All Rights Reserved
            </div>
            <div class="credits">
                <!-- All the links in the footer should remain intact. -->
                <!-- You can delete the links only if you purchased the pro version. -->
                <!-- Licensing information: https://bootstrapmade.com/license/ -->
                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
                
            </div>
        </footer><!-- End Footer -->

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