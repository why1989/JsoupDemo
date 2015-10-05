package com.xfei.demo.JsoupDemo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSON;

public class MoocTest {

	public static Response loginMooc(String username, String password){
		Map<String, String> cookie = new HashMap<String, String>();
		Map<String,String>  param = new HashMap<String, String>();
//		Map<String,String> logincookie = new HashMap<String,String>();
		try {
		Response	res = Jsoup.connect("http://www.imooc.com/").ignoreContentType(true).execute();
			cookie = res.cookies();
			param.put("username",username);
			param.put("password", password);
			param.put("remember", "1");
			Response loginres =Jsoup.connect("http://www.imooc.com/user/login").ignoreContentType(true).cookies(cookie).data(param)
			 .method(Method.POST).execute();
			  //System.out.println(loginres.body());
			
			return loginres;
//			 logincookie.putAll(cookie);
//			 logincookie.putAll(loginres.cookies());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
		
		     return null;
          
		 } 
	
	   public static List<LoginParam> readfile(String path) throws IOException{
		   String[] param;
		   File file = new File(path);
		   InputStream in = null;
		    if(file.exists()){
		    	 in = new  FileInputStream(file);
		    	Reader reader= new InputStreamReader(in);

		    	List<LoginParam> paramlist = new ArrayList<LoginParam>();
				BufferedReader br = new BufferedReader(reader);
				try {
					
		    	String tempString = null; 
		    	while((tempString=br.readLine()) !=null){
		    		 param= tempString.split(",");
		       LoginParam loginparam= new LoginParam();
		       loginparam.setUsername(param[0]);
		       loginparam.setPassword(param[1]);
		       loginparam.setStatus(param[2]);
		       loginparam.setMsg(param[3]);
		       paramlist.add(loginparam);		         
		    	}

				} finally {
					br.close();
					reader.close();
					in.close();
				}
	            return paramlist;
		    }
		       return Collections.emptyList();
	   }
	 public  static String logintest() throws IOException{
			  loginMooc();
		Response	resinfo =Jsoup.connect("http://www.imooc.com/user/userinfo").ignoreContentType(true).cookies(loginMooc())
			.execute();
//		          System.out.println(resinfo.body());
		      if(resinfo.body().contains("个人资料")){
		    	  return "登陆成功";
		      }
		      else{
		    	  return "登陆失败";
		      }
		    
			
			
			
		}
	
 
public static void main(String[] s) throws IOException{
	List<LoginParam> list = MoocTest.readfile("/Users/WHY/login");
	for(int i0=0;i0<list.size();i0++){
		String usernamevalue= list.get(i0).getUsername();
		String passwordvalue = list.get(i0).getPassword();
		
	Response   Resp=MoocTest.loginMooc(usernamevalue,passwordvalue);
	           String data = Resp.body();
	           System.out.println(usernamevalue);
	           System.out.println(passwordvalue);
	           System.out.println(data);
	           
	           Map<String, Object> jsonmap = JSON.parseObject(data, Map.class);
	           System.out.println(jsonmap.get("msg"));
	           System.out.println(jsonmap.get("status"));
	          System.out.println( list.get(i0).getStatus());
	          System.out.println( list.get(i0).getMsg());
	           System.out.println(list.get(i0).getMsg().equals(jsonmap.get("msg")));
	           System.out.println(list.get(i0).getStatus().equals(jsonmap.get("status").toString()));
	           if(list.get(i0).getMsg().equals(jsonmap.get("msg"))&&
	        		   list.get(i0).getStatus().equals(jsonmap.get("status").toString())){
	        	  
	           System.out.println("通过");
	   
   }else {
	   System.out.println("失败");
	     }
	}
}
}



