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
	
	yahooSuggestSymbols(query, displaySymbolList);
}

/**
 * Displays a list of symbols in the UI which may be selected to track.
 * 
 * @param symbols an array of symbols
 */
function displaySymbolList(symbols) {
	alert(JSON.stringify(symbols));
}

/**
 * Retries symbol suggestions for a name queried.
 * 
 * @param query the name of the stock to search for
 * @param resultsFunction the function to call with the results of the search.
 * @returns an array of the suggested symbols
 */
function yahooSuggestSymbols(query, resultsFunction) {
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

	YAHOO.Finance.SymbolSuggest.ssCallback = function (jsonData) {
		/* Parse results to an array of symbols */
		var symbols = [];
		
		var resultsArray = jsonData.ResultSet.Result;
		for (var i = 0; i < resultsArray.length; i++) {
			var result = resultsArray[i];
			/* TODO: may want to retrieve more information here to help user 
			 * choose the right one. */
			symbols.push(result.symbol);
		}
		
		/* Pass along the parsed results */
        resultsFunction(symbols);
    };
}
