package users;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import json.Mapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Users {

	private static HashMap<String, User> users = new HashMap<String, User>();	
	private static File file = new File("C:\\baseJson\\users.json");

	public static void postUser(User user) {
		users.put(user.getName(), user);
		writeFile();
	}

	public static void deleteUser(String userName) {
		users.remove(userName);
		writeFile();
	}

	public static String getUser(String userName) {
		String users = toJson();

		JSONObject arr = new JSONObject(users);

		if (arr != null) {
			JSONObject user = arr.getJSONObject(userName);

			if (user != null) {
				return user.toString();
			}
		}
		return null;
	}

	public static String getUsers() {
		return toJson();
	}

	public static HashMap<String, User> getMapUsers() {
		return users;
	}

	public static void writeFile() {
		try {
			Mapper.getInstance().writeValue(file, users);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadFile() {
		HashMap<String, User> readFile = readFile();

		if (readFile != null) {
			users = readFile;
		}
	}

	public static HashMap<String, User> readFile() {
		try {
			return Mapper.getInstance().readValue(file, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String toJson() {
		ObjectMapper mapper = Mapper.getInstance();

		try {
			return mapper.writeValueAsString(users);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
