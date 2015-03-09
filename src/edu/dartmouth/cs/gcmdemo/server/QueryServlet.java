package edu.dartmouth.cs.gcmdemo.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dartmouth.cs.gcmdemo.server.data.ExcursionDatastore;
import edu.dartmouth.cs.gcmdemo.server.data.ExcursionEntity;
import edu.dartmouth.cs.gcmdemo.server.data.PostDatastore;
import edu.dartmouth.cs.gcmdemo.server.data.PostEntity;

public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ArrayList<ExcursionEntity> postList = ExcursionDatastore.query();
		
		//Add the list of ExerciseEntity to the request to send to the querier
		req.setAttribute("postList", postList);
				
		getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}
	
}
