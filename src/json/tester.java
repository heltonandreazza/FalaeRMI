package json;

import groups.Group;
import groups.Groups;
import users.User;
import users.Users;

import com.fasterxml.jackson.databind.ObjectMapper;

public class tester {

	public static void main(String[] args) {
		ObjectMapper mapper = Mapper.getInstance();

		User user1 = new User(1, "helton", "123");
		User user2 = new User(2, "ana", "321");
		User user3 = new User(3, "airton", "123456");
		User user4 = new User(4, "vanusa", "senha");
		
		Group group = new Group(1, "group", "desc", "name");
		group.putUser(user2);
		group.putUser(user3);

		Group group2 = new Group(20, "group2", "desc2", "name2");
		group2.putUser(user2);
		group2.putUser(user4);
		group2.putUser(user1);

		Groups.postGroup(group);
		Groups.postGroup(group2);
		
		Users.postUser(user2);
		Users.postUser(user1);
		Users.postUser(user3);
		Users.postUser(user4);
		
		
		//TESTE USERS
		//Users.writeFile();
		Users.loadFile();
		System.out.println("get user: " + Users.getUser("helton"));
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
