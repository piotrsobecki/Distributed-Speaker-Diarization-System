<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<c:set var="lang"><c:choose><c:when test="${param['lang'] == null}">en</c:when><c:when test="${param['lang'] != null}">${param['lang']}</c:when></c:choose></c:set>
<html lang="<c:out value="${lang}"/>">
<head>
    <mytags:meta title="WhoWhen"/>
    <mytags:bootstrap-head/>
</head>
<body>
<mytags:bootstrap-nav/>
<mytags:bootstrap-intro-header/>

<div class="content-section-a">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12">
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h2 class="section-heading">
                    <spring:message code="support.title"/>
                </h2>
                <p class="lead">
                    <spring:message code="support.text" htmlEscape="false"/>
                </p>
            </div>
        </div>
    </div><!-- /.container -->
</div><!-- /.content-section-a -->

<mytags:bootstrap-footer/>
<mytags:bootstrap-postbody/>
</body>
</html>
