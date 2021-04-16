package com.kishore.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Runner {

	public static void main(String[] args) {
		
		
		
		Map<Integer, Integer> countMap = new HashMap();
		countMap.put(1,47);
		countMap.put(2,72);
		countMap.put(3,43);
		countMap.put(4,42);
		countMap.put(5,29);
		countMap.put(6,47);
		countMap.put(7,30);
		countMap.put(8,28);
		countMap.put(9,34);
		countMap.put(10,42);
		countMap.put(11,55);
		countMap.put(12,20);
		countMap.put(13,34);
		countMap.put(14,27);
		countMap.put(15,20);
		countMap.put(16,24);
		countMap.put(17,28);
		countMap.put(18,78);
		
//		String urlStr = "https://www.gitasupersite.iitk.ac.in/srimad?etpurohit=1&choose=1&&language=dv&field_chapter_value=1&field_nsutra_value=1\r\n"
//				+ "https://www.gitasupersite.iitk.ac.in/srimad?etpurohit=1&choose=1&&language=dv&field_chapter_value=1&field_nsutra_value=2";
//
//		String[] lines = urlStr.split(System.getProperty("line.separator"));
//		
//		List<String> urls = Arrays.asList(lines);
		
		List<String> urls = genUrls(countMap);
		
		//urls.forEach(System.out::println);
		
//		for(String url: urls) {
//			String htmlStr = extractHtml(url);
//			Document doc = Jsoup.parse(htmlStr);
//			Element first = doc.select("div.views-field-field-etpurohit").first();
//			Element font = first.select("font").get(1);
//			String extractedText = font.text();
//			System.out.println(extractedText);
//		}

	}
	
	public static List<String> genUrls(Map<Integer, Integer> countMap) {
		List<String> urls = new ArrayList<String>();
		
		countMap.keySet().forEach(key -> {
			System.out.println("Chapter Chapter "+key);
			Integer max = countMap.get(key);
			for(int i=1; i<=max; i++) {
				
				String url = "https://www.gitasupersite.iitk.ac.in/srimad?language=dv&field_chapter_value="+key+"&field_nsutra_value="+i+"&etpurohit=1";
				
				urls.add(url);
				
				String htmlStr = extractHtml(url);
				Document doc = Jsoup.parse(htmlStr);
				Element first = doc.select("div.views-field-field-etpurohit").first();
				Element font = first.select("font").get(1);
				String extractedText = font.text();
				System.out.println(extractedText);

			}
		});
		
		return urls;
	}

	public static String extractHtml(String urlStr) {

		URL url;

		try {
			// get URL content
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer buffer = new StringBuffer();

			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				buffer.append(System.lineSeparator() + inputLine);
				//System.out.println(inputLine);
			}
			br.close();
			//System.out.println("Done");

			return buffer.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";

	}

}
