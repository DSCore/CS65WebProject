package edu.dartmouth.cs.gcmdemo.server.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.metamodel.EntityType;

import org.mortbay.log.Log;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

public class ExerciseDatastore {
	private static final DatastoreService mDatastore = DatastoreServiceFactory
			.getDatastoreService();

	private static Key getParentKey() {
		return KeyFactory.createKey(ExerciseEntity.ENTITY_KIND_PARENT,
				ExerciseEntity.ENTITY_PARENT_KEY);
	}

	private static void createParentEntity() {
		Entity entity = new Entity(getParentKey());

		mDatastore.put(entity);
	}

	public static boolean add(ExerciseEntity post) {
		Key parentKey = getParentKey();
		try {
			mDatastore.get(parentKey);
		} catch (Exception ex) {
			createParentEntity();
		}

		Entity entity = new Entity(ExerciseEntity.ENTITY_KIND_POST, post.id,
				parentKey);

		entity.setProperty(ExerciseEntity.FIELD_NAME_ID, post.id);
		entity.setProperty(ExerciseEntity.FIELD_NAME_INPUT_TYPE,
				post.mInputType);
		entity.setProperty(ExerciseEntity.FIELD_NAME_ACTIVITY_TYPE,
				post.mActivityType);
		entity.setProperty(ExerciseEntity.FIELD_NAME_DATE_TIME, post.mDateTime);
		entity.setProperty(ExerciseEntity.FIELD_NAME_DURATION, post.mDuration);
		entity.setProperty(ExerciseEntity.FIELD_NAME_DISTANCE, post.mDistance);
		entity.setProperty(ExerciseEntity.FIELD_NAME_AVG_SPEED, post.mAvgSpeed);
		entity.setProperty(ExerciseEntity.FIELD_NAME_CALORIE, post.mCalorie);
		entity.setProperty(ExerciseEntity.FIELD_NAME_CLIMB, post.mClimb);
		entity.setProperty(ExerciseEntity.FIELD_NAME_HEARTRATE, post.mHeartRate);
		entity.setProperty(ExerciseEntity.FIELD_NAME_COMMENT, post.mComment);
		entity.setProperty(ExerciseEntity.FIELD_NAME_LOCATIONS, post.mLocationList);
		entity.setProperty(ExerciseEntity.FIELD_NAME_PICTURES, post.mPictures);

		mDatastore.put(entity);

		return true;
	}

	public static ArrayList<ExerciseEntity> query() {
		ArrayList<ExerciseEntity> resultList = new ArrayList<ExerciseEntity>();

		// Fetch the data from the datastore
		Query query = new Query(ExerciseEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExerciseEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);

		// Put the fetched data in the resultList
		for (Entity entity : pq.asIterable()) {
			ExerciseEntity post = new ExerciseEntity();

			post.id = (long) entity.getProperty(ExerciseEntity.FIELD_NAME_ID);
			post.mInputType = Integer.parseInt(entity.getProperty(
					ExerciseEntity.FIELD_NAME_INPUT_TYPE).toString());
			post.mActivityType = Integer.parseInt(entity.getProperty(
					ExerciseEntity.FIELD_NAME_ACTIVITY_TYPE).toString());
			post.mDateTime = (String) entity
					.getProperty(ExerciseEntity.FIELD_NAME_DATE_TIME);
			post.mDuration = Integer.parseInt(entity.getProperty(
					ExerciseEntity.FIELD_NAME_DURATION).toString());
			post.mDistance = (double) entity
					.getProperty(ExerciseEntity.FIELD_NAME_DISTANCE);
			post.mAvgSpeed = (double) entity
					.getProperty(ExerciseEntity.FIELD_NAME_AVG_SPEED);
			post.mCalorie = Integer.parseInt(entity.getProperty(
					ExerciseEntity.FIELD_NAME_CALORIE).toString());
			post.mClimb = (double) entity
					.getProperty(ExerciseEntity.FIELD_NAME_CLIMB);
			post.mHeartRate = Integer.parseInt(entity.getProperty(
					ExerciseEntity.FIELD_NAME_HEARTRATE).toString());
			post.mComment = (String) entity
					.getProperty(ExerciseEntity.FIELD_NAME_COMMENT);
			post.mLocationList = (String) entity
					.getProperty(ExerciseEntity.FIELD_NAME_LOCATIONS);
			post.mPictures = (ArrayList<String>) entity
					.getProperty(ExerciseEntity.FIELD_NAME_PICTURES);
			resultList.add(post);
		}
		return resultList;
	}
	
	public static boolean containsID(long id) {
		ArrayList<ExerciseEntity> resultList = new ArrayList<ExerciseEntity>();

		// Fetch the data from the datastore
		Query query = new Query(ExerciseEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExerciseEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);

		// Look through it for the id
		boolean containsID = false;
		for (Entity entity : pq.asIterable()) {
			if(id == (long) entity.getProperty(ExerciseEntity.FIELD_NAME_ID)){
				containsID = true;
			}
		}
		return containsID;
	}
	
	public static void clear(){
		
		// Fetch the data from the datastore
		Query query = new Query(ExerciseEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExerciseEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);
		//Delete all of the data in the datastore
		for (Entity entity : pq.asIterable()) {
			mDatastore.delete(entity.getKey());
		}
		// Fetch the data from the datastore
		query = new Query(ExerciseEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExerciseEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		pq = mDatastore.prepare(query);
		if(pq.asIterable().iterator().hasNext()){
			System.out.println("Clear failed!");
		}
	}
	
	public static void delete( long id){
		// Fetch the data from the datastore
		Query query = new Query(ExerciseEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExerciseEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);
		//Delete all of the data in the datastore
		for (Entity entity : pq.asIterable()) {
			if(id == (long) entity.getProperty(ExerciseEntity.FIELD_NAME_ID)){
					mDatastore.delete(entity.getKey());
			}
		}	
	}
}
