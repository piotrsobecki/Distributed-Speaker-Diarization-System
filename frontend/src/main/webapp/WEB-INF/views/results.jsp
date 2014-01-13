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
    <script src="<c:url value="/resources/js/results.js"/>"></script>

    <script type="text/javascript">


        var serviceProps ={
            urls:{
                getProgress:"<c:url value="http://${applicationPath}/${requestIdentifier}/progress"/>",
                getTimeline:"<c:url value="http://${applicationPath}/${requestIdentifier}/timeline"/>",
                getConvertedAudio:"<c:url value="http://static.${domainPath}/${requestIdentifier}"/>"
            }
        };
        var labelMapperProps = {
            SILENCE:"<spring:message code="SILENCE"/>",
            NOT_SILENCE:"<spring:message code="NOT_SILENCE"/>",
            cluster0:"<spring:message code="cluster0"/>",
            cluster1:"<spring:message code="cluster1"/>",
            cluster2:"<spring:message code="cluster2"/>",
            cluster3:"<spring:message code="cluster3"/>",
            cluster4:"<spring:message code="cluster4"/>",
            cluster5:"<spring:message code="cluster5"/>",
            cluster6:"<spring:message code="cluster6"/>"
        };
        var statusBarUpdaterProps = {
            statusMap: <spring:message code="request.status.status.map"/>,
            statusMapDone:<spring:message code="request.status.status.map_done"/>
        };
        var labelMapper = new LabelMapper(labelMapperProps);
        var service = new ServiceApi(serviceProps);
        var html = new HTMLApi({},service,labelMapper);
        var progressProps={
            listeners:{
                progress:[
                    new StatusBarUpdater(statusBarUpdaterProps),
                    new ResultsReceiver(html),
                    new StopProgressAwareListener()
                ]
            }
        };
        var progress = new ProgressAware(service,progressProps);

    </script>
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
                <h2 class="section-heading progress-status">
                    <spring:message code="request.progress.title"/>
                </h2>
                <p class="lead">
                    <spring:message code="request.progress.text"/>
                </p>
            </div>
            <div class="col-lg-12 col-sm-12">
                <div class="progress progress-striped active">
                    <div class="progress-bar"  role="progressbar"  aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                        <span class="sr-only">0%</span>
                    </div>
                </div>
            </div>
        </div>

    </div><!-- /.container Progress -->

</div><!-- /.content-section-a Progress-->
<div class="content-section-a">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12">
                <hr class="section-heading-spacer">
                <div class="clearfix"></div>
                <h2 class="section-heading">
                    <spring:message code="request.results.title"/>
                </h2>
                <p class="lead">
                    <spring:message code="request.results.text" htmlEscape="false"/>
                </p>
            </div>
            <div class="results-output col-lg-12 col-sm-12">
            </div>
        </div>
    </div><!-- /.container -->
</div><!-- /.content-section-a Results-->
<mytags:bootstrap-footer/>
<mytags:bootstrap-postbody/>

<script type="text/javascript">
    $(function(){
        progress.start();
    });
</script>
</body>
</html>
