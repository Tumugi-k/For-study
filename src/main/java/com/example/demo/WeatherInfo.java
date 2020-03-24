package com.example.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;




public class WeatherInfo {
	
	public static void main() {
				
	}
		
		public static String Info(int n) throws IOException{	
		String Info = null;

		URL url = new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		String str = reader.readLine();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(str);
		final String telop = root.get("forecasts").get(n).get("telop").asText();
		final String dateLabel = root.get("forecasts").get(n).get("dateLabel").asText();
		Info =  dateLabel + "の東京の天気は\"" + telop + "\"です。";
		if(n == 0 && telop.contains("雨")) {
			Info += "\n" + "傘は持ちましたか？";
		}
		return Info;
		} 
	
	public static String TemperatureInfo() throws IOException{
		String Info = null;
		URL url = new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));			
		String str = reader.readLine();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(str);
		String nowTemperature = root.get("forecasts").get(0).get("temperature").get("max").get("celsius").asText();
		String comment = "";
		int temp = Integer.parseInt(nowTemperature);
		if(temp >= 28) {
			comment = "暑い";
		}else if(temp < 28 && temp >= 18) {
			comment = "暖かい";
		}else if(temp < 18 && temp >= 14) {
			comment = "涼しい";
		}else {				
			comment = "寒い";
		}
		Info = "今日の最高気温は" + nowTemperature + "℃です。" + "\n" + comment + "ですね。";
		return Info;
	}
	
}
