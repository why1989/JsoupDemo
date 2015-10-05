package com.xfei.demo.JsoupDemo.courseMooc;

import java.util.List;

public class Course {
   String coursename;
   String courseurl;
   public String getCoursename() {
	return coursename;
}
public void setCoursename(String coursename) {
	this.coursename = coursename;
}
public String getCourseurl() {
	return courseurl;
}
public void setCourseurl(String courseurl) {
	this.courseurl = courseurl;
}
public List<CourseNode> getNodelist() {
	return nodelist;
}
public void setNodelist(List<CourseNode> nodelist) {
	this.nodelist = nodelist;
}
List<CourseNode> nodelist ;

   
}
