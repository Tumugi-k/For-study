package com.example.demo;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DelayInfo {
	
	public static String main(String msg) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File("DlayInfo.json"));
		String Info = null;
		for(JsonNode n : root.get("info")) {
			String name = n.get("name").asText();
			String url = n.get("url").asText();
			if(name.equals(msg)) {
				Info = name + "ならわかりますよ、" + "\n" + "リンクを送りますね。" + "\n" + url;
			}
		}
		return Info;
	}

}
