package rmi;

import groups.Group;
import groups.Groups;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import users.User;
import users.Users;

public class RMIServer implements RMIServerAPI {

	public RMIServer() {
	}

	//INIT SERVER
	public static void main(String args[]) {

		try {
			RMIServer server = new RMIServer();
			
			RMIServerAPI serverStub = (RMIServerAPI) UnicastRemoteObject.exportObject(server, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("ServerAPI", serverStub);

			System.err.println("Server RMI ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public String postGroup(int id, String name, String desc, String userName)
			throws RemoteException {
		Group group = new Group(id, name, desc, userName);

		Groups.postGroup(group);

		return "Grupo criado com sucesso -> " + group;
	}

	@Override
	public String getGroups() throws RemoteException {
		return Groups.getGroups();
	}

	@Override
	public String deleteGroup(String groupName) {
		Groups.deleteGroup(groupName);
		
		return "Grupo removido com sucesso -> " + groupName;
	}

	@Override
	public String getUsers() throws RemoteException {
		// TODO Auto-generated method stub
		return Users.getUsers();
	}

	@Override
	public String deleteUser(String userName) throws RemoteException {
		// TODO Auto-generated method stub
		Users.deleteUser(userName);
		return "User removido com sucesso -> " + userName;
	}

	@Override
	public String postUser(int id, String name, String email)
			throws RemoteException {
		// TODO Auto-generated method stub
		User user = new User(id, name, email);
		
		Users.postUser(user);
		return "User criado com sucesso -> " + user;
	}

	@Override
	public void writeGroupsFile() throws RemoteException {
		// TODO Auto-generated method stub
		Groups.writeFile();
	}

	@Override
	public void writeUsersFile() throws RemoteException {
		// TODO Auto-generated method stub
		Users.writeFile();
	}

	@Override
	public void loadGroupsFile() throws RemoteException {
		// TODO Auto-generated method stub
		Groups.loadFile();
	}

	@Override
	public void loadUsersFile() throws RemoteException {
		// TODO Auto-generated method stub
		Users.loadFile();
	}

}