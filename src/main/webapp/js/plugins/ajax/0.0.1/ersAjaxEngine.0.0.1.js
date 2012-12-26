/**
 * 
 */

var _main_section_content_div = "";

$(document).ready(function() {
	_main_section_content_div = get_main_section_div();
	_ersAjaxEngineCommonTabListener();
});

var commonUserRequest = $.manageAjax.create('commonAjaxServices', {
	queue : true,
	cacheResponse : false,
	preventDoubleRequests : true,
	maxRequests : 2,
	type : "POST"
});

function _register_service_action(ajRequestData) {
	commonUserRequest.add(ajRequestData.ajaxHandler, {
		success : function(html) {
			if (ajRequestData.additionResultsParams != null) {
				if (ajRequestData.additionResultsParams.arg1 != null) {
					ajRequestData.resultsFunc.call(this, html,
							ajRequestData.additionResultsParams.arg1);
				}
			} else {
				ajRequestData.resultsFunc.call(this, html);
			}
		},
		complete : function() {
			if (ajRequestData.chain != null) {
				for ( var i = 0; i < ajRequestData.chain.length; i++) {
					if (ajRequestData.chainArgs
							&& ajRequestData.chainArgs.length > 0) {
						ajRequestData.chain[i].call(this,
								ajRequestData.chainArgs[0]);
					} else {
						ajRequestData.chain[i].call(this);
					}
				}
			}
			if (ajRequestData.showWait) {
				if ($(ajRequestData.waitNode)) {
					$(ajRequestData.waitNode).html('');
				}
			}
		},
		data : ajRequestData.data != null ? ajRequestData.data : {},
		url : 'user.html'
	});
	if (ajRequestData.showWait) {
		$(ajRequestData.waitNode).html(
				'<img src="' + context_get_main_section_div()
						+ '/images/ajax-loader.gif"/>');
	}
	if (ajRequestData.hideNode) {
		$(ajRequestData.hideNode).hide();
	}
	return false;
}

function get_main_section_div() {
	var qs, t, qstring;
	var process = false;
	for ( var i = 0; i < document.getElementsByTagName("script").length; i++) {
		if (document.getElementsByTagName("script")[i].src.match(/\w+=\w+/g)) {
			qs = document.getElementsByTagName("script")[i].src
					.match(/\w+=\w+/g), qstring = {}, t, i = qs.length;
			process = true;
		}
		if (process) {
			while (i--) {
				t = qs[i].split("=");
				qstring[t[0]] = t[1];
			}
			return qstring.cs;
		}
	}
}

function _ersAjaxEngineCommonTabListener() {
	$('a[href=' + document.location.hash + ']').addClass('current');
	$('a[rel=ajax]').click(function() {
		var hash = this.href;
		hash = hash.replace(/^.*#/, '');
		// check parent nodes first, then href nodes

		$('a[rel=ajax]').removeClass('current');
		$(this).addClass('current');
		$('#' + _main_section_content_div).hide();

		ers.getPage(hash);
		return false;
	});
}

function _ersAjaxEngineCommonInnerTabListener() {
	$('a[href=' + document.location.hash + ']').addClass('current');
	$('a[rel=ajax_inner]').click(function() {
		var hash = this.href;
		hash = hash.replace(/^.*#/, '');
		$('a[rel=ajax_inner]').removeClass('current');
		$(this).addClass('current');
		$('#' + _main_section_content_div).hide();
		ers.getInnerPage(hash);
		return false;
	});
}

function _doCommonScreenLoad(section, htmlData) {
	$(section).html(htmlData);
	//display the body with fadeIn transition
	$(section).fadeIn('slow');

	if (arguments[2] != null) {
		if ($('#source') != null) {
			$('#source').attr('value', arguments[2]);
		}
	}
}

function _param(args) {
	return args[0] != null ? args[0] : null;
}

function _params(args, idx) {
	return args[idx] != null ? args[idx] : null;
}

function _commonAlertMessageTrigger() {
	$('#ex6b').jqm(
			{
				ajax : context_get_main_section_div()
						+ '/access/confirmation_message_1',
				target : 'div.jqmAlertContent',
				modal : true,
				overlay : 100
			});

	// Close Button Highlighting. IE doesn't support :hover. Surprise?
	if ($.browser.msie) {
		$('div.jqmAlert .jqmClose').hover(function() {
			$(this).addClass('jqmCloseHover');
		}, function() {
			$(this).removeClass('jqmCloseHover');
		});
	}
	$('#ex6b').jqmShow();
}

function _commonErrorMessageListener(type) {
	var type = _param(arguments);
	if (type) {
		var triggers = $(type)[0];
		$('#ex3b').jqm({
			trigger : triggers,
			ajax : context_get_main_section_div() + '/access/alertmessage',
			target : 'div.jqmAlertContent',
			overlay : 100
		});

		// Close Button Highlighting. IE doesn't support :hover. Surprise?
		if ($.browser.msie) {
			$('div.jqmAlert .jqmClose').hover(function() {
				$(this).addClass('jqmCloseHover');
			}, function() {
				$(this).removeClass('jqmCloseHover');
			});
		}
	}
}

function _commonErrorMessageListenerMulti(type, holder) {
	if (type) {
		var triggers = $(type)[0];
		$(holder).jqm({
			trigger : triggers,
			ajax : context_get_main_section_div() + '/access/alertmessage',
			target : 'div.jqmAlertContent',
			modal : true,
			overlay : 100
		});

		// Close Button Highlighting. IE doesn't support :hover. Surprise?
		if ($.browser.msie) {
			$('div.jqmAlert .jqmClose').hover(function() {
				$(this).addClass('jqmCloseHover');
			}, function() {
				$(this).removeClass('jqmCloseHover');
			});
		}
	}
}

function _doServerCommunications(action, aResultsFunc, formId, showWait,
		waitNode, hideNode) {
	var commRequest = {
		ajaxHandler : action,
		resultsFunc : aResultsFunc,
		data : $(formId).serialize(),
		showWait : showWait,
		waitNode : waitNode,
		hideNode : hideNode
	};
	_register_service_action(commRequest);
}

function _commonSaveAlertMessageTrigger() {
	$('#ex6b').jqm(
			{
				ajax : context_get_main_section_div()
						+ '/access/save_confirmation_message_1',
				target : 'div.jqmAlertContent',
				modal : true,
				overlay : 100
			});

	// Close Button Highlighting. IE doesn't support :hover. Surprise?
	if ($.browser.msie) {
		$('div.jqmAlert .jqmClose').hover(function() {
			$(this).addClass('jqmCloseHover');
		}, function() {
			$(this).removeClass('jqmCloseHover');
		});
	}
	$('#ex6b').jqmShow();
}
