<%@page import="edu.dartmouth.cs.gcmdemo.server.data.ExerciseEntity"%>
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
			<td>ID</td>
			<td>Input Type</td>
			<td>Activity Type</td>
			<td>Date Time</td>
			<td>Duration</td>
			<td>Distance</td>
			<td>Average Speed</td>
			<td>Calories</td>
			<td>Climb</td>
			<td>Heart Rate</td>
			<td>Comment</td>
			<td>Locations:</td>
			<td><!--DeleteButton--></td>
		</tr>
				<%
					ArrayList<ExerciseEntity> listEE = (ArrayList<ExerciseEntity>) request
							.getAttribute("postList");
					for (ExerciseEntity ee : listEE) {
				%> 
		<tr>
			<td><%=""+ee.id%></td>
			<%
				String inputType = "";
				switch(ee.mInputType){
					case 0:
						inputType = "Manual Entry";
						break;
					case 1:
						inputType = "GPS";
						break;
					case 2:
						inputType = "Automatic";
						break;
					default:
						inputType = "Default";
						break;
				}
			%>
			<td><%=""+inputType%></td>
			<%
				String activityType = "";
				switch(ee.mActivityType){
					case 0:
						activityType = "Running";
						break;
					case 1:
						activityType = "Walking";
						break;
					case 2:
						activityType = "Standing";
						break;
					case 3:
						activityType = "Cycling";
						break;
					case 4:
						activityType = "Hiking";
						break;
					case 5:
						activityType = "Downhill Skiing";
						break;
					case 6:
						activityType = "Cross-Country Skiing";
						break;
					case 7:
						activityType = "Snowboarding";
						break;
					case 8:
						activityType = "Skating";
						break;
					case 9:
						activityType = "Swimming";
						break;
					case 10:
						activityType = "Mountain Biking";
						break;
					case 11:
						activityType = "Wheelchair";
						break;
					case 12:
						activityType = "Elliptical";
						break;
					default:
						activityType = "Other";
						break;
				}
			%>
			<td><%=""+activityType%></td>
			<td><%=""+ee.mDateTime%></td>
			<td><%=""+ee.mDuration+" minutes"%></td>
			<td><%=""+ee.mDistance+" miles"%></td>
			<td><%=""+ee.mAvgSpeed%></td>
			<td><%=""+ee.mCalorie%></td>
			<td><%=""+ee.mClimb%></td>
			<td><%=""+ee.mHeartRate%></td>
			<td><%=""+ee.mComment%></td>
			<td><%=""+ee.mLocationList%></td>
			<td>
				<form name="delete" action="sendmsg.do" method="post">
					<input type="hidden" name="id" value= <%=""+ee.id%> >
					<input type="submit" value="Delete">
				</form>
			</td>
			
		</tr>
				<%
					}
				%>
		</tr>

		</tr>
	</table>

</body>
</html>
