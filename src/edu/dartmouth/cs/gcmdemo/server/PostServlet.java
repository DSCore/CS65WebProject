package edu.dartmouth.cs.gcmdemo.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.PostRemove;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import edu.dartmouth.cs.gcmdemo.server.data.ExerciseDatastore;
import edu.dartmouth.cs.gcmdemo.server.data.ExerciseEntity;
import edu.dartmouth.cs.gcmdemo.server.data.PostDatastore;
import edu.dartmouth.cs.gcmdemo.server.data.PostEntity;

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String postText = req.getParameter("post_text");
		System.out.println("Received text: "+postText);
		
		this.storeJsonInDatastore(postText);
		// notify
		String from = req.getParameter("from");
		if (from == null || !from.equals("phone")) {
			resp.sendRedirect("/sendmsg.do");
		}
		if(from.equals("phone")){
			System.out.println("Calling query to update server screen");
			System.out.println("The current contents in the datastore are: "+ExerciseDatastore.query().size());
			System.out.println(ExerciseDatastore.query().toString());
			resp.sendRedirect("/query.do");
		}
	}
	
	public void storeJsonInDatastore(String text){
		//Clear out the datastore for replacement
		ExerciseDatastore.clear();
		//Convert array to a JSON array of JSON objects. 
		try {
			JSONArray jsonArray = new JSONArray(text);
			//Store all of the objects in the array into the datastore.   
			for(int i = 0; i<jsonArray.length(); i++){
				//Get the object
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				//Get the information for the EE from the object
				long id = 0;
				int inputType = 0;
				int activityType = 0;
				Date date = new Date();
				//Conversion object for converting dates between Java and MySQL
			    final java.text.SimpleDateFormat df
			            = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				String dateTime = df.format(date);
				int duration = 0;
				double distance = 0;
				double avgspeed = 0;
				int calories = 0;
				double climb = 0;
				int heartRate = 0;
				String comment = "";
				String locations = "";
				ArrayList<String> pictures = new ArrayList<>();
//				byte[] pictures;
				
				try{					
					id = Long.parseLong(jsonObject.get("id").toString());
				}
				catch(com.google.appengine.labs.repackaged.org.json.JSONException je){
					System.out.println("One of the fields wasn't "
							+ "passed since it was null. Using default values instead.");
				}
				
				try{					
					locations = (String)jsonObject.get("listEE");
					System.out.println("locations is: "+locations);
				}
				catch(com.google.appengine.labs.repackaged.org.json.JSONException je){
					System.out.println("One of the fields wasn't "
							+ "passed since it was null. Using default values instead.");
				}
				
				try{					
					dateTime = (String)jsonObject.get("timeStamp");
				}
				catch(com.google.appengine.labs.repackaged.org.json.JSONException je){
					System.out.println("One of the fields wasn't "
							+ "passed since it was null. Using default values instead.");
				}
				
				try{					
					comment = (String)jsonObject.get("name");
				}
				catch(com.google.appengine.labs.repackaged.org.json.JSONException je){
					System.out.println("One of the fields wasn't "
							+ "passed since it was null. Using default values instead.");
				}
				
				try{
					//Store the image representation of the string on the server
					pictures.add((String)jsonObject.get("pictures"));
					
					
					
//					for(int i1=0;i1<pictures.size();i1++){
//						Blob blob = new Blob(pictures.get(i1).getBytes());
//					}
//					byte[] bytes = Base64.decode(pictures);
//					byte[] bytes = pictures.getBytes();
//					byte[] bytes = ((String)jsonObject.get("pictures")).getBytes();
//					//Parse to image.
//					ImagesService imagesService = ImagesServiceFactory.getImagesService();
//
//			        Image oldImage = ImagesServiceFactory.makeImage(bytes);
//			        Transform resize = ImagesServiceFactory.makeResize(200, 300);

//			        Image newImage = imagesService.applyTransform(resize, oldImage);
//			        System.out.println("The type of image is: "+oldImage.getFormat().toString());
					
					//Store the blob in the Excursion;
//					Blob blob = new Blob(bytes);
				}
				catch(com.google.appengine.labs.repackaged.org.json.JSONException je){
					System.out.println("One of the fields wasn't "
							+ "passed since it was null. Using default values instead.");
				}
				
				//Build the EE
				ExerciseEntity ee = new ExerciseEntity(
						id,
						inputType,
						activityType,
						dateTime,
						duration,
						distance,
						avgspeed,
						calories,
						climb,
						heartRate,
						comment,
						locations,
						pictures);
				//Add the EE to the ExerciseDataStore
				ExerciseDatastore.add(ee);
			}
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to convert text to JSON!");
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}
}
