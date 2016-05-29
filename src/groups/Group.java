package groups;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import json.Mapper;
import users.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Group {

	private int id;
	private String name;
	private String description;
	private int rating;
	private String owner;
	private HashMap<String, User> users;

	public Group(int id, String name, String description, String ownerName) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rating = 0;
		this.owner = ownerName;
		this.users = new HashMap<String, User>();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(JSONArray users) {
		if (users != null) {
			for (int i = 0; i < users.length(); i++) {
				JSONObject jsonUser = users.getJSONObject(i);

				if (jsonUser != null) {
					User user = new User(jsonUser.getInt("id"),
							jsonUser.getString("name"),
							jsonUser.getString("password"));
					user.setRating(jsonUser.getInt("rating"));

					this.users.put(user.getName(), user);
				}
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void putUser(User user) {
		this.users.put(user.getName(), user);
	}

	public User getUser(String userName) {
		return this.users.get(userName);
	}

	public void removeUser(String userName) {
		this.users.remove(userName);
	}

	public String toJson() {
		ObjectMapper mapper = Mapper.getInstance();

		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String toString() {
		return toJson();
	}
}
