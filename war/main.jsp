<%@page import="edu.dartmouth.cs.gcmdemo.server.data.ExcursionEntity"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post</title>
</head>
<body>
	<b>Exercise Entries List</b>
	<table border="1">
		<tr>
			<td>Excursion ID</td>
			<td>Name</td>
			<td>Share Time</td>
			<td>Locations:</td>
		</tr>
				<%
					ArrayList<ExcursionEntity> listEE = (ArrayList<ExcursionEntity>) request
							.getAttribute("postList");
					for (ExcursionEntity ee : listEE) {
				%> 
		<tr>
			<td><%=""+ee.id%></td>
			<td><%=""+ee.mComment%></td>
			<td><%=""+ee.mDateTime%></td>
			<td><%=""+ee.mLocationList%></td>
			
		</tr>
				<%
					}
				%>
		</tr>

		</tr>
	</table>

</body>
</html>
