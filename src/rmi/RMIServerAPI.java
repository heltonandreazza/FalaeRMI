package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerAPI extends Remote {
	// GROUPS SERVICES
	String getGroups() throws RemoteException;

	String deleteGroup(String nameGroup) throws RemoteException;

	String postGroup(int id, String name, String desc, String userName)
			throws RemoteException;

	void writeGroupsFile() throws RemoteException;
	
	void loadGroupsFile() throws RemoteException;
	
	// USERS SERVICES
	String getUsers() throws RemoteException;

	String deleteUser(String userName) throws RemoteException;

	String postUser(int id, String name, String email) throws RemoteException;

	void writeUsersFile() throws RemoteException;
	
	void loadUsersFile() throws RemoteException;
	
}
