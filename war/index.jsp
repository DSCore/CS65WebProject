<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>


<html>
    <head>
        <title>Upload Test</title>
    </head>
    <body>
        <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="foo">
            <input type="file" name="myFile">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>

// web.xml

<?xml version="1.0" encoding="utf-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns="http://java.sun.com/xml/ns/javaee"
   xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
   xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
   http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" version="2.5">

  <servlet>
    <servlet-name>Upload</servlet-name>
    <servlet-class>Upload</servlet-class>
  </servlet>
  
  <servlet>
    <servlet-name>Serve</servlet-name>
    <servlet-class>Serve</servlet-class>
  </servlet>
 
  <servlet-mapping>
    <servlet-name>Upload</servlet-name>
    <url-pattern>/upload</url-pattern>
  </servlet-mapping>

  <servlet-mapping>
    <servlet-name>Serve</servlet-name>
    <url-pattern>/serve</url-pattern>
  </servlet-mapping>
  
</web-app>