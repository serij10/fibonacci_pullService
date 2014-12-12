package ie.gmit;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class Scheduler {

	private static Scheduler instance = null;

	protected Scheduler() {

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				try {
					getSequence();
				} catch (RemoteException | MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}, 0, 2000);
	}

	public static Scheduler getInstance() {
		if (instance == null) {
			instance = new Scheduler();
		}
		return instance;
	}


	public static String getSequence() throws RemoteException,
			MalformedURLException {

		String seq = null;
		if (FibonacciService.getInQueue().isEmpty() != true) {
			FibonacciRequest fibRequest = FibonacciService.getInQueue().get(0);
			int i = fibRequest.getMax();
			System.out.println(i);
			FibonacciClient fc = new FibonacciClient();
			try {
				seq=fc.callRMI(i);
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(seq);
			
			FibonacciService.getInQueue().remove(0);
			FibonacciService.getOutQueue().put(fibRequest.getJobNumber(), seq);
		}
		
		return seq;

	}
}
