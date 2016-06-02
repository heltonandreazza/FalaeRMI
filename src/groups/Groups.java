package groups;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import json.Mapper;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Groups {

	private static HashMap<String, Group> groups = new HashMap<String, Group>();
	private static File file = new File("C:\\baseJson\\groups.json");
	private static Semaphore semaforoPost = new Semaphore(1, true);

	public static void postGroup(Group group) {
		try {
			semaforoPost.acquire();
			if (groups.get(group.getName()) != null) {
				throw new IllegalArgumentException("Já existe um grupo com este nome.");
			}
			groups.put(group.getName(), group);
			writeFile();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforoPost.release();
		}

	}

	public static void deleteGroup(String groupName) {
		groups.remove(groupName);
		writeFile();
	}

	public static String getGroup(String groupName) {
		String groups = toJson();
		
		JSONObject group = new JSONObject(groups).getJSONObject(groupName);
		
		if(group != null) {			
			return group.toString();
		}
		return null;
	}
	
	public static void setUsers(String groupName, JSONArray users) {
		Group group = groups.get(groupName);
		
		if( group != null ) {
			group.setUsers(users);
		}
	}

	public static String getGroups() {
		return toJson();
	}

	public static Map<String, Group> getMapGroups() {
		return groups;
	}

	public static void writeFile() {
		try {
			Mapper.getInstance().writeValue(file, groups);
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
		HashMap<String, Group> readFile = readFile();

		if (readFile != null) {
			groups = readFile;
		}
	}

	public static HashMap<String, Group> readFile() {
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
			return mapper.writeValueAsString(groups);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
