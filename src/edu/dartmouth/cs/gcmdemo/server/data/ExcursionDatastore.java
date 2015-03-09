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

public class ExcursionDatastore {
	private static final DatastoreService mDatastore = DatastoreServiceFactory
			.getDatastoreService();

	private static Key getParentKey() {
		return KeyFactory.createKey(ExcursionEntity.ENTITY_KIND_PARENT,
				ExcursionEntity.ENTITY_PARENT_KEY);
	}

	private static void createParentEntity() {
		Entity entity = new Entity(getParentKey());

		mDatastore.put(entity);
	}

	public static boolean add(ExcursionEntity post) {
		Key parentKey = getParentKey();
		try {
			mDatastore.get(parentKey);
		} catch (Exception ex) {
			createParentEntity();
		}

		Entity entity = new Entity(ExcursionEntity.ENTITY_KIND_POST, post.id,
				parentKey);

		entity.setProperty(ExcursionEntity.FIELD_NAME_ID, post.id);
		entity.setProperty(ExcursionEntity.FIELD_NAME_INPUT_TYPE,
				post.mInputType);
		entity.setProperty(ExcursionEntity.FIELD_NAME_ACTIVITY_TYPE,
				post.mActivityType);
		entity.setProperty(ExcursionEntity.FIELD_NAME_DATE_TIME, post.mDateTime);
		entity.setProperty(ExcursionEntity.FIELD_NAME_DURATION, post.mDuration);
		entity.setProperty(ExcursionEntity.FIELD_NAME_DISTANCE, post.mDistance);
		entity.setProperty(ExcursionEntity.FIELD_NAME_AVG_SPEED, post.mAvgSpeed);
		entity.setProperty(ExcursionEntity.FIELD_NAME_CALORIE, post.mCalorie);
		entity.setProperty(ExcursionEntity.FIELD_NAME_CLIMB, post.mClimb);
		entity.setProperty(ExcursionEntity.FIELD_NAME_HEARTRATE, post.mHeartRate);
		entity.setProperty(ExcursionEntity.FIELD_NAME_COMMENT, post.mComment);
		entity.setProperty(ExcursionEntity.FIELD_NAME_LOCATIONS, post.mLocationList);
		entity.setProperty(ExcursionEntity.FIELD_NAME_PICTURES, post.mPictures);

		mDatastore.put(entity);

		return true;
	}

	public static ArrayList<ExcursionEntity> query() {
		ArrayList<ExcursionEntity> resultList = new ArrayList<ExcursionEntity>();

		// Fetch the data from the datastore
		Query query = new Query(ExcursionEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExcursionEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);

		// Put the fetched data in the resultList
		for (Entity entity : pq.asIterable()) {
			ExcursionEntity post = new ExcursionEntity();

			post.id = (long) entity.getProperty(ExcursionEntity.FIELD_NAME_ID);
			post.mInputType = Integer.parseInt(entity.getProperty(
					ExcursionEntity.FIELD_NAME_INPUT_TYPE).toString());
			post.mActivityType = Integer.parseInt(entity.getProperty(
					ExcursionEntity.FIELD_NAME_ACTIVITY_TYPE).toString());
			post.mDateTime = (String) entity
					.getProperty(ExcursionEntity.FIELD_NAME_DATE_TIME);
			post.mDuration = Integer.parseInt(entity.getProperty(
					ExcursionEntity.FIELD_NAME_DURATION).toString());
			post.mDistance = (double) entity
					.getProperty(ExcursionEntity.FIELD_NAME_DISTANCE);
			post.mAvgSpeed = (double) entity
					.getProperty(ExcursionEntity.FIELD_NAME_AVG_SPEED);
			post.mCalorie = Integer.parseInt(entity.getProperty(
					ExcursionEntity.FIELD_NAME_CALORIE).toString());
			post.mClimb = (double) entity
					.getProperty(ExcursionEntity.FIELD_NAME_CLIMB);
			post.mHeartRate = Integer.parseInt(entity.getProperty(
					ExcursionEntity.FIELD_NAME_HEARTRATE).toString());
			post.mComment = (String) entity
					.getProperty(ExcursionEntity.FIELD_NAME_COMMENT);
			post.mLocationList = (String) entity
					.getProperty(ExcursionEntity.FIELD_NAME_LOCATIONS);
			post.mPictures = (ArrayList<String>) entity
					.getProperty(ExcursionEntity.FIELD_NAME_PICTURES);
			resultList.add(post);
		}
		return resultList;
	}
	
	public static boolean containsID(long id) {
		ArrayList<ExcursionEntity> resultList = new ArrayList<ExcursionEntity>();

		// Fetch the data from the datastore
		Query query = new Query(ExcursionEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExcursionEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);

		// Look through it for the id
		boolean containsID = false;
		for (Entity entity : pq.asIterable()) {
			if(id == (long) entity.getProperty(ExcursionEntity.FIELD_NAME_ID)){
				containsID = true;
			}
		}
		return containsID;
	}
	
	public static void clear(){
		
		// Fetch the data from the datastore
		Query query = new Query(ExcursionEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExcursionEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);
		//Delete all of the data in the datastore
		for (Entity entity : pq.asIterable()) {
			mDatastore.delete(entity.getKey());
		}
		// Fetch the data from the datastore
		query = new Query(ExcursionEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExcursionEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		pq = mDatastore.prepare(query);
		if(pq.asIterable().iterator().hasNext()){
			System.out.println("Clear failed!");
		}
	}
	
	public static void delete( long id){
		// Fetch the data from the datastore
		Query query = new Query(ExcursionEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(ExcursionEntity.FIELD_NAME_ID, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);
		//Delete all of the data in the datastore
		for (Entity entity : pq.asIterable()) {
			if(id == (long) entity.getProperty(ExcursionEntity.FIELD_NAME_ID)){
					mDatastore.delete(entity.getKey());
			}
		}	
	}
}
