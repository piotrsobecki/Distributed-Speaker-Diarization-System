<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only"><spring:message code="nav.toggle"/></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">WhoWhen</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/"/>"><spring:message code="home.nav"/></a></li>
                <li><a href="<c:url value="/about"/>"><spring:message code="about.nav"/></a></li>
                <li><a href="<c:url value="/support"/>"><spring:message code="support.nav"/></a></li>
                <li><a href="<c:url value="/contact"/>"><spring:message code="contact.nav"/></a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="">
                        <spring:message code="front.step0.language.title"/>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <li><a href="<c:url value=""><c:param name="lang" value="en"/></c:url>"><spring:message code="front.step0.languages.en"/></a></li>
                        <li><a href="<c:url value=""><c:param name="lang" value="pl"/></c:url>"><spring:message code="front.step0.languages.pl"/></a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav>
