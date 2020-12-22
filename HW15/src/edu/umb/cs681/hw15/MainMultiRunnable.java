package edu.umb.cs681.hw15;

public class MainMultiRunnable {
	public static void main(String[] args) {
		AdmissionControl admissionControl = new AdmissionControl();
		AdmissionMonitor admissionMonitor = new AdmissionMonitor(admissionControl);
		EntranceHandler entranceHandler = new EntranceHandler(admissionControl);
		ExitHandler exitHandler = new ExitHandler(admissionControl);
		
		Thread Threadmonitor = new Thread(admissionMonitor);
		Thread ThreadEntrance = new Thread(entranceHandler);
		Thread ThreadExit = new Thread(exitHandler);
		

		Threadmonitor.start();
		ThreadEntrance.start();
		ThreadExit.start();

		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		admissionMonitor.setDone();
		entranceHandler.setDone();
		exitHandler.setDone();

		Threadmonitor.interrupt();
		ThreadEntrance.interrupt();
		ThreadExit.interrupt();

		try {
			Threadmonitor.join();
			ThreadEntrance.join();
			ThreadExit.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
