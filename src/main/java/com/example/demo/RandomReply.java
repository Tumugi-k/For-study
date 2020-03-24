package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.Random;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomReply {
	public static String main(String type) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File("reply.json"));
		Random random = new Random();
		int n = random.nextInt(5);
		String rep = root.get(type).get(n).get("rep").asText();
		return rep;
	}
}
