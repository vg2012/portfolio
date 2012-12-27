require({
			baseUrl : '/js/plugins/jquery/1.4.3/',
				priority : [ 'jquery-1.4.3.min' ]
		},
		['/js/pfm/core/krn/sys/lwapi.js',
		 '/js/plugins/jquery.pagination.js',
		 '/js/plugins/ui/tree/jquery.cookie.js',
		 '/js/plugins/ajax/0.0.1/ersAjaxEngine.0.0.1.js',
		 '/js/stocks/jquery.validate.min.js',
		 '/js/plugins/jVal.js' ],
		 function() {
			setTimeout(function() {
				require(['/js/stocks/main.js' ])
			}, 1300);
		});