package clients;

import java.util.Date;

import servers.ServerUtils;
import Corba.CORBA_Falae;

public class ClientCorba {

	public static void main(String args[]) {

		try {
			CORBA_Falae server = ServerUtils.getServerCorba();

			testAllServices(server);

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}

	private static void testAllServices(CORBA_Falae server) {

		System.out.println();
		System.out.println("post log: " + server.postLog("helton", "asdsad", new Date(new Date().getTime()).toString()));
		System.out.println("post log: " + server.postLog("helton2", "684684", new Date(new Date().getTime()).toString()));

		System.out.println("\n LISTAR LOGS: \n" + server.getLogs());
		
		System.out.println();
		//System.out.println("generate token: " + server.generateToken("helton", "123"));
		
		System.out.println();
		//String token = server.generateToken("helton", "987654");
		
		System.out.println("verify a token: " + server.verifyToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjNoZWx0b24iLCJpc3MiOiJodHRwOlwvXC9oZWx0b24tcGM6ODA4MCIsImV4cCI6MTQ2NDQ2MTc0M30.6jEOjJAJc779jAKrsF0Gqz79yAaxe7k3NFGcLfrTOrU"));
		
		//System.out.println("\n LISTAR USERS: \n" + server.getUsers());
	}

}
