require({
	baseUrl : '/js/stocks/',
	priority : [ 'def' ]
}, [ "plugin" ], function() {
	require.ready(function() {
		init();
	});
})