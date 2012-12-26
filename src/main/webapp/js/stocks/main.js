require({
	baseUrl : context_get_main_section_div() + '/' + 'js/stocks/',
	priority : [ 'def' ]
}, [ "plugin" ], function() {
	require.ready(function() {
		init();
	});
})