<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fi">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<mytags:jquery />
<mytags:style />

    <title>WhoWhen</title>
    <script type="text/javascript">
        var stepLocations = {0:0};
        function levelAction(level,success){
            for (var i=0; i<=4; i++){
              if (i<=level){
                  $("#progress_step_"+i).addClass("progress_step_past");
                  $("#step_wrapper"+i).show();
              } else if (!success){
                  $("#progress_step_"+i).removeClass("progress_step_past");
                  $("#step_wrapper"+i).hide();
              }
            }

            var targetLevel = level;
            if (success){
                targetLevel=level+1;
                $("#step_wrapper"+targetLevel).show();
                $("#progress_step_"+targetLevel).addClass("progress_step_past");
                stepLocations[targetLevel]=$("#step_wrapper"+targetLevel).offset().top;
                console.log("stepLocs = "+JSON.stringify(stepLocations));
            }

            scrollToLevel(targetLevel);
        }
        function scrollToLevel(targetLevel){
            if (targetLevel==0){
                $('html,body').animate({scrollTop: 0}, 1000);
            }   else {
                var position =$("div#step_wrapper"+targetLevel+" .step_content").offset();
                position.top-=$("div#step_wrapper"+targetLevel+" .step_content").height()/3;
                $('html,body').animate({scrollTop: position.top}, 1000);
            }

        }

        $(document).scroll(function(){

            var pos = $(this).scrollTop();
            var largesti =0;
            for(var i in stepLocations){
                $("#progress_step_"+i).removeClass("progress_step_active");
                if(stepLocations[i]<=pos){
                    largesti=i;
                }
            }
            $("#progress_step_"+largesti).addClass("progress_step_active");


        });



    </script>
</head>
<body accent='blue' theme='light'>

    <div id="progress_window">
        <ol id="progress_steps">
            <li id="progress_step_0" class="progress_step_past progress_step_active"><h3><spring:message code="front.progress.step0"/></h3></li>
            <li id="progress_step_1"><h3><spring:message code="front.progress.step1"/></h3></li>
            <li id="progress_step_2"><h3><spring:message code="front.progress.step2"/></h3></li>
            <%--<li id="progress_step_3"><h3><spring:message code="front.progress.step3" /></h3></li>--%>
            <li id="progress_step_3"><h3><spring:message code="front.progress.step4"/></h3></li>
        </ol>
    </div>

    <script type="text/javascript">
        $("ol#progress_steps li").click(function(event){
            scrollToLevel(Number(this.id.substring("progress_step_".length)));
        });
    </script>
    <div id="head_wrapper">
        <mytags:head/>
    </div>
    <div id="content_wrapper">
        <c:if test="${not empty error and error}">
            <div class="error"><spring:message code="form.error" /></div>
        </c:if>
        <mytags:menu/>
        <div class="info_window">
            <div id="metro-pivot1" class='metro-pivot'>
                <div class='pivot-item'>
                    <h3><spring:message code="front.step0.about.title"/></h3>
                    <spring:message code="front.step0.about.text"/>
                </div>
                <div class='pivot-item'>
                    <h3><spring:message code="front.step0.author"/></h3>
                    <spring:message code="front.step0.author.text"/>
                </div>
                <div class='pivot-item'>
                    <h3><spring:message code="front.step0.language.title"/></h3>
                    <ul id="languages">
                        <li><a title="<spring:message code="front.step0.languages.pl"/>" href="?lang=pl">.pl</a></li>
                        <li><a title="<spring:message code="front.step0.languages.en"/>" href="?lang=en">.en</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <script type="text/javascript">
                $("div#metro-pivot1").metroPivot();
        </script>
        <div id="start" class="button"  onclick="levelAction(0,true);"><spring:message code="front.step0.start"/></div>

        <form:form commandName="FORM" id="whoWhenRequestForm" enctype="multipart/form-data" action="/datasubmit/" method="POST">
            <div id="step_wrapper1" class="step_wrapper" style="display: none;" >
                <div class="step_separator"></div>
                <div class="step_content">
                    <div class="step_title"><spring:message code="front.step1.title"/> </div>
                    <div class="info_window">
                        <div id="metro-pivot11" class='metro-pivot'>
                            <div class='pivot-item'>
                                <h3><spring:message code="front.step1.menu.info"/></h3>
                                <p><spring:message code="front.step1.text"/></p></br></br>
                            </div>
                            <div class='pivot-item'>
                                <h3><spring:message code="front.step1.menu.help"/></h3>
                                <p><spring:message code="front.step1.maxsize"/>: <c:out value="${maxUploadFileSize/1000000}"/>MB</p></br>
                                <p><spring:message code="front.step1.acceptableformats"/></p></br>
                                <div id="supportedFileTypes">
                                    <ul>
                                        <c:forEach var="type" items="${acceptableFormats}" varStatus="varStatus">
                                            <li><c:out value="${type}"/></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $("div#metro-pivot11").metroPivot();
                    </script>
                    <div id="file_button" class="button" ><spring:message code="front.step1.choosefile"/></div>
                    <input type="file" id="file" name="file"/>
                    <script type="text/javascript">
                        var supportedFileTypes ='<c:forEach var="type" items="${acceptableFormats}" varStatus="varStatus"><c:out value="${type}"/><c:if test="${!varStatus.last}">|</c:if></c:forEach>';

                        String.prototype.endsWith = function(pattern) {
                            return this.match(new RegExp(pattern+'$','i'));
                        };
                        var wrapper = $('<div/>').css({height:0,width:0,'overflow':'hidden'});
                        var fileInput = $('#file').wrap(wrapper);
                        function isValid(path){
                            return path.endsWith(supportedFileTypes);
                        }
                        function getFileName(path){
                            return path.match(/[^\\]+$/i)[0];
                        }

                        function resetVisibility(){

                        }
                        fileInput.change(function(){
                            $this = $(this);
                            var filePath = $this.val();
                            if (filePath == undefined || filePath === "" || !isValid(filePath)){
                                $('#file_button').text("Choose audio file");
                                $("#step_1_ok").hide();
                                levelAction(1,false);
                            } else{
                                $('#file_button').text(getFileName(filePath));
                                $("#step_1_ok").show();
                            }
                        });

                        $('#file_button').click(function(){
                            fileInput.click();
                        }).show();
                    </script>

                    <div id="step_1_ok" class="button" style="display: none;"  onclick="levelAction(1,true);"><spring:message code="front.step1.submit"/></div>
                    <div class="clear_both"></div>
                </div>
            </div>
            <div id="step_wrapper2" class="step_wrapper" style="display: none;">
                <div class="step_separator"></div>
                <div class="step_content">
                    <div class="step_title"><spring:message code="front.step2.title"/></div>
                    <div class="info_window">
                        <div id="metro-pivot2" class='metro-pivot'>
                            <div class='pivot-item'>
                                <h3><spring:message code="front.step2.menu.email"/></h3>
                                <ul class="processing_parameters">
                                    <li><form:label path="email"><spring:message code="front.step2.label.email"/>:</form:label><form:input path="email"/></li>
                                    <li><form:label path="speakersCount"><spring:message code="front.step2.label.speakers"/>:</form:label><form:input path="speakersCount"/></li>
                                </ul>
                            </div>
                            <div class='pivot-item'>
                                <h3><spring:message code="front.step2.menu.info"/></h3>
                                <ul class="processing_parameters">
                                    <li><spring:message code="front.step2.label.email"/> :<br/> <spring:message code="front.step2.menu.info.content.email"/></li>
                                    <li><spring:message code="front.step2.label.speakers"/> :<br/> <spring:message code="front.step2.menu.info.content.speakers"/></li>
                                </ul>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $("div#metro-pivot2").metroPivot();
                        </script>
                    </div>
                    <div id="step_2_parameters">
                        <div class="clear_both"></div>
                        <div id="step_2_ok" class="button" onclick="levelAction(2,true);"><spring:message code="front.step2.submit"/></div>
                        <div class="clear_both"></div>
                    </div>
                </div>
            </div>
            <%--<div id="step_wrapper3" class="step_wrapper" style="display: none;">
                <div class="step_separator"></div>
                <div class="step_content">
                    <div class="step_title"><spring:message code="front.step3.title"/></div>
                    <div class="info_window"><spring:message code="front.step3.text"/></div>
                    <div class="step_info"></div>
                    &lt;%&ndash;<div id="step_3_show_terms" onclick=" $('#step_3_terms_accept').show();" class="button"><spring:message code="front.step3.terms.show"/></div>&ndash;%&gt;
                    <div  class="button" id="step_3_terms_accept" onclick="levelAction(3, true);" ><spring:message code="front.step3.terms.accept"/></div>
                    &lt;%&ndash;<div style="float: left; width: 40%; margin-right:5%;"onclick="step_3_terms_checkbox.checked = 0; levelAction(0, false); " class="button"><spring:message code="front.step3.terms.dontaccept"/></div>
                    <div style="float: right; width: 40%; margin-left: 5%;" onclick="step_3_terms_checkbox.checked = 1; levelAction(3, true);" class="button"><spring:message code="front.step3.terms.accept"/></div>
&ndash;%&gt;
                      &lt;%&ndash;  <input type="checkbox" id="step_3_terms_checkbox"  style="display: none;"/>&ndash;%&gt;

                </div>
            </div>--%>
            <div id="step_wrapper3" class="step_wrapper" style="display: none;">
                <div class="step_separator"></div>
                <div class="step_content">
                    <div class="step_title"><spring:message code="front.step4.title"/></div>
                    <div id="processingStatus" class="info_window">
                        <spring:message code="front.step4.text"/>
                    </div>
                    <div class="step_info"></div>
                    <div onclick="$('#whoWhenRequestForm').submit()" class="button" style="font-size: 3em;"><spring:message code="front.step4.submit"/></div>
                </div>
            </div>
        </form:form>
        <mytags:footer/>
    </div>
</body>
</html>
