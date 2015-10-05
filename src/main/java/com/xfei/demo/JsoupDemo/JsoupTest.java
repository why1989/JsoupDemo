package com.xfei.demo.JsoupDemo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.print.Doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupTest {

	public static void main(String[] args) {
		Map  linkMap = new HashMap();
		String url = "http://www.jikexueyuan.com";
        try {
			Document doc = Jsoup.connect(url).ignoreContentType(true).get();
			 Elements link = doc.select("a");
			 for(int i = 0;i<link.size();i++){
				
				 String key=link.get(i).attr("href");
				 String value=link.get(i).text();
				         linkMap.put(key, value);
			 }
			 for
		} catch (IOException e) {
			// TODO Auto-generated catch  block
			e.printStackTrace();
		}
      
	}

}
