package json;

import groups.Group;
import groups.Groups;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import users.User;
import users.Users;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class tester {

	public static void main(String[] args) {
		ObjectMapper mapper = Mapper.getInstance();

		User user2 = new User(1, "helton", "email");
		Group group = new Group(1, "group", "desc", "name");
		group.putUser(user2);
		User user3 = new User(2, "helton2", "email");
		group.putUser(user3);

		Group group2 = new Group(20, "group2", "desc2", "name2");
		group2.putUser(user2);
		User user4 = new User(12, "newuser", "email2");
		group2.putUser(user4);

		Groups.postGroup(group);
		Groups.postGroup(group2);
		// System.out.println(Groups.getGroup("group"));
		// System.out.println(Groups.toJson());

		Users.postUser(user2);
		Users.postUser(user3);
		Users.postUser(user4);
		
		//TESTE USERS
		Users.writeFile();
		Users.loadFile();
		System.out.println("users: " + Users.getUsers());
//		try {
//			System.out.println("map users: \n" + mapper.writeValueAsString(Users.readFile()));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//TESTE GROUPS
		Groups.writeFile();
		Groups.loadFile();
		System.out.println("groups: " + Groups.getGroups());
//		try {
//			System.out.println("map groups: \n" + mapper.writeValueAsString(Groups.readFile()));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			File file = new File("C:\\baseJson\\users.json");
//			mapper.writeValue(file, Users.getMapUsers());
//			
//			HashMap<String, User> maps = mapper.readValue(file, HashMap.class);
//			
//			System.out.println(mapper.writeValueAsString(maps));
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			File file = new File("C:\\baseJson\\groups.json");
//			mapper.writeValue(file, Groups.getMapGroups());
//
//			File from = new File("C:\\baseJson\\teste.json");
//			
//			Map<String, User> propertyMap = new HashMap<String, User>();
//			propertyMap.put("teste", new User(1, "name","email"));
//			propertyMap.put("teste2", new User(12, "name2","email2"));
//			
//			mapper.writeValue(from, propertyMap);
//			
//			HashMap<String, Group> maps = mapper.readValue(file, HashMap.class);
//			
//			System.out.println(mapper.writeValueAsString(maps));
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/*
		 * 
		 * try { User user = mapper.readValue(jsonString, User.class);
		 * System.out.println("java obj:" + user);
		 * mapper.enable(SerializationFeature.INDENT_OUTPUT); jsonString =
		 * mapper.writeValueAsString(user); System.out.println("json string:" +
		 * jsonString); } catch (JsonParseException e) { e.printStackTrace(); }
		 * catch (JsonMappingException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
	}
}
