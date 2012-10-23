/** 
 * Handles the various data retrieval operations needed by the application.
 * 
 * @author drusk
 */

$(document).ready(initEventHandlers);

function initEventHandlers() {
	$("#search-submit").click(searchSubmitHandler);
}

function searchSubmitHandler() {
	var query = $("#search-input").val();
	alert(query);
}
