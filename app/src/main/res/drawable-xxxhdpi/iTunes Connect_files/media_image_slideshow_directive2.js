define(['sbl!app'], function (itcApp) {

	itcApp.directive('mediaImageSlideshow2',function($timeout, $sce){
        /* for ss */
        return {
            restrict: 'A',
            templateUrl: getGlobalPath('/itc/views/directives/image_slideshow_template2.html'),
            scope: {
                show: "=",
                device: "=", 
                allImages: "=",
                allVideos: '=',
                currentIndex: "=",
                videoShowing: "=",
                previewPlayAllowed: "="
            }, 
          
            link: function(scope, element, attrs) {
                var mainImg = element.find("img");
                var arrowButtons = element.find(".slideshowArrow");
                var btnContainerRt = element.find(".slideshowButtonContainerRight");
                var btnContainerLt = element.find(".slideshowButtonContainerLeft");
                var mainContent = element.find(".slideshowMainContent");
                var mainContentParent = mainContent.parent();
                var videoHolder = element.find("#videoSlideshowHolder");
                scope.videoShowing = false;
                scope.animate = false;

                scope.hasVideoError = function(index) {
                    var error = false;
                    if (scope.videos && scope.videos[index]) {
                        error = scope.videos[index].cantPlayVideo || 
                                scope.videos[index].processingVideo || 
                                scope.videos[index].videoError;
                    }
                    return error || !scope.previewPlayAllowed;
                };

                scope.setVideo = function() {
                    var index = scope.currentIndex;
                    if (scope.videos && scope.videos[index]) {
                        console.info("video: ", scope.videos[index]);
                        var file = scope.videos[index].$videoFile;
                        var videoUrlFromServer = scope.videos[index].videoUrlFromServer;

                        var fileType, sourceEl;
                        // if got a file
                        if (file) {
                            var videoFile = URL.createObjectURL(file);
                            var videoURL = $sce.trustAsResourceUrl(videoFile); 

                            fileType = file.type;
                            var sourceEl = '<source src="' + videoURL + '" type="' +  fileType + '">';
                            if (fileType === "video/quicktime") {
                                sourceEl += '<source src="' + videoURL + '" type="video/mp4">'; // makes .mov movie work in chrome
                            }     
                        }
                        else if (videoUrlFromServer) { // if got a url from server
                            fileType = "application/x-mpegurl";
                            sourceEl = '<source src="' + videoUrlFromServer + '" type="' +  fileType + '">';
                        }

                        // This is how we're getting video source to change - reinserting a video element altogether.
                        // This is all because of an Angular bug with setting ng-src on a video's, source's src.
                        //videoHolder.html('<video class="ng-hide" controls="" id="video" name="media" style="margin:auto;">' + sourceEl + '</video>');
                        videoHolder.html('<video class="ng-hide" controls="" id="video" name="media" style="margin:auto;"></video>');

                        var jqV = element.find('#video');

                        scope.loading = true;
                        jqV.on("loadeddata", scope.onVideoLoadedFunc); 
                        jqV.on("error", scope.onVideoLoadingErrorFunc);

                        jqV.html(sourceEl);

                    }
                };

                scope.onVideoLoadingErrorFunc = function(e) {
                    log("onVideoLoadingErrorFunc " + e);
                    scope.loading = false;
                    scope.$apply();
                };

                // this is where we get the video height and width.
                scope.onVideoLoadedFunc = function(e) {
                    var v = e.target;

                    scope.videoWidth = v.videoWidth;
                    scope.videoHeight = v.videoHeight;

                    scope.setDimensions();
                    scope.adjustContentAndButtonMargins();

                    var jqV = element.find('#video');
                    jqV.removeClass("ng-hide");
                    scope.loading = false;

                    scope.$apply();
                };
                
                // Currently does nothing. Was trying to find a way to know when video is collapsed.    
                $(document).on("webkitfullscreenchange", function (e) { 
                    //console.log("expanded!");
                    var jqV = element.find('#video');
                    var v = jqV[0];
                    
                    var fullscreen = document.webkitIsFullScreen;
                    if (fullscreen) {
                        if (document.webkitCurrentFullScreenElement === v) {
                            scope.lastFullScreenElement = document.webkitCurrentFullScreenElement;
                        }
                    }
                    else {
                        if (v === scope.lastFullScreenElement) {
                            //console.log("just collapsed!");
                        }
                    }
                });

                scope.setDimensions = function() {
                    var wh = window.innerHeight; // do we care about non-modern browsers? if so, do this: http://stackoverflow.com/questions/3333329/javascript-get-browser-height
                    var ww = window.innerWidth;
                    
                    var maxHeight = wh - 40;
                    
                    var maxWidth = ww - 40;

                    scope.maxHeight = maxHeight;
                    scope.maxWidth = maxWidth;
                };

                mainImg.bind('load', function() {
                    scope.adjustContentAndButtonMargins();

                });

                mainImg.bind('error', function() {
                    console.log("error loading image in slideshow");

                });

                // Vertically centers slideshowPanel in the lightbox.
                scope.centerImageSlideshowPanel = function() {
                    var lightbox = element.closest('.full-lightbox');
                    var slideshowPanel = element.find('.imageSlideshowPanel');

                    var marginTop = (lightbox.height() - slideshowPanel.height())/2;
                    //slideshowPanel.css("margin-top", marginTop);
                };

                // This function should be cleaned up. It used to set margin-tops, etc, till we started using flex.
                scope.adjustContentAndButtonMargins = function() {
                    
                    // make the main content fit between the arrows.
                    // mainContent.width(scope.maxWidth - 214);
                    //mainContent.height(scope.maxHeight - 40); //?

                    //var mainContentH = mainContent.height();
                    var mainContentH = scope.maxHeight - 40;

                    var mainContentW = mainContent.width();
                    
                    //console.log("mainContent w h: " + mainContentW + ", " + mainContentH);

                    var mainImgWidthOld = mainImg[0].naturalWidth; //mainImg.width();
                    var mainImgHeightOld = mainImg[0].naturalHeight; //.height();
                    mainImg.width(mainImgWidthOld);
                    mainImg.height(mainImgHeightOld);

                    if (mainImgWidthOld > mainContentW) {
                        mainImg.width(mainContentW); // shrink image width to fit content
                        mainImg.height(mainImg.width() * mainImgHeightOld/mainImgWidthOld);
                    }

                    mainImgHeightOld = mainImg.height();
                    mainImgWidthOld = mainImg.width();

                    if (mainImgHeightOld > mainContentH) {
                        mainImg.height(mainContentH);
                        mainImg.width(mainImg.height() * mainImgWidthOld/mainImgHeightOld);
                    }
                    var btn = arrowButtons.first();

                    var h = mainImg.height();
                    if (scope.videoShowing) {
                        vid = element.find("video");
                        var vidHolder = element.find("#videoSlideshowHolder");
                        h = scope.maxHeight - 40;
                        w = scope.maxWidth - 214;
                        vidHolder.height(h);
                        var vh = vid.height();
                        var vw = vid.width();
                        //h = scope.maxHeight - 40;
                        //vid.height(h);
                        vid.css("max-height", h + "px");
                        vid.css("max-width", w + "px");
                    }
                    //btnContainerRt.height(h);
                    //btnContainerLt.height(h);

                    // center mainContent horizontally. pretty ugly but works.
                    var diff; // = mainContentParent.width() - mainContent.width() - (2*btn.width()) - 2*parseInt(btn.css("margin-left"));
                    //mainContent.css("margin-left", diff/2); // nah nevermind. unnecessary

                    // center image vertically
                    var imgMarginTop = 0;
                    if (!scope.videoShowing) {
                        diff = mainContent.height() - mainImg.height();
                        imgMarginTop = diff/2;
                        //mainImg.css("margin-top", imgMarginTop);
                    }

                    var marginTop = (btnContainerRt.height() - btn.height())/2;
                    //arrowButtons.css("margin-top", marginTop + imgMarginTop);

                    scope.centerImageSlideshowPanel();
                };

                // Resize this drop element if the window is resized.
                var resizeAdjustHandler = scope.adjustContentAndButtonMargins();
                $(window).on('resize',resizeAdjustHandler);
                scope.$on("$destroy",function(){
                    $(window).off('resize',resizeAdjustHandler);
                });

                scope.$watch('show', function(newVal, oldVal) {
                    if (!newVal) { // if hiding 
                        scope.stopVideo();
                    }      
                    else { // if showing
                      scope.updateMedia();
                      if (!scope.hasVideoError() && scope.videoShowing)  {
                        scope.setVideo();
                      }
                      scope.setDimensions();

                      scope.adjustContentAndButtonMargins();

                    }
                });

                scope.updateMedia = function() {
                    scope.images = scope.allImages[scope.device].arr;
                    if (scope.allVideos && scope.allVideos[scope.device]) {
                        scope.videos = scope.allVideos[scope.device].arr;
                    }
                }

                // does nothing
                scope.$watch('currentIndex', function(newVal, oldVal) {
                    if (newVal !== undefined) { 
                    }      
                });

                scope.$watch('videoShowing', function(newVal, oldVal) {
                    if (newVal !== undefined) { 
                        scope.adjustContentAndButtonMargins();
                    }      
                });

                scope.stopVideo = function() {
                    var v = videoHolder.find("video");
                    if (v[0]) {
                        v[0].pause();
                    }
                };

                scope.onFirstSlide = function() {
                    if (scope.show) { // tbd take vid errors into acccount
                        if (scope.videoShowing) {
                            var prevVidIndex = scope.getPreviousGoodVideoIndex();
                            var hasNoGoodVids = !scope.videos || prevVidIndex === -1; 
                            return hasNoGoodVids;
                        }
                        else { // if on an image
                            if (scope.currentIndex === 0) {
                                var prevVidIndex = scope.getPreviousGoodVideoIndex();
                                var hasNoGoodVids = !scope.videos || prevVidIndex === -1; 
                                return hasNoGoodVids;
                            }
                            else {
                                return false;
                            }
                        }
                    }
                    else {
                        return true;
                    }
                };

                scope.onLastSlide = function() {
                    if (scope.show && scope.images) {
                        if (scope.videos && scope.videoShowing) { // if on a video, we're only on the last slide if there are no images
                            if (scope.images.length === 0) {
                                var nextVidIndex = scope.getNextGoodVideoIndex();
                                return nextVidIndex === -1; 
                            }
                            else { // if has images
                                return false;
                            }
                        }
                        else {
                            return (scope.currentIndex === (scope.images.length-1));
                        }
                    }
                    else {
                        return true;
                    }
                };

                scope.getNextGoodVideoIndex = function() {
                    if (scope.videos) {
                        var start;
                        if (scope.videoShowing) { // if on a video
                            start = scope.currentIndex+1; // start with the next video  
                            for (var i=start; i<scope.videos.length; i++) {
                                if (!scope.hasVideoError(i)) {
                                    return i;
                                }
                            }
                            return -1;
                        }
                        return -1;
                    }
                    return -1;
                }

                scope.getPreviousGoodVideoIndex = function() {
                    if (scope.videos) {
                        var start;
                        if (scope.videoShowing) { // if on a video
                            start = scope.currentIndex-1; // start with the prev video
                        }
                        else { // if on an image
                            start = scope.videos.length-1; // start the last video
                        }
                        for (var i=start; i>=0; i--) {
                            if (!scope.hasVideoError(i)) {
                                return i;
                            }
                        }
                        return -1;
                    }
                    return -1;
                }
               
                scope.slideLeft = function() {
                    var from = "toLeft";
                    var to = "toRight";

                    if (scope.animate) {
                        mainImg.addClass(to);
                        videoHolder.addClass(to);
                    }

                    $timeout(function() {
                        if (scope.videoShowing) {
                            if (scope.currentIndex > 0) { // if on any vid but the first, TBD take vid errors into account
                                var prev = scope.getPreviousGoodVideoIndex();
                                if (prev !== -1) {
                                    scope.currentIndex = prev;
                                    scope.stopVideo();
                                    scope.setVideo();
                                }
                            }
                        }
                        else { // image showing
                            if (scope.currentIndex === 0) { // if on first image
                                if (scope.videos && scope.videos.length > 0) { // if there are vids
                                    var prev = scope.getPreviousGoodVideoIndex();
                                    if (prev !== -1) {
                                        scope.currentIndex = prev;
                                        scope.videoShowing = true;
                                        scope.setVideo();
                                    }
                                }
                            }
                            else {
                                scope.currentIndex--; // go to previous image
                            }

                        }

                        if (scope.animate) {
                            mainImg.addClass("removeTransition");     
                            mainImg.addClass(from);
                            mainImg.removeClass(to);

                            videoHolder.addClass("removeTransition");     
                            videoHolder.addClass(from);
                            videoHolder.removeClass(to);
                           
                            // image about to come in 
                            $timeout(function() {
                                mainImg.removeClass("removeTransition");
                                mainImg.removeClass(from);

                                videoHolder.removeClass("removeTransition");
                                videoHolder.removeClass(from);
                            });
                        }
                    }); //, 250); // uncomment 250 to see the image moving to the left before it moves out.
                };

                scope.slideRight = function() {
                    var from = "toRight";
                    var to = "toLeft";

                    if (scope.animate) {
                        mainImg.addClass(to);
                        videoHolder.addClass(to);
                    }

                    $timeout(function() {
                        if (scope.videoShowing) {
                            if (scope.currentIndex === (scope.videos && scope.videos.length-1)) { // if on last video, go to first image
                                scope.currentIndex = 0;
                                scope.videoShowing = false;
                                scope.stopVideo();
                            }
                            else {
                                var next = scope.getNextGoodVideoIndex();
                                if (next === -1) {
                                    // go to next image
                                    scope.currentIndex = 0;
                                    scope.videoShowing = false;
                                    scope.stopVideo();
                                }
                                else {
                                    scope.currentIndex = next;
                                    scope.stopVideo();
                                    scope.setVideo();
                                }
                            }
                        }
                        else { // go to next image
                            scope.currentIndex++;
                            scope.videoShowing = false;
                        }

                        if (scope.animate) {
                            mainImg.addClass("removeTransition");     
                            mainImg.addClass(from);
                            mainImg.removeClass(to);
                            videoHolder.addClass("removeTransition");     
                            videoHolder.addClass(from);
                            videoHolder.removeClass(to);
                       
                        // image about to come in 
                        
                            $timeout(function() {
                                mainImg.removeClass("removeTransition");
                                mainImg.removeClass(from);
                                videoHolder.removeClass("removeTransition");
                                videoHolder.removeClass(from);
                            });
                        }
                    }); //, 250); // uncomment 250 to see the image moving to the left before it moves out.
                };

                scope.getImgSrc = function() {
                    if (scope.currentIndex !== -1 && scope.images && (scope.currentIndex<scope.images.length)) {
                        var image = scope.images[scope.currentIndex];
                        if (image) {
                            var url = image.data;
                            return url;
                        }
                    }
                };

            }, // end link

        } // end return
	}); // end itcApp.directive

}); // end define