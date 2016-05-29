package servers;

import groups.Group;
import groups.Groups;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import logs.Log;
import logs.Logs;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import users.User;
import users.Users;
import Corba.CORBA_Falae;

public class RMIServer implements RMIServerAPI {

	private static final String TOKEN_INVALIDO = "Token inválido";

	public RMIServer() {
	}

	// INIT SERVER
	public static void main(String args[]) {

		try {
			RMIServer server = new RMIServer();

			RMIServerAPI serverStub = (RMIServerAPI) UnicastRemoteObject
					.exportObject(server, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			System.out.println(registry);
			registry.bind("RMIServerAPI", serverStub);

			// load files
			Groups.loadFile();
			Users.loadFile();

			System.err.println("Server RMI ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public String postGroup(int id, String name, String desc, String userName, String jsonUsers,
			String token) throws RemoteException {

		if (verifyToken(token)) {
			Group group = new Group(id, name, desc, userName);
			
			if(StringUtils.isNotEmpty(jsonUsers)) {
				JSONArray jsonArray = new JSONArray(jsonUsers);
				
				if(jsonArray != null) {
					group.setUsers(jsonArray);
				}
			}
			
			Groups.postGroup(group);
			return "Grupo criado com sucesso -> " + group;
		} else {
			return TOKEN_INVALIDO;
		}
	}

	@Override
	public String setUsersGroup(String groupName, String jsonUsers, String token) {
		if (verifyToken(token)) {
			
			if(StringUtils.isNotEmpty(jsonUsers)) {
				JSONArray jsonArray = new JSONArray(jsonUsers);
				
				if(jsonArray != null) {
					Groups.setUsers(groupName, jsonArray);
				}
			}
			
			return "Users adicionado com sucesso -> " + jsonUsers;
		} else {
			return TOKEN_INVALIDO;
		}
	}

	@Override
	public String getGroups(String token) throws RemoteException {
		if (verifyToken(token)) {
			return Groups.getGroups();
		} else {
			return TOKEN_INVALIDO;
		}
	}

	@Override
	public String deleteGroup(String groupName, String token) {
		if (verifyToken(token)) {
			Groups.deleteGroup(groupName);
			return "Grupo removido com sucesso -> " + groupName;
		} else {
			return TOKEN_INVALIDO;
		}
	}

	@Override
	public String getUsers() throws RemoteException {
		// TODO Auto-generated method stub
		return Users.getUsers();
	}

	@Override
	public String deleteUser(String userName, String token)
			throws RemoteException {
		// TODO Auto-generated method stub
		if (verifyToken(token)) {
			Users.deleteUser(userName);
			return "User removido com sucesso -> " + userName;
		} else {
			return TOKEN_INVALIDO;
		}
	}

	@Override
	public String postUser(int id, String name, String email)
			throws RemoteException {

		User user = new User(id, name, email);
		Users.postUser(user);
		return "User criado com sucesso -> " + user;
	}

	@Override
	public String getUser(String userName, String token) throws RemoteException {
		// TODO Auto-generated method stub
		if (verifyToken(token)) {
			return Users.getUser(userName);
		} else {
			return TOKEN_INVALIDO;
		}
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

	@Override
	public String getLogs() throws RemoteException {
		// TODO Auto-generated method stub
		return Logs.getlogs();
	}

	@Override
	public String deleteLog(String logDateTime) throws RemoteException {
		// TODO Auto-generated method stub
		Logs.deleteLog(logDateTime);
		return "Log removido com sucesso -> " + logDateTime;
	}

	@Override
	public String postLog(String userName, String token, String dateTime)
			throws RemoteException {
		Log log = new Log(userName, token, dateTime);

		Logs.postLog(log);

		return "Log criado com sucesso -> " + log;
	}

	@Override
	public void writeLogsFile() throws RemoteException {
		// TODO Auto-generated method stub
		Logs.writeFile();
	}

	@Override
	public void loadLogsFile() throws RemoteException {
		// TODO Auto-generated method stub
		Logs.loadFile();
	}

	private boolean verifyToken(String token) {
		CORBA_Falae serverCorba;
		try {
			serverCorba = ServerUtils.getServerCorba();
			if (serverCorba.verifyToken(token)) {
				return true;
			} else {
				return false;
			}
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}