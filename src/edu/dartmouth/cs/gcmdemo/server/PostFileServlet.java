//package edu.dartmouth.cs.gcmdemo.server;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import javax.persistence.PostRemove;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.appengine.api.files.AppEngineFile;
//import com.google.appengine.api.files.FileService;
//import com.google.appengine.api.files.FileServiceFactory;
//import com.google.appengine.api.images.ImagesService;
//import com.google.appengine.api.images.ImagesServiceFactory;
//import com.google.appengine.api.images;
//import com.google.appengine.labs.repackaged.org.json.JSONArray;
//import com.google.appengine.labs.repackaged.org.json.JSONException;
//import com.google.appengine.labs.repackaged.org.json.JSONObject;
//
//import edu.dartmouth.cs.gcmdemo.server.data.ExerciseDatastore;
//import edu.dartmouth.cs.gcmdemo.server.data.ExerciseEntity;
//import edu.dartmouth.cs.gcmdemo.server.data.PostDatastore;
//import edu.dartmouth.cs.gcmdemo.server.data.PostEntity;
//
//public class PostFileServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws IOException, ServletException {
//		
//		try {
//			InputStream stream = req.getInputStream();
//			
//			//Use the files API to save this image to a file
//			// Get a file service
//			FileService fileService = FileServiceFactory.getFileService();
//			ImagesService imageService = ImagesServiceFactory.getImagesService();
//			// Create a new Blob file with mime-type "text/plain"
//			AppEngineFile file = fileService.createNewBlobFile("text/plain");
//
//			// Open a channel to write to it
//			boolean lock = false;
//			FileWriteChannel writeChannel = fileService.openWriteChannel(file, lock);
//
//			// Different standard Java ways of writing to the channel
//			// are possible. Here we use a PrintWriter:
//			PrintWriter out = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));
//			out.println("The woods are lovely dark and deep.");
//			out.println("But I have promises to keep.");
//
//			// Close without finalizing and save the file path for writing later
//			out.close();
//			String path = file.getFullPath();
//
//			// Write more to the file in a separate request:
//			file = new AppEngineFile(path);
//
//			// This time lock because we intend to finalize
//			lock = true;
//			writeChannel = fileService.openWriteChannel(file, lock);
//
//			// This time we write to the channel directly
//			writeChannel.write(ByteBuffer.wrap
//			          ("And miles to go before I sleep.".getBytes()));
//
//			// Now finalize
//			writeChannel.closeFinally();
//
//	 
//		} catch (Exception ex) {
//			throw new ServletException(ex);
//		}
//		
////		String postText = req.getParameter("post_text");
////		System.out.println("Received text: "+postText);
////		
////		this.storeJsonInDatastore(postText);
////		// notify
////		String from = req.getParameter("from");
////		if (from == null || !from.equals("phone")) {
////			resp.sendRedirect("/sendmsg.do");
////		}
////		if(from.equals("phone")){
////			System.out.println("Calling query to update server screen");
////			System.out.println("The current contents in the datastore are: "+ExerciseDatastore.query().size());
////			System.out.println(ExerciseDatastore.query().toString());
////			resp.sendRedirect("/query.do");
////		}
//	}
//	
//	public void storeJsonInDatastore(String text){
//		//Clear out the datastore for replacement
//		ExerciseDatastore.clear();
//		//Convert array to a JSON array of JSON objects. 
//		try {
//			JSONArray jsonArray = new JSONArray(text);
//			//Store all of the objects in the array into the datastore.   
//			for(int i = 0; i<jsonArray.length(); i++){
//				//Get the object
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				//Get the information for the EE from the object
//				long id = 0;
//				int inputType = 0;
//				int activityType = 0;
//				Date date = new Date();
//				//Conversion object for converting dates between Java and MySQL
//			    final java.text.SimpleDateFormat df
//			            = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				String dateTime = df.format(date);
//				int duration = 0;
//				double distance = 0;
//				double avgspeed = 0;
//				int calories = 0;
//				double climb = 0;
//				int heartRate = 0;
//				String comment = "";
//				
//				try{					
//					id = Long.parseLong(jsonObject.get("id").toString());
////					inputType = (int)jsonObject.get("inputType");
////					activityType = (int)jsonObject.get("activityType");
//					dateTime = (String)jsonObject.get("timeStamp");
////					duration = (int)jsonObject.get("duration");
////					distance = Double.parseDouble(jsonObject.get("distance").toString());
////					avgspeed = Double.parseDouble(jsonObject.get("avgSpeed").toString());
////					calories = (int)jsonObject.get("calories");
////					climb = Double.parseDouble(jsonObject.get("climb").toString());
////					heartRate = (int)jsonObject.get("heartRate");
//					comment = (String)jsonObject.get("name");
//				}
//				catch(com.google.appengine.labs.repackaged.org.json.JSONException je){
//					System.out.println("One of the fields wasn't "
//							+ "passed since it was null. Using default values instead.");
//				}
//				//Build the EE
//				ExerciseEntity ee = new ExerciseEntity(
//						id,
//						inputType,
//						activityType,
//						dateTime,
//						duration,
//						distance,
//						avgspeed,
//						calories,
//						climb,
//						heartRate,
//						comment);
//				//Add the EE to the ExerciseDataStore
//				ExerciseDatastore.add(ee);
//			}
//		} 
//		catch (JSONException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Failed to convert text to JSON!");
//			e.printStackTrace();
//		}
//	}
//
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws IOException, ServletException {
//		doPost(req, resp);
//	}
//}
