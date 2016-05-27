package rmi;

import groups.Group;
import groups.Groups;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import users.Users;

public class Client {

	private Client() {
	}

	public static void main(String[] args) {

		try {
			String serverName = "localhost";

			RMIServerAPI serverStub = getServerStub("ServerAPI", serverName);

			// testAllServices(serverStub);
			// serverStub.writeGroupsFile();
			// serverStub.writeUsersFile();
			loadFiles(serverStub);
			System.out.println("groups: \n" + serverStub.getGroups());
			System.out.println("users: \n" + serverStub.getUsers());

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	private static void loadFiles(RMIServerAPI serverStub) {
		// TODO Auto-generated method stub
		try {
			serverStub.loadGroupsFile();
			serverStub.loadUsersFile();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testAllServices(RMIServerAPI serverStub)
			throws RemoteException {
		// CRETING USERS
		System.out.println("post user: "
				+ serverStub.postUser(1, "helton", "helton@com"));
		System.out.println("post user: "
				+ serverStub.postUser(2, "janio", "janio@com"));
		System.out.println("post user: "
				+ serverStub.postUser(3, "ana", "ana@com"));

		System.out.println();

		// CREATING GROUPS
		System.out.println("post group: "
				+ serverStub.postGroup(1, "nome", "desc", "helton"));
		System.out.println("post group: "
				+ serverStub.postGroup(2, "nome2", "desc2", "janio"));
		System.out.println("post group: "
				+ serverStub.postGroup(3, "nome3", "desc3", "ana"));

		// delete GROUP 2
		System.out.println("\n delete group: "
				+ serverStub.deleteGroup("nome2"));

		// GET GROUPS AGAIN
		System.out.println("\n LISTAR GRUPOS: \n" + serverStub.getGroups());

		System.out.println();
		// DELETE USERS
		System.out.println("delete user: " + serverStub.deleteUser("janio"));

		System.out.println();
		// GET USERS
		System.out.println("get users: " + serverStub.getUsers());
	}

	private static RMIServerAPI getServerStub(String api, String server)
			throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(server);
		return (RMIServerAPI) registry.lookup(api);
	}

}