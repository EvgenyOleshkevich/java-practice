package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	private static Map<String, Integer> data = new HashMap<String, Integer>();

	public static void clearData() {
		data.clear();
	}

	public static JSONObject request(String line) {
		String[] str = line.split(" ");
		if (str.length != 2) {
			return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: require 2 params\" }");
		}
		try {
			return request(str[0], new JSONObject(str[1]));
		}
		catch (JSONException e) {
			return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: second param is not json\" }");
		}
	}

	private static JSONObject request(String type, JSONObject req){
		try {
			switch (type) {
				case "add":
					int value;
					String str_value = req.get("value").toString();
					try {
						return add(req.get("name").toString(), Integer.parseInt(str_value));
					} catch (NumberFormatException e) {
						return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: value is nan\" }");
					}
				case "remove":
					return remove(req.get("name").toString());
				case "get":
					return get(req.get("name").toString());
				case "sum":
					return sum(req.get("first").toString(), req.get("second").toString());
				default:
					return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: type request\" }");
			}
		}
		catch (JSONException e) {
			return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: json content\" }");
		}
	}

	private static JSONObject add(String name, int value) {
		if (data.containsKey(name))
			return new JSONObject("{ \"code\" : 1, \"description\": \"name exists\" }");

		data.put(name, value);
		return new JSONObject("{ \"code\":0,\"description\":\"OK\"}");
	}

	private static JSONObject get(String name) {
		if (!data.containsKey(name))
			return new JSONObject("{ \"code\" : 1, \"description\": \"name not exist\" }");

		int value = data.get(name);
		return new JSONObject(String.format("{ \"value\" : %s,\"code\" : 0, \"description\": \"OK\" }", value));
	}

	private static JSONObject remove(String name) {
		data.remove(name);
		return new JSONObject("{ \"code\" : 0, \"description\": \"OK\" }");
	}

	private static JSONObject sum(String name1, String name2) {
		if (! data.containsKey(name1) || ! data.containsKey(name2))
			return new JSONObject("{\"code\":1,\"description\":\"name not exist\" }");

		int sum = data.get(name1) + data.get(name2);
		return new JSONObject(String.format("{ \"sum\" : %s,\"code\" : 0, \"description\": \"OK\" }", sum));
	}

}
