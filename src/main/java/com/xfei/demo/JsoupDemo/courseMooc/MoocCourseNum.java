package com.xfei.demo.JsoupDemo.courseMooc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xfei.demo.JsoupDemo.MoocTest;

public class MoocCourseNum {

	public static void main(String[] s) throws IOException {
		Map<String, String> logincookie = MoocTest.loginMooc();
		List<LanClassfy> classfylist = new ArrayList<LanClassfy>();
		String classfypath;
		String classfyname;
		Document lanclassfydoc = Jsoup.connect("http://www.imooc.com/course/list").ignoreContentType(true)
				.cookies(logincookie).get();
		Elements lanlink = lanclassfydoc.select(".course-nav-item a[data-id]");
//		System.out.println(lanlink.size());
		for (int i = 0; i < lanlink.size(); i++) {
			classfypath = lanlink.get(i).attr("href");
			classfyname = lanlink.get(i).text();
//			System.out.print(classfypath + " ");
//			System.out.println(classfyname);
			LanClassfy classfyObject = new LanClassfy();
			classfyObject.setClassfyname(classfyname);
			classfyObject.setClassfyurl("http://www.imooc.com" + classfypath);
			classfylist.add(classfyObject);
		
		}
		Iterator<LanClassfy> it = classfylist.iterator();
		while (it.hasNext()) {
			LanClassfy CourseObject = it.next();
			String courseurl = CourseObject.getClassfyurl();
			Document coursedoc = Jsoup.connect(courseurl).ignoreContentType(true).cookies(logincookie).get();
			Elements courselist = coursedoc.select(".course-one a");
			List<Course> courseArraylist = new ArrayList<Course>();
//			System.out.print(courseurl);
//			System.out.println(CourseObject.getClassfyname());

			for (int j = 0; j < courselist.size(); j++) {
				// courselist.get(j).select("span").text();
				courseArraylist.add(MoocCourseNum.addcourse(courselist.get(j)));
				
				Elements lastpage = courselist.select(".page a");

				for (int i = 0; i < lastpage.size(); i++) {
					if (lastpage.get(i).text().equals("尾页")) {
						String pagepath = lastpage.get(i).attr("href");
						int pagenum = Integer.parseInt(pagepath.substring(pagepath.lastIndexOf("=") + 1));
						for (int j1 = 2; j1 <= pagenum; j1++) {
							String coursepageurl = courseurl + "&page=" + j1;
							Document docnum = Jsoup.connect(coursepageurl).ignoreContentType(true).cookies(logincookie)
									.get();
							Elements coursepagelist = docnum.select(".course-one a");
							for (int j2 = 0; j2 < coursepagelist.size(); j2++) {
								courseArraylist.add(MoocCourseNum.addcourse(courselist.get(j2)));
													
							}
						}
					}
				}
			}
			 CourseObject.setCourses(courseArraylist);  
			System.out.println(CourseObject.getClassfyname());
			System.out.println(CourseObject.getClassfyurl());
			System.out.println(">>>");
			for(int i1=0;i1<CourseObject.getCourses().size();i1++){
				System.out.println(CourseObject.getCourses().get(i1).getCoursename());
				
				System.out.println(CourseObject.getCourses().get(i1).getCourseurl());  } 
			
		}
		        
	}
	
	//根据页面元素获取course对象
         public static  Course addcourse(Element element){
        	 
		  Course course = new Course();
		  String coursepath =element.attr("href");
		  String coursename = element.select("h5 span").text();
			course.setCoursename(coursename);
		    course.setCourseurl("http://www.imooc.com" +coursepath );
		    return  course;
         }
}
