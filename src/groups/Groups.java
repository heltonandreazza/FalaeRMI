package groups;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import json.Mapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Groups {

	private static HashMap<String, Group> groups = new HashMap<String, Group>();
	private static File file = new File("C:\\baseJson\\groups.json");

	public static void postGroup(Group group) {
		groups.put(group.getName(), group);
	}

	public static void deleteGroup(String groupName) {
		groups.remove(groupName);
	}

	public static Group getGroup(String groupName) {
		return groups.get(groupName);
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
