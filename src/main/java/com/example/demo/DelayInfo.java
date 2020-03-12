package com.example.demo;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DelayInfo {
	
	public static void main(String[] args) {
	}
	
	public static String DlayInfo(String train) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(new File("DlayInfo.json"));
			String Info = null;
			int RC = 0;
			for(JsonNode n : root.get("info")) {
				String name = n.get("name").asText();
				String url = n.get("url").asText();
				if(name.equals(train)) {
					Info = name + "ならわかるー" + "\n" + "リンク送るね!" + "\n" + url;
					RC = 1;
				}
			}
			if(RC == 0) {
				Info = "ごめん、それはわかんない。。。";
			}
			return Info;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

}
