/**
 *
 * @author Piotr Sukiennik
 * Date: 23.10.12
 * Time: 21:56
 *
 */


var AudioPlayer = function AudioPlayer(audioElementId, unactiveCssClass, activeCssClass) {
    var thisAudioPlayer = this;
    this.registeredPlayStopButtons = {};
    this.$audioElement = $("#" + audioElementId);
    this.unactiveCssClass = unactiveCssClass ? unactiveCssClass : "audioplayer-psbutton-unactive";
    this.activeCssClass = activeCssClass ? activeCssClass : "audioplayer-psbutton-active";
    this.playThroughState = false;
    this.$audioElement[0].pause();
    this.duration = 0;


    this.$audioElement.bind("play", function () {

    });
    this.$audioElement.bind("pause", function () {
        thisAudioPlayer.reset();
    });
    this.$audioElement.bind("ended", function () {
        thisAudioPlayer.reset();
    });


    this.$audioElement.bind("timeupdate", function () {
        if (thisAudioPlayer.$audioElement[0].currentTime > 0) {
            $(thisAudioPlayer).trigger("percentProgressUpdate", [(thisAudioPlayer.$audioElement[0].currentTime / thisAudioPlayer.$audioElement[0].duration) * 100]);
        }
        if (thisAudioPlayer.playThroughState) {
            playThroughTimeUpdate();
        } else {
            normalTimeUpdate();
        }
    });

    var normalTimeUpdate = function () {
        var currTime = Number(thisAudioPlayer.$audioElement[0].currentTime);
        for (var buttonId in thisAudioPlayer.registeredPlayStopButtons) {
            var $button = $(thisAudioPlayer.registeredPlayStopButtons[buttonId].button);
            if (thisAudioPlayer.registeredPlayStopButtons[buttonId].active) {
                if (currTime > thisAudioPlayer.registeredPlayStopButtons[buttonId].to) {
                    $button.addClass(thisAudioPlayer.activeCssClass);
                    thisAudioPlayer.reset();
                }
                break;
            }
        }
    };

    var playThroughTimeUpdate = function () {
        if (!thisAudioPlayer.$audioElement[0].paused) {
            var currTime = Number(thisAudioPlayer.$audioElement[0].currentTime);
            var active = false;
            for (var buttonId in thisAudioPlayer.registeredPlayStopButtons) {
                var $button = $(thisAudioPlayer.registeredPlayStopButtons[buttonId].button);
                if (currTime >= thisAudioPlayer.registeredPlayStopButtons[buttonId].from && currTime < thisAudioPlayer.registeredPlayStopButtons[buttonId].to) {
                    if (!thisAudioPlayer.registeredPlayStopButtons[buttonId].active) {
                        thisAudioPlayer.$audioElement.trigger("streamactivate", [$button]);
                    }
                    thisAudioPlayer.registeredPlayStopButtons[buttonId].active = true;
                    $button.removeClass(thisAudioPlayer.unactiveCssClass);
                    $button.addClass(thisAudioPlayer.activeCssClass);
                    active = true;
                } else {
                    if (thisAudioPlayer.registeredPlayStopButtons[buttonId].active) {
                        thisAudioPlayer.$audioElement.trigger("streamdeactivate", [$button]);
                    }
                    thisAudioPlayer.registeredPlayStopButtons["active"] = false;
                    $button.removeClass(thisAudioPlayer.activeCssClass);
                    $button.addClass(thisAudioPlayer.unactiveCssClass);
                }
            }
            if (!active) {
                thisAudioPlayer.reset();
            }
        }
    };


    this.playThrough = function () {
        thisAudioPlayer.reset();
        thisAudioPlayer.playThroughState = true;
        thisAudioPlayer.$audioElement[0].play();
    };

    this.reset = function () {
        this.$audioElement[0].pause();
        this.$audioElement[0].currentTime = 0;
        //this.playThroughState=false;
        for (var buttonId in this.registeredPlayStopButtons) {
            var $button = $(thisAudioPlayer.registeredPlayStopButtons[buttonId].button);
            if (thisAudioPlayer.registeredPlayStopButtons[buttonId].active) {
                thisAudioPlayer.$audioElement.trigger("streamdeactivate", [$button]);
            }
            thisAudioPlayer.registeredPlayStopButtons[buttonId].active = false;
            $button.removeClass(this.unactiveCssClass);
            $button.removeClass(this.activeCssClass);
        }
    };

    var playStopButtonClicked = function (event, $buttonElement) {
        if (thisAudioPlayer.registeredPlayStopButtons[$buttonElement[0].id].active) {
            thisAudioPlayer.reset();
        } else if (thisAudioPlayer.$audioElement[0].paused) {
            thisAudioPlayer.reset();
            if (!thisAudioPlayer.playThroughState) {
                for (var buttonId in thisAudioPlayer.registeredPlayStopButtons) {
                    var $button = $(thisAudioPlayer.registeredPlayStopButtons[buttonId].button);
                    $button.removeClass(thisAudioPlayer.activeCssClass);
                    $button.addClass(thisAudioPlayer.unactiveCssClass);
                }
            }
            $buttonElement.removeClass(thisAudioPlayer.unactiveCssClass);
            $buttonElement.addClass(thisAudioPlayer.activeCssClass);
            thisAudioPlayer.$audioElement.trigger("streamactivate", [$buttonElement]);
            thisAudioPlayer.registeredPlayStopButtons[$buttonElement[0].id].active = true;
            thisAudioPlayer.$audioElement[0].currentTime = thisAudioPlayer.registeredPlayStopButtons[$buttonElement[0].id].from;
            thisAudioPlayer.$audioElement[0].play();
        }
    };
    this.registerPlayStopButton = function (buttonElementId, from, to) {
        var $buttonElement = $("#" + buttonElementId);
        var registeredPlayStopButtons = this.registeredPlayStopButtons;
        registeredPlayStopButtons[buttonElementId] = {};
        registeredPlayStopButtons[buttonElementId]["button"] = $buttonElement;
        registeredPlayStopButtons[buttonElementId]["label"] = $buttonElement.text();
        registeredPlayStopButtons[buttonElementId]["active"] = false;
        registeredPlayStopButtons[buttonElementId]["from"] = Number(from);
        registeredPlayStopButtons[buttonElementId]["to"] = Number(to);
        $buttonElement.bind("click", function () {
            playStopButtonClicked(event, $buttonElement);
        });
    };

    this.unRegisterPlayStopButton = function (buttonElementId) {
        $("#" + buttonElementId).unbind("click");
        delete this.registeredPlayStopButtons[buttonElementId];
    }

};
