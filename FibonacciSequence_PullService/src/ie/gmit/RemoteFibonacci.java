package ie.gmit;
import java.rmi.*;

public interface RemoteFibonacci extends Remote {
	
	public String getFibonacciSequence(int max) throws RemoteException;
}
