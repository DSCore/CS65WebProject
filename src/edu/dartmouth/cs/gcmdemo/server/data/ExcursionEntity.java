package edu.dartmouth.cs.gcmdemo.server.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExcursionEntity {
	public static String ENTITY_KIND_PARENT = "PostParent";
	public static String ENTITY_PARENT_KEY = ENTITY_KIND_PARENT;
	public static String ENTITY_KIND_POST = "Post";

	public static String FIELD_NAME_ID = "id";
	public static String FIELD_NAME_INPUT_TYPE = "mInputType";
	public static String FIELD_NAME_ACTIVITY_TYPE = "mActivityType";
	public static String FIELD_NAME_DATE_TIME = "mDateTime";
	public static String FIELD_NAME_DURATION = "mDuration";
	public static String FIELD_NAME_DISTANCE = "mDistance";
	public static String FIELD_NAME_AVG_PACE = "mAvgPace";
	public static String FIELD_NAME_AVG_SPEED = "mAvgSpeed";
	public static String FIELD_NAME_SPEED = "speed";
	public static String FIELD_NAME_CALORIE = "mCalorie";
	public static String FIELD_NAME_CLIMB = "mClimb";
	public static String FIELD_NAME_HEARTRATE = "mHeartRate";
	public static String FIELD_NAME_COMMENT = "mComment";
	public static String FIELD_NAME_LOCATIONS = "mLocationList";
	public static String FIELD_NAME_PICTURES = "mPictures";
	
    public Long id;
    public int mInputType;        // Manual, GPS or automatic
    public int mActivityType;     // Running, cycling etc.
    public String mDateTime;    // When does this entry happen
    public int mDuration;         // Exercise duration in seconds
    public double mDistance;      // Distance traveled. Either in meters or feet.
    public double mAvgPace;       // Average pace
    public double mAvgSpeed;      // Average speed
    public double mSpeed;
    public int mCalorie;          // Calories burnt
    public double mClimb;         // Climb. Either in meters or feet.
    public int mHeartRate = 85;   // Heart rate
    public String mComment;       // Comments
    public int mPrivacy;          // Privacy
    public String mLocationList;  // String representation of the locationList
    public ArrayList<String> mPictures; //String representation of the pictures

	public ExcursionEntity() {
	}

	public ExcursionEntity(
			Long id, 
			int inputType, 
			int activityType, 
			String dateTime,
			int duration,
			double distance,
			double avgPace,
			double avgSpeed,
			double speed, 
			int calories,
			double climb, 
			int heartRate,
			String comment,
			String locations
			) {
		this.id = id;
		this.mInputType = inputType;
		this.mActivityType = activityType;
		this.mDateTime = dateTime;
		this.mDuration = duration;
		this.mDistance = distance;
		this.mAvgPace = avgPace;
		this.mAvgSpeed = avgSpeed;
		this.mSpeed = speed;
		this.mCalorie = calories;
		this.mHeartRate = heartRate;
		this.mComment = comment;
		this.mLocationList = locations;
				
	}
	
	public ExcursionEntity(long id, int inputType, int activityType, String dateTime, 
			int duration, double distance, double avgSpeed, int calorie, 
			double climb, int heartRate, String comment, String locations, ArrayList<String> pictures){
		this.id = id;
		this.mInputType = inputType;
		this.mActivityType = activityType;
		this.mDateTime = dateTime;
		this.mDuration = duration;
		this.mDistance = distance;
		this.mAvgSpeed = avgSpeed; 
		this.mCalorie = calorie;
		this.mClimb = climb;
		this.mHeartRate = heartRate;
		this.mComment = comment;
		this.mLocationList = locations;
		this.mPictures = pictures;
	}
}
