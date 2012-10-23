/** 
 * Handles the various data retrieval operations needed by the application.
 * 
 * @author drusk
 */

$(document).ready(initEventHandlers);

/**
 * Initializes the application's UI event handlers.
 */
function initEventHandlers() {
	$("#search-submit").click(searchSubmitHandler);
}

/**
 * Handles events when the user submits a search.
 */
function searchSubmitHandler() {
	var query = $("#search-input").val();
	alert(query);
	
	if (query.length == 0) {
		/* No query has been entered, do nothing */
		return;
	}
	
	var symbols = yahooSuggestSymbols(query);
	
}

/**
 * Retries symbol suggestions for a name queried.
 * 
 * @param query
 */
function yahooSuggestSymbols(query) {
	/* The callback doesn't work if YAHOO isn't global. */
	window.YAHOO = {
		Finance: {
			SymbolSuggest: {}
		}
	};
	
	$.ajax({
		type: "GET",
		url: "http://d.yimg.com/autoc.finance.yahoo.com/autoc",
		data: {query: query},
		dataType: "jsonp",
		jsonp: "callback",
		jsonpCallback: "YAHOO.Finance.SymbolSuggest.ssCallback",
	});

	YAHOO.Finance.SymbolSuggest.ssCallback = function (data) {
        alert(JSON.stringify(data));
    };
}
