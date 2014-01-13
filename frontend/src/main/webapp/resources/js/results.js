var LabelMapper = function(props){
    var properties = {
        SILENCE:"Silence",
        NOT_SILENCE:"Not silence",
        cluster0:"Speaker 1",
        cluster1:"Speaker 2",
        cluster2:"Speaker 3",
        cluster3:"Speaker 4",
        cluster4:"Speaker 5",
        cluster5:"Speaker 6",
        cluster6:"Speaker 7"
    };
    $.extend(true,properties,props);
    return {
        map: function(label){
            return properties[label];
        }
    }
};




/**
 *  props : {
    *       urls:{
    *           getProgress:"",
    *           getTimeline:""
    *    }
    *  }
 *
 * @param props
 * @constructor
 */
var ServiceApi = function(props){
    return {
        getProgress: function(){
            var data;
            $.ajax({
                method: "GET",
                url: props.urls.getProgress,
                dataType: "json",
                cache: false,
                async: false,
                success: function (d) {
                    data = d;
                },
                error: function (data) {
                    console.log(data);
                }
            });
            return data;
        },
        getFileUrl:function(label){
            return props.urls.getConvertedAudio+'/'+label+'.wav';
        },
        getTimeline: function(){
            var d;
            $.ajax({
                method: "GET",
                url: props.urls.getTimeline,
                dataType: "json",
                cache: false,
                async: false,
                success: function(data){
                    d=data;
                }
            });
            return d;
        }
    }
};
/**
 *
 * props : {
    *   interval: refresh interval,
    *   listeners:{
    *       progress:[]  - progress listeners - with function receive(data)
    *   }
    * }
 * @param service - Backend API
 * @param props
 * @returns {{start: start}}
 * @constructor
 */
var ProgressAwareStatic=0;
var ProgressAware = function(service,props){
    var progressInterval;
    var properties = {
        interval:2000
    };
    $.extend( true, properties, props );
    function notify(key,data){
        var listeners = properties.listeners[key];
        for (var key in listeners){
            listeners[key]['receive'](data);
        }
    }
    var obj =  {
        start: function(){
            var d = service.getProgress();
            notify("progress",d);
            if (parseInt(d.progress)<100){
                progressInterval = setInterval(function () {
                    notify("progress",service.getProgress());
                }, properties.interval);
            }
        },
        stop : function(){
            clearInterval(progressInterval);
        }
    };
    ProgressAwareStatic = obj;
    return obj;
};

var StopProgressAwareListener = function(){
    return{
        receive:function(data){
            if (parseInt(data.progress)>=100){
                ProgressAwareStatic.stop();
            }
        }
    }
};
var StatusBarUpdater = function(props){
    var properties = {
        progressStatusClass:".sr-only",
        progressBarClass:".progress-bar",
        statusClass:".progress-status"
    };
    $.extend(true,properties,props);
    var currentProgress = 0;
    return {
        receive:function(data){
            var progress = parseInt(data.progress);
            if (currentProgress<progress){
                if (data.progress >= 100) {
                    $(properties.progressBarClass).css("width", "100%");
                    $(properties.progressBarClass).attr("aria-valuenow",100);
                    $(properties.statusClass).html(properties.statusMapDone[data.status]);
                } else {
                    $(properties.progressBarClass).css("width", progress + "%");
                    $(properties.progressBarClass).attr("aria-valuenow",progress);
                    $(properties.statusClass).html(properties.statusMap[data.status]);
                }
                $(properties.progressStatusClass).html( progress + "%");
                currentProgress = progress;
            }
        }
    }
};

var HTMLApi = function(props,serviceApi,labelMapper){
    var properties = {
        resultsCssClass:".results-output"
    };
    $.extend(true,properties,props);
    function getElementHtml(label){
        return '<a href="'+serviceApi.getFileUrl(label)+'" class="list-group-item">'+labelMapper.map(label)+'</a>'
    }
    function getStartElementsHtml(level){
        return '<div class="list-group">';
    }
    function getEndElementsHtml(level){
        return '</div>';
    }

    var elements={};
    function sortOnKeys(dict) {
        var sorted = [];
        for(var key in dict) {
            sorted[sorted.length] = key;
        }
        sorted.sort();
        var tempDict = {};
        for(var i = 0; i < sorted.length; i++) {
            tempDict[sorted[i]] = dict[sorted[i]];
        }
        return tempDict;
    }
    return {
        addElementOnLevel: function(level,elementLabel){
            var els = elements[level];
            if(typeof els == 'undefined') {
                elements[level]=[];
            }
            elements[level].push(elementLabel);
        },
        render:function(){
            sortOnKeys(elements);
            var html = "";
            for (var level in elements){
                html+=getStartElementsHtml(level);
                var elementLabels = elements[level];
                for (var label in elementLabels){
                    html+=getElementHtml(elementLabels[label]);
                }
                html+=getEndElementsHtml(level);
            }
            $(properties.resultsCssClass).html(html);
            elements={};
        }
    }
};

var ResultsReceiver = function(htmlApi){
    return{
        receive:function(data){
            var progress = parseInt(data.progress);
            if (progress>=100){
                var timeline = service.getTimeline();
                for (var level = 0; level < timeline.length; level++) {
                    for (var label in timeline[level]) {
                        htmlApi.addElementOnLevel(level,label);
                    }
                }
                htmlApi.render();
            }
        }
    }
};
