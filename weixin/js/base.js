$(function(){

	$('#dialog1').hide();
	$('#dialog2').hide();
	$('#toast').hide();
	$('#loadingToast').hide();
	$('#mask').hide();
		
	$(document).on('click','#showDialog1',function(){
		toast.showDialog1();
	});
	$(document).on('click','#showDialog2',function(){
		toast.showDialog2();
	});
	$(document).on('click','#showToast',function(){
		toast.showToast();
	});
	$(document).on('click','#showLoadingToast',function(){
		toast.showLoadingToast();
	});
	$(document).on('click','#btnStartProgress',function(){
		toast.btnStartProgress();
	});
	$(document).on('click','#showActionSheet',function(){
		toast.showActionSheet();
	})

	var toast = {
		showDialog1: function(){
	        var $dialog = $('#dialog1');
            $dialog.show();
            $dialog.find('.weui_btn_dialog').one('click', function () {
                $dialog.hide();
            });
	    },
	    showDialog2: function(){
	    	var $dialog = $('#dialog2');
            $dialog.show();
            $dialog.find('.weui_btn_dialog').one('click', function () {
                $dialog.hide();
            });
	    },
	    showToast: function(){
	        var $toast = $('#toast');
            if ($toast.css('display') != 'none') {
                return;
            }
            $toast.show();
            setTimeout(function () {
                $toast.hide();
            }, 2000);
	    },
	    showLoadingToast: function(){
	    	var $loadingToast = $('#loadingToast');
            if ($loadingToast.css('display') != 'none') {
                return;
            }

            $loadingToast.show();
            setTimeout(function () {
                $loadingToast.hide();
            }, 2000);
	    },
	    btnStartProgress: function(){
	    	if ($(this).hasClass('weui_btn_disabled')) {
                return;
            }

            $(this).addClass('weui_btn_disabled');

            var progress = 0;
            var $progress = $('.js_progress');

            function next() {
                $progress.css({width: progress + '%'});
                progress = ++progress % 100;
                setTimeout(next, 30);
            }

            next();
        },
        showActionSheet: function(){
        	var mask = $('#mask');
            var weuiActionsheet = $('#weui_actionsheet');
            weuiActionsheet.addClass('weui_actionsheet_toggle');
            mask.show().addClass('weui_fade_toggle').click(function () {
                hideActionSheet(weuiActionsheet, mask);
            });
            $('#actionsheet_cancel').click(function () {
                hideActionSheet(weuiActionsheet, mask);
            });
            weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');

            function hideActionSheet(weuiActionsheet, mask) {
                weuiActionsheet.removeClass('weui_actionsheet_toggle');
                mask.removeClass('weui_fade_toggle');
                weuiActionsheet.on('transitionend', function () {
                    mask.hide();
                }).on('webkitTransitionEnd', function () {
                    mask.hide();
                })
            }
        }
	}
});