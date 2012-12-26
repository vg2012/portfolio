/**
 * 
 */
var _main_section_content_div = "";
function context_get_main_section_div() {
	var qs, t, qstring;
	var process = false;
	for ( var i = 0; i < document.getElementsByTagName("script").length; i++) {
		if (document.getElementsByTagName("script")[i].src.match(/\w+=/g)) {
			qs = document.getElementsByTagName("script")[i].src
					.match(/\w+=\/\w+/g), qstring = {}, t, i = qs.length;
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
require(
		{
			baseUrl : context_get_main_section_div() + '/'
					+ 'js/plugins/jquery/1.4.3/',
			priority : [ 'jquery-1.4.3.min' ]
		},
		[
				,
				context_get_main_section_div() + '/'
						+ 'js/pfm/core/krn/sys/lwapi.js',
				context_get_main_section_div() + '/'
						+ 'js/plugins/jquery.pagination.js',
				context_get_main_section_div() + '/'
						+ 'js/plugins/ui/tree/jquery.cookie.js',
				context_get_main_section_div()
						+ '/'
						+ 'js/plugins/ajax/0.0.1/ersAjaxEngine.0.0.1.js?cs=section_content',
				context_get_main_section_div() + '/'
						+ 'js/stocks/jquery.validate.min.js',
				context_get_main_section_div() + '/' + 'js/plugins/jVal.js' ],
		function() {
			setTimeout(function() {
				require([ context_get_main_section_div() + '/'
						+ 'js/stocks/main.js' ])
			}, 1300);
		});