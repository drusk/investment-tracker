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
