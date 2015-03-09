package edu.dartmouth.cs.gcmdemo.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dartmouth.cs.gcmdemo.server.data.ExerciseDatastore;
import edu.dartmouth.cs.gcmdemo.server.data.ExerciseEntity;
import edu.dartmouth.cs.gcmdemo.server.data.PostDatastore;
import edu.dartmouth.cs.gcmdemo.server.data.PostEntity;

public class GetHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ArrayList<ExerciseEntity> postList = ExerciseDatastore.query();

		PrintWriter out = resp.getWriter();
		for (ExerciseEntity entity : postList) {
			out.append(entity.mDateTime + "    " + entity.id + "\n");
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}

}
