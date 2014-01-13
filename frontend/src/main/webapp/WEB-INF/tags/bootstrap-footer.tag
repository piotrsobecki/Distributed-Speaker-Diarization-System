<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="list-inline">
                    <li><a href="<c:url value="/"/>"><spring:message code="home.nav"/></a></li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li><a href="<c:url value="/about"/>"><spring:message code="about.nav"/></a></li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li><a href="<c:url value="/support"/>"><spring:message code="support.nav"/></a></li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li><a href="<c:url value="/contact"/>"><spring:message code="contact.nav"/></a></li>
                </ul>
                <p class="copyright text-muted small">Copyright &copy; <a href="http://piotrsukiennik.pl">Piotr Sukiennik</a> 2013. All Rights Reserved</p>
            </div>
        </div>
    </div>
</footer>
