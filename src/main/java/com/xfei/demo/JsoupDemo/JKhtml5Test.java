package com.xfei.demo.JsoupDemo;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JKhtml5Test {
	
	public static void login() throws IOException {
		String loginUrl = "http://passport.jikexueyuan.com/sso/login";
		Response response = Jsoup.connect(loginUrl).ignoreContentType(true).execute();
		Map<String, String> cookies = response.cookies();
		
		
	}

	public static void main(String[] args) {
		
		
		String mainurl= "http://www.jikexueyuan.com/course/html5";
		try {
			Document html5doc = Jsoup.connect(mainurl).ignoreContentType(true).get();
			Elements verdiolink = html5doc.select(".lessonimg-box a");
			 for(int i=0;i<verdiolink.size();i++){
//				 System.out.println(verdiolink.get(i).attr("href"));
				String courseurl=verdiolink.get(i).attr("href");
				Document coursedoc= Jsoup.connect(courseurl).ignoreContentType(true).get();
				Elements coursedownload=coursedoc.select(".btn-box a");
				for(int j=0;j<coursedownload.size();j++){
					
             	System.out.println(coursedownload.get(j).attr("href"));
				//String loadurl=coursedownload.get(j).attr("href");
				//Document download = Jsoup.connect(loadurl).ignoreContentType(true).get();
				String courseId = coursedownload.get(j).select("#course_download").attr("course_id");
				System.out.println(courseId);
				String jsonUrl = "http://www.jikexueyuan.com/course/downloadRes?course_id=" + courseId;
				Document jsonData = Jsoup.connect(jsonUrl).ignoreContentType(true).get();
				System.out.println(jsonData);
				}
			 }
//			String docHmtl = html5doc.html();
//			System.out.println(docHmtl);
//			Pattern pattern = Pattern.compile(".+require\\(\"common:widget/pager/PagerDemo\\.js\"\\).init\\((\\d+),(\\d+),(\\d+)\\).+");
//			Matcher matcher = pattern.matcher(docHmtl);
//			if (matcher.find()) {
//				System.out.println(matcher.group(1));
//
//				System.out.println(matcher.group(2));
//
//				System.out.println(matcher.group(3));
//			}
//			System.out.println();
			
			
			//docHmtl.matches(regx);
			
			//Elements numpage=html5doc.select("#page-nav");
//			 System.out.println(html5doc.text());
			
		//	for (int i=0;i<numpage.size();i++)
		//	   System.out.println(numpage.get(i).html());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
