package edu.dartmouth.cs.gcmdemo.server.data;

import java.util.Date;

public class PostEntity {
	public static String ENTITY_KIND_PARENT = "PostParent";
	public static String ENTITY_PARENT_KEY = ENTITY_KIND_PARENT;
	public static String ENTITY_KIND_POST = "Post";

	public static String FIELD_NAME_DATE = "Date";
	public static String FIELD_NAME_POST = "Post";

	public Date mPostDate;
	public String mPostString;

	public PostEntity() {
	}

	public PostEntity(String post, Date date) {
		mPostDate = date;
		mPostString = post;
	}
}
