package edu.dartmouth.cs.gcmdemo.server.data;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

public class PostDatastore {
	private static final DatastoreService mDatastore = DatastoreServiceFactory
			.getDatastoreService();

	private static Key getParentKey() {
		return KeyFactory.createKey(PostEntity.ENTITY_KIND_PARENT,
				PostEntity.ENTITY_PARENT_KEY);
	}

	private static void createParentEntity() {
		Entity entity = new Entity(getParentKey());

		mDatastore.put(entity);
	}

	public static boolean add(PostEntity post) {
		Key parentKey = getParentKey();
		try {
			mDatastore.get(parentKey);
		} catch (Exception ex) {
			createParentEntity();
		}

		Entity entity = new Entity(PostEntity.ENTITY_KIND_POST,
				post.mPostDate.getTime(), parentKey);

		entity.setProperty(PostEntity.FIELD_NAME_DATE, post.mPostDate);
		entity.setProperty(PostEntity.FIELD_NAME_POST, post.mPostString);

		mDatastore.put(entity);

		return true;
	}

	public static ArrayList<PostEntity> query() {
		ArrayList<PostEntity> resultList = new ArrayList<PostEntity>();

		Query query = new Query(PostEntity.ENTITY_KIND_POST);
		query.setFilter(null);
		query.setAncestor(getParentKey());
		query.addSort(PostEntity.FIELD_NAME_DATE, SortDirection.ASCENDING);
		PreparedQuery pq = mDatastore.prepare(query);

		for (Entity entity : pq.asIterable()) {
			PostEntity post = new PostEntity();
			post.mPostDate = (Date)entity.getProperty(PostEntity.FIELD_NAME_DATE);
			post.mPostString = (String)entity.getProperty(PostEntity.FIELD_NAME_POST);
			
			resultList.add(post);
		}
		return resultList;
	}

}
