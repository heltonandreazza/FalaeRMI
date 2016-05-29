package groups;

import java.util.HashMap;

import json.Mapper;

import org.json.JSONArray;
import org.json.JSONObject;

import users.User;
import users.Users;

public class teste {

	public static void main(String[] args) {
		Users.loadFile();

		String users = "[{\"password\":\"123\",\"name\":\"hell\",\"rating\":10,\"id\":99},{\"password\":\"123456\",\"name\":\"helll\",\"rating\":100,\"id\":999}]";

		JSONArray jsonArray = new JSONArray(users);
		System.out.println(jsonArray.length());
		HashMap<String, User> hashMap = new HashMap<String, User>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			User user = new User(jsonObject.getInt("id"),
					jsonObject.getString("name"),
					jsonObject.getString("password"));
			user.setRating(jsonObject.getInt("rating"));

			hashMap.put(user.getName(), user);
		}

		for (String key : hashMap.keySet()) {
			System.out.println("key: " + key + " value: " + hashMap.get(key));
		}
	}
}
