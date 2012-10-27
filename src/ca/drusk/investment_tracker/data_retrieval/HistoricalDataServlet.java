package ca.drusk.investment_tracker.data_retrieval;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class HistoricalDataLookup
 * 
 * Retrieves historical data for a specified symbol.
 * 
 * @author drusk
 * 
 */
@WebServlet("/HistoricalData")
public class HistoricalDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private YahooHistoricalDataFetcher dataFetcher = new YahooHistoricalDataFetcher();

	private Gson gson = new Gson();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoricalDataServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String queriedSymbol = request.getParameter("symbol");
		assert queriedSymbol != null : "query symbol was null!";

		response.setContentType("application/json");
		
		try {
			List<YahooDataBean> beans = dataFetcher
					.fetchDataForSymbol(queriedSymbol);
			Type type = new TypeToken<List<YahooDataBean>>(){}.getType();
			PrintWriter out = response.getWriter();
			out.write(gson.toJson(beans, type));

		} catch (ParseException e) {
			e.printStackTrace();
			// TODO return an error code
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* Does nothing */
	}

}
