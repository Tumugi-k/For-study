package com.example.demo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WeatherInfo {
	
	public static String main() {
		String Info = "";
		try {
			URL url = new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String str = reader.readLine();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(str);
			final String telop = root.get("forecasts").get(0).get("telop").asText();
			System.out.println(telop);
			Info = "今日の東京の天気は\"" + telop + "\"です。";
			if(telop.contains("雨")) {
				Info += "\n" + "傘は持ちましたか？";
			}
			return Info;
		} catch (Exception ex) {
			Logger.getLogger(WeatherInfo.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Info;
	}
	
	public static String TemperatureInfo() {
		String Info = "";
		try {
			URL url = new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String str = reader.readLine();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(str);
			int nowTemperature = Integer.parseInt(root.get("forecasts").get(0).get("temperature").get("max").get("celsius").asText());
			String comment = null;
			if(nowTemperature >= 28) {
				comment = "暑い";
			}else if(nowTemperature < 28 && nowTemperature >= 18) {
				comment = "暖かい";
			}else if(nowTemperature < 18 && nowTemperature >= 14) {
				comment = "涼しい";
			}else {
				comment = "寒い";
			}
			Info = "今日の最高気温は" + nowTemperature + "℃です。" + "\n" + comment + "ですね。";
			System.out.println("hoge");
			return Info;
		} catch (Exception ex) {
			Logger.getLogger(WeatherInfo.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
}
