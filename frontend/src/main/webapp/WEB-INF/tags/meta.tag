<%@ attribute name="title" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<title><c:out value="${title}"/></title>
<meta charset="utf-8">
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
<meta name="author" content="Piotr Sukiennik"/>
<meta name="description" content="<spring:message code="front.meta.description"/>"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel=”author” href=”https://plus.google.com/u/0/115450018004994842048“/>
<meta property=”og:title” content="<c:out value="${title}"/>"/>
<meta property=”og:description” content="<spring:message code="front.meta.description"/>"/>
<meta property="og:type" content="website"/>
<meta property=”og:image” content=”<c:url value="/resources/img/whowhen_logo.gif"/>”/>
<meta property=”fb:admins” content="100000023032989"/>
