package ie.gmit;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FibonacciClient {

	public String callRMI(int max) throws RemoteException, MalformedURLException, NotBoundException {
		// TODO Auto-generated method stub
		//Ask the registry running on 10.2.2.65 and listening in port 1099 for the instannce of
		//the MessageService object that is bound to the RMI registry with the name howdayService.
		FibSequenceService fibService = (FibSequenceService) Naming.lookup("rmi://localhost:1099/FibonacciService");
		
		//Make the remote method invocation. This results in the RemoteMessage object being transferred
		//to us over the network in serialized form. 
		String message = fibService.getFibonacci(max);
		
		return message;
	}

}
