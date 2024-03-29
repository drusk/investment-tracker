/******************************************************************************
 * Copyright (C) 2012 David Rusk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *****************************************************************************/
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
	if (query.length == 0) {
		/* No query has been entered, do nothing */
		return;
	}

	yahooSuggestSymbols(query, displaySymbolList);
}

/**
 * Retries symbol suggestions for a name queried.
 * 
 * @param query
 *            the name of the stock to search for
 * @param resultsFunction
 *            the function to call with the results of the search.
 * @returns an array of the suggested symbols
 */
function yahooSuggestSymbols(query, resultsFunction) {
	/* The callback doesn't work if YAHOO isn't global. */
	window.YAHOO = {
		Finance : {
			SymbolSuggest : {}
		}
	};

	$.ajax({
		type : "GET",
		url : "http://d.yimg.com/autoc.finance.yahoo.com/autoc",
		data : {
			query : query
		},
		dataType : "jsonp",
		jsonp : "callback",
		jsonpCallback : "YAHOO.Finance.SymbolSuggest.ssCallback",
	});

	YAHOO.Finance.SymbolSuggest.ssCallback = function(jsonData) {
		/* Pass along the relevant data */
		resultsFunction(jsonData.ResultSet.Result);
	};
}

/**
 * Displays a list of symbols and their related info in the UI. Provide ability
 * for them to be selected for tracking.
 * 
 * @param suggestions
 *            an array of suggestions
 */
function displaySymbolList(suggestions) {
	var resultsList = $("#search-results");

	/* Clear any existing search results */
	resultsList.empty();

	for ( var i = 0; i < suggestions.length; i++) {
		var suggestion = suggestions[i];
		var domId = "suggestion-" + i;

		/* The information about the suggestion to be displayed to the user */
		var displayInfo = suggestion.symbol + ": " + suggestion.name;
		resultsList.append("<li><a href=\"#page-plot\" data-role=\"button\" "
				+ "id=\"" + domId + "\">" + displayInfo + "</a>");

		/*
		 * Attach an event handler to fire if the suggestion is selected for for
		 * tracking.
		 */
		$("#" + domId).click({
			symbol : suggestion.symbol
		}, function(event) {
			retrieveSymbolData(event.data.symbol);
		});
	}

	/*
	 * Need to refresh the list in order for the new items to be styled
	 * correctly.
	 */
	resultsList.listview("refresh");
}

/**
 * Retrieves all available data for a specified symbol.
 * 
 * @param symbol
 *            the symbol to look up. Ex: GOOG for Google Inc.
 */
function retrieveSymbolData(symbol) {
	// download data for symbol
	$.ajax({
		url : "HistoricalData",
		data : {
			symbol : symbol
		},
		success : generatePlot,
		dataType : "json"
	});
}

/**
 * Generates a plot of the provided JSON data.
 * 
 * @param json
 *            the JSON data to plot.
 */
function generatePlot(json) {
	/* Select the data to be plotted from the data returned. */
	var plotData = [];
	for ( var i = 0; i < json.length; i++) {
		var dataPoint = json[i];
		plotData.push([ Date.parse(dataPoint.date), dataPoint.close ]);
	}
	/*
	 * XXX can Dygraph sort the dates for me automatically? They are being
	 * parsed in reverse order, but it would be best if the plot didn't care the
	 * order.
	 */
	plotData.reverse();
	new Dygraph(document.getElementById("plot-holder"), plotData, {
		labels : [ "Date", "Closing price" ]
	});
}
