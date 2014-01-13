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

<%--http://data.${domainPath}/?redirect=http://${servletPath}--%>

<form:form commandName="FORM" id="whoWhenRequestForm" enctype="multipart/form-data" action="http://localhost:8280/datasubmit/?redirect=http://${servletPath}" method="POST">

    <div class="content-section-a">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-sm-12">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">
                        <spring:message code="front.info.title"/>
                    </h2>
                    <p class="lead">
                        <spring:message code="front.info.text" htmlEscape="false"/>
                    </p>
                </div>
            </div>
        </div><!-- /.container -->
    </div><!-- /.content-section-a Info -->
<div class="content-section-a">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12">
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h2 class="section-heading">
                    <spring:message code="front.step1.title"/>
                </h2>
                <p class="lead">
                    <spring:message code="front.step1.text"/>
                </p>
            </div>
        </div>
        <div class="row">

            <div class="col-lg-12 col-sm-12">
                <div>
                    <a class='btn btn-primary col-lg-2 col-sm-2' href='javascript:;'>

                        <span id="upload-file-info"><spring:message code="front.step1.choosefile"/>.</span>
                        <input id="file" name="file" type="file" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="file_source" size="40"  onchange='$("#upload-file-info").html(getFileName($(this).val()));'>
                    </a>
                </div>
            </div>
        </div>
    </div><!-- /.container -->
</div><!-- /.content-section-a Info -->
<div class="content-section-a">
    <div class="container">
        <div class="row" >
            <div class="col-lg-12 col-sm-12">
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h2 class="section-heading">
                    <spring:message code="front.step2.label.speakers"/>
                </h2>
                <p class="lead">
                    <spring:message code="front.step2.menu.info.content.speakers"/>
                </p>
            </div>
            <form>
                <div class="btn-group col-lg-12 col-sm-12" data-toggle-name="speakersCount" data-toggle="buttons-radio">
                    <button type="button" value="1" data-toggle="button" class="btn col-lg-2 col-sm-2">1</button>
                    <button type="button" value="2" data-toggle="button" class="btn col-lg-2 col-sm-2">2</button>
                    <button type="button" value="3" data-toggle="button" class="btn col-lg-2 col-sm-2">3</button>
                    <button type="button" value="4" data-toggle="button" class="btn col-lg-2 col-sm-2">4</button>
                    <button type="button" value="5" data-toggle="button" class="btn col-lg-2 col-sm-2">5</button>
                    <button type="button" value="6" data-toggle="button" class="btn col-lg-2 col-sm-2">6</button>
                </div>
                <input type="hidden" id="speakersCount" name="speakersCount" value="1"/>
            </form>
        </div>
    </div><!-- /.container -->
</div><!-- /.content-section-a Speakers Count -->
<div class="content-section-a">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12">
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h2 class="section-heading">
                    <spring:message code="front.step2.label.email"/>
                </h2>
                <p class="lead">
                    <spring:message code="front.step2.menu.info.content.email"/>
                </p>
            </div>
            <div class="col-lg-12 col-sm-12">
                <form:input path="email"  placeholder="Your email" cssClass="form-control col-lg-12 col-sm-12"/>
            </div>
        </div>
    </div><!-- /.container -->
</div><!-- /.content-section-a Email -->
<div class="content-section-a">

    <div class="container">

        <div class="row">
            <div class="col-lg-12 col-sm-12">
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h2 class="section-heading">
                    <spring:message code="front.step4.title"/>
                </h2>
                <p class="lead">
                    <spring:message code="front.step4.text"/>
                </p>
            </div>
            <div class="col-lg-12 col-sm-12">
                <button  onclick="$('#whoWhenRequestForm').submit()" type="button" class="btn btn-lg btn-primary"><spring:message code="front.step4.submit"/></button>
            </div>
        </div>

    </div><!-- /.container -->

</div><!-- /.content-section-a Submit-->
</form:form>
<mytags:bootstrap-footer/>
<mytags:bootstrap-postbody/>
</body>
</html>
