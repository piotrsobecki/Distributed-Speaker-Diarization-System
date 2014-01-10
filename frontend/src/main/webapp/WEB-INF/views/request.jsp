<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fi">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<mytags:jquery />
     <script src="<c:url value="/resources/js/WhoWhenAudioPlayer.js"/>" type="text/javascript"></script>
<mytags:style />


    <title>WhoWhen</title>
    <script type="text/javascript">

        var statusMap = <spring:message code="request.status.status.map"/>;
        var statusMapDone = <spring:message code="request.status.status.map_done"/>;
        var getProgressURL = "<c:url value="http://${applicationPath}/${requestIdentifier}/progress"/>";
        var getTimelineURL = "<c:url value="http://${applicationPath}/${requestIdentifier}/timeline"/>";
        var convertedAudioURL = "<c:url value="http://static.${domainPath}/${requestIdentifier}"/>";
        var progress=0;
        var progressBarActive=false;
        var status;
        var whoWhenAudioPlayer;

        var getProgressInterval;
        $(function(){
            getProgress();
            getProgressInterval = setInterval(function(){
                getProgress();
            },2000);
            progressBarActive=true;
//            startProgressbar(250);
        });

        function getProgress(){
            $.ajax({
                method: "GET",
                url: getProgressURL,
                dataType: "json",
                cache:false,
                success: function(data) {
                    handleData(data);
                },
                error: function(data){
                    console.log(data);
                }
            });
        }

        function getTimeline(timeline){
            $.ajax({
                method: "GET",
                url: getTimelineURL,
                dataType: "json",
                cache:false,
                success: timeline
            });
        }

        var audioInitialized = false;
        function handleData(data){
           // $("div#request_status_msg").slideUp(function(){
                if (data.progress>progress){
                    progress=parseInt(data.progress);
                    refreshProgressBar();
                }
                if (data.status !=status){
                    progress=data.progress;
                    status=data.status;
                    refreshProgressBar();
                }
                var statusLabel;
             //   var fileName = decode64(data.base64FileName);

                if (data.progress>=100){
                    if (getProgressInterval){
                        clearInterval(getProgressInterval);
                    }
                    getTimeline(function(timeline){
                        initAudioElement(convertedAudioURL,timeline);
                        console.log(timeline);
                    });
                    $("#statusProgressBar").addClass("done");
                    statusLabel=statusMapDone[data.status];

                }  else {
                    $("#statusProgressBar").removeClass("done");
                    statusLabel=statusMap[data.status];
                }

                    $("span#requestStatus").text(data.progress + "%");

        }
        var msecWidth = 0.5;
        function initAudioElement(fileLocation,timeline){
            $("body").append('<audio id="audioElement0" src="'+fileLocation+'" type="audio/wav" preload="auto" />');
            //  convertedFileAudio.play();

            whoWhenAudioPlayer=new AudioPlayer("audioElement0","button_unactive","button_active");

            var $audioElement = $("#audioElement0");
            $audioElement.bind("durationchange",function(){
                var audioDuration = $audioElement[0].duration;
                for (var level=0; level<timeline.length; level++){
                    $("#labels").prepend('<ul id="labelsForLevel'+level+'" class="labelsForLevel"></ul>');
                    var timelineOnLevelId = "timeline"+level;
                    $("#timelines").prepend('<li id="'+timelineOnLevelId+'"></li>');
                    for (var label in timeline[level]){
                        var color = "rgb("+parseInt(100+Math.random()*100)+","+parseInt(100+Math.random()*100)+","+parseInt(100+Math.random()*100)+")";
                        var labelId= "editable_label_"+label;
                        $("#labelsForLevel"+level).append('<li id="'+labelId+'li" class="labelLi"><span id="'+labelId+'" class="labelForIntervals" style="color: white;background-color: '+color+'">'+label+'</span><span style="position:absolute;"><span id="'+labelId+'link" class="linkForLabel" style="display:none;"> (<a href="'+convertedAudioURL+'/'+label+'">link</a>)</span></span></li>');
                        $("#"+labelId).editable(function(){
                            console.log($(this).val());
                        });
                        $("#"+labelId+"li").hover(function(){
                            $("#"+this.id+"nk").fadeToggle();
                        });
                        for (var intervalId in timeline[level][label]){
                            var playStopButtonId = label+intervalId;
                            var from = timeline[level][label][intervalId][0];
                            var to = timeline[level][label][intervalId][1];
                            var width = (to-from);
                            var  html = '<div id="'+playStopButtonId+'" class="playStopButton from'+(from/1000)+' to'+(to/1000)+'"  style="left:'+msecWidth*from+'px; width:'+msecWidth*width+'px; background-color:'+color+';" >&nbsp;</div>';
                            $("#"+timelineOnLevelId).append(html);
                            whoWhenAudioPlayer.registerPlayStopButton(playStopButtonId,from/1000,to/1000);
                        }
                    }
                }

            });
            $(whoWhenAudioPlayer).bind("percentProgressUpdate",function(event,progress){
                var $tW =  $("#timelineWrapper");
                console.log("t width = "+$tW[0].scrollWidth);
                $tW[0].scrollLeft =$tW[0].scrollWidth*progress/100;
                console.log(progress);
            });

            $audioElement.bind("streamdeactivate",function(event,$button){
               // $button.text("Play");
            });

            $audioElement.bind("streamactivate",function(event,$button){
              //  $button.text("Stop");
            });
        }

        function startProgressbar(interval){
            setInterval(function(){
              if(progressBarActive){
                progress+=5;
                refreshProgressBar();
              }
            },interval);
        }

        function refreshProgressBar(){
            if (progress>=100){
                $("#statusProgressBar").css("width","100%");
            } else {
                $("#statusProgressBar").css("width",progress+"%");
            }
        }



    </script>
</head>
<body accent='blue' theme='light'>
    <div id="head_wrapper">
        <mytags:head/>
    </div>
    <div id="content_wrapper">
        <mytags:menu/>
        <div class="info_window">
            <div id="metro-pivot1" class='metro-pivot'>
                <div class='pivot-item'>
                    <h3><spring:message code="request.menu.status"/></h3>
                    <div class="progressBarWrapper" title="<spring:message code="request.status.status"/>">
                        <div class="progress2">
                            <span  id="statusProgressBar" class="bar2"><span id="requestStatus"></span></span>
                        </div>
                    </div>
                </div>
               <div class='pivot-item'>
                    <h3><spring:message code="request.menu.help"/></h3>
                   <spring:message code="request.help.text"/>
                </div>
                <div class='pivot-item'>
                    <h3><spring:message code="request.menu.result"/></h3>
                    <div id="labelledTimeline">
                        <ul id="labels"></ul>
                        <div id="timelineWrapper">
                            <ul id="timelines"></ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <script type="text/javascript">
            $("div#metro-pivot1").metroPivot();
        </script>
<%--        <div id="start" class="button"  onclick="levelAction(0,true);">start</div>--%>


    </div>

    <mytags:footer/>
</body>
</html>
