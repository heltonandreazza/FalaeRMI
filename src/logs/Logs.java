package logs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import json.Mapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Logs {

	private static HashMap<String, Log> logs = new HashMap<String, Log>();
	private static File file = new File("C:\\baseJson\\logs.json");

	public static void postLog(Log log) {
		logs.put(log.getDateTime(), log);
		writeFile();
	}

	public static void deleteLog(String logDateTime) {
		logs.remove(logDateTime);
		writeFile();
	}

	public static Log getLog(String logDateTime) {
		return logs.get(logDateTime);
	}

	public static String getlogs() {
		return toJson();
	}

	public static HashMap<String, Log> getMaplogs() {
		return logs;
	}

	public static void writeFile() {
		try {
			Mapper.getInstance().writeValue(file, logs);
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
		HashMap<String, Log> readFile = readFile();

		if (readFile != null) {
			logs = readFile;
		}
	}

	public static HashMap<String, Log> readFile() {
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
			return mapper.writeValueAsString(logs);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
