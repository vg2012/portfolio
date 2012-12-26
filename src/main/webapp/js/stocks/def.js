init = function load_all_presets() {
	var inData = {
		ajaxHandler : 'holdings',
		resultsFunc : loadStockArea,
		data : null,
		showWait : true,
		waitNode : "#wait",
		hideNode : null,
		chain:[pagingSetup]
	};

	_register_service_action(inData);
	
	if ('$("#ticker")') {
		$("#ticker").blur(function(){
		 validateTicker();
		});
	}
	
	$("#stockDetailSaveAction").click(function() {
		var isValid;
		if ( $('#stockDetailSaveForm').jVal({style:'blank',padding:12,border:0,wrap:false}) )
			isValid = true;
		else
			isValid = false;
		if (isValid) {
			addStockDetails();
		}
	});

};

loadStockGridSection = function get_holding_list() {
	var holdingData = {
			ajaxHandler : 'holdings',
			resultsFunc : loadStockArea,
			data : null,
			showWait : true,
			waitNode : "#wait",
			hideNode : "#holdingGrid",
			chain:[pagingSetup]
		};
	_register_service_action(holdingData);
}

 function addStockDetails() {
	var data = $("#stockDetailSaveForm").serialize();
	var save = {
		ajaxHandler : 'addStock',
		resultsFunc : loadStockGridSection,
		data : data,
		showWait : false,
		waitNode : null,
		hideNode : null
	};
	_register_service_action(save);
}

validateTicker = function stockTickerCheck() {
	 $('#valid').html('<img src="'+context_get_main_section_div()+'/images/ajax-loader.gif"/>' + ' Validating ...');
	 var s_ticker = $("#ticker").val();
	 $.ajax({
		url :  context_get_main_section_div() +"/validateTicker",
		data : {ticker : s_ticker},
		success: function(msg) {
          var delay = function() {
              AjaxSucceeded(msg);
           };
            setTimeout(delay, 2000);
         },

         error: AjaxFailed
	});
}
	

pagingSetup = function load_initPagination() {
	if("#Pagination"){
		  $("#Pagination").pagination( $('#numOfPageChunks').attr("value"), {
	          num_edge_entries: 0,
	          num_display_entries: 3,
	          callback: null,
	          items_per_page:1,
	          prev_show_always:false,
	          next_show_always:false,
	          next_text : ">",
	          prev_text : "<",
	          callback  : paginationHandler
	      });
		  
	}   
}

paginationHandler = function handlePaginationClick(new_page_index, pagination_container) {
    // This selects 5 elements from a content array
   $("#nextIdx").attr("value", new_page_index+1);
   var ajaxReq;
     ajaxReq = {ajaxHandler: "holdingsPaged", 
			   resultsFunc: loadStockArea,
			   data : $('#paginationHandler').serialize(),
			   showWait : true,
			   waitNode : "#wait",
			   hideNode : "#holdingGrid"};
  
	_register_service_action(ajaxReq);
    return false;
}


function AjaxSucceeded(result) {
	 $('#valid').html(result);
	if (result == "") {
		 $('#stockDetailSaveAction').removeAttr("disabled");
	} else {
		 $('#stockDetailSaveAction').attr("disabled", "disabled");
	}
    
}

function AjaxFailed(result) {

    alert(result.status + + result.statusText);

}    

function loadStockArea(html){
      _doCommonScreenLoad('#holdingGrid',html);
};

