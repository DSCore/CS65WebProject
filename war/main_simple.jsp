<%@page import="edu.dartmouth.cs.gcmdemo.server.data.ExcursionEntity"%>
<%@page import="java.util.*"%>

	<%
		ArrayList<ExcursionEntity> listEE = (ArrayList<ExcursionEntity>) request
				.getAttribute("postList");
		for (ExcursionEntity ee : listEE) {
	%> 
			
	<%=""+ee.mLocationList%>
	
	<%}%>