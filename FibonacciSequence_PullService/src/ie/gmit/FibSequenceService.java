package ie.gmit;

import java.rmi.*;


public interface FibSequenceService extends Remote {
	public String getFibonacci(int max) throws RemoteException;

	
}
