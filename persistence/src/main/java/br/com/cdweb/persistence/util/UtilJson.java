package br.com.cdweb.persistence.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class UtilJson {
	private static Gson gson = new Gson();
	private static ObjectMapper mapper = new ObjectMapper();
	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
//		return gson.toJson(object);
		
	}
	public static String toJson(Object object, Class class1) {
//		return gson.toJson(object,class1);
		return toJson(object);
	}
}
