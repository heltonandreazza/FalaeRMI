package clients;

import java.rmi.RemoteException;
import java.util.Date;

import servers.RMIServerAPI;
import servers.ServerUtils;
import Corba.CORBA_Falae;

public class Client {

	private Client() {
	}

	public static void main(String[] args) {

		try {
			RMIServerAPI serverStubRMI = ServerUtils.getServerStub();
			CORBA_Falae serverCorba = ServerUtils.getServerCorba();
			
			testAllServices(serverStubRMI, serverCorba);
			
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

	private static void testAllServices(RMIServerAPI serverStub, CORBA_Falae serverCorba)
			throws RemoteException {
		
		System.out.println();
		String strToken = serverCorba.generateToken("helton", "helton@com");
		//System.out.println("generate token: " + strToken);
		
		String token = strToken;
		System.out.println("verify a token: " + serverCorba.verifyToken(token));
		System.out.println();
		
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
				+ serverStub.postGroup(1, "nome", "desc", "helton", "", token));
		System.out.println("post group: "
				+ serverStub.postGroup(2, "nome2", "desc2", "janio", "", token));
		System.out.println("post group: "
				+ serverStub.postGroup(3, "nome3", "desc3", "ana", "", token));

		//SET USER IN A GROUP
		System.out.println();
		String users = "[{\"password\":\"123\",\"name\":\"hell\",\"rating\":10,\"id\":99},{\"password\":\"123456\",\"name\":\"helll\",\"rating\":100,\"id\":999}]";
		System.out.println("set user in a group: " 
				+ serverStub.setUsersGroup("nome", users, token));
		
		// delete GROUP 2
		System.out.println("\n delete group: "
				+ serverStub.deleteGroup("nome2", token));

		// GET GROUPS AGAIN
		System.out.println("\n LISTAR GRUPOS: \n" + serverStub.getGroups(token));

		System.out.println();
		// DELETE USERS
		System.out.println("delete user: " + serverStub.deleteUser("janio", token));
		
		System.out.println();
		// GET USERS
		System.out.println("get users: " + serverStub.getUsers());
		
		//GET ONE USER
		System.out.println("ger user: " + serverStub.getUser("helton", token));
		
		// CREATING LOGS
		System.out.println("post log: "
				+ serverStub.postLog("usernome1", "asdsad", new Date(new Date().getTime()).toString()));
		System.out.println("post log: "
				+ serverStub.postLog("usernome2", "6843654", new Date(new Date().getTime()).toString()));

		// GET LOGS
		System.out.println("\n LISTAR LOGS: \n" + serverStub.getLogs());

	}
}