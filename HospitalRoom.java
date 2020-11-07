import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HospitalRoom {
	// TODO: implement your code here

	private int numOfDoctor = 0;
	private int numOfPatient = 0;
	private static final int MAXIMUM_DOCTOR_NUM = 1;
	private static final int MAXIMUM_PATIENT_NUM = 3;
	private static Object lock = new Object();

	public boolean doctorEnter(Doctor d) throws InterruptedException {
		// TODO: implement your code here
		synchronized (lock) {
			while (true) {
				boolean result;
				if (numOfDoctor < MAXIMUM_DOCTOR_NUM) {
					numOfDoctor++;
					System.out.println(d.name + " enter the room. Number of doctor is " + numOfDoctor);
					result = true;
					lock.wait();
				} else {
					System.out.println(d.name + " is waiting. Number of doctor is " + numOfDoctor);
					result = false;
					lock.notify();
				}
				return result;
			}
		}

	}

	public boolean doctorLeave(Doctor d) throws InterruptedException {
		// TODO: implement your code here
		synchronized (lock) {

			while (true) {
				if (numOfDoctor > 0) {
					numOfDoctor = numOfDoctor - 1;
					System.out.println(d.name + " leave. Num of doctor is " + numOfDoctor);
					lock.notify();
					return true;
				}
				lock.wait();
				return false;

			}
		}

	}

	public boolean patientEnter(Patient p) throws InterruptedException {
		// TODO: implement your code here
		synchronized (lock) {
			while (true) {

				boolean result;
				if (numOfPatient < MAXIMUM_PATIENT_NUM) {
					numOfPatient++;
					System.out.println(p.name + " enter the room. Number of Patient is " + numOfPatient);
					result = true;
					lock.notify();
				} else {
					System.out.println(p.name + " is waiting. Number of Patient is " + numOfPatient);
					result = false;
					lock.wait();
				}
				return result;
			}
		}
	}

	public boolean patientLeave(Patient p) throws InterruptedException {
		// TODO: implement your code here
		synchronized (lock) {
			while (true) {

				if (numOfPatient > 0) {
					numOfPatient = numOfPatient - 1;
					System.out.println(p.name + " leave. Number of Patient is " + numOfPatient);
					lock.notify();
					return true;
				}
				lock.wait();
				return false;
			}
		}
	}

}

class Doctor {
	public String name;

	public Doctor(String name) {
		this.name = name;
	}
}

class Patient {
	public String name;

	public Patient(String name) {
		this.name = name;
	}
}

class Main2 {
	public static void main(String[] args) {
		HospitalRoom room = new HospitalRoom();
		Doctor siva = new Doctor("siva");
		Doctor john = new Doctor("john");
		Patient p1 = new Patient("p1");
		Patient p2 = new Patient("p2");
		Patient p3 = new Patient("p3");
		Patient p4 = new Patient("p4");
		Patient p5 = new Patient("p5");
		Thread doctor1 = new Thread(() -> {
			try {
				while (!room.doctorEnter(siva)) {
				}
				Thread.sleep(500);
				while (!room.doctorLeave(siva)) {
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread doctor2 = new Thread(() -> {
			try {
				while (!room.doctorEnter(john)) {
				}
				Thread.sleep(500);
				while (!room.doctorLeave(john)) {
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread patient1 = new Thread(() -> {
			try {
				while (!room.patientEnter(p1)) {
				}
				Thread.sleep(500);
				while (!room.patientLeave(p1)) {
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread patient2 = new Thread(() -> {
			try {
				while (!room.patientEnter(p2)) {
				}
				;
				Thread.sleep(500);
				while (!room.patientLeave(p2)) {
				}
				;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread patient3 = new Thread(() -> {
			try {
				while (!room.patientEnter(p3)) {
				}
				;
				Thread.sleep(500);
				while (!room.patientLeave(p3)) {
				}
				;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread patient4 = new Thread(() -> {
			try {
				while (!room.patientEnter(p4)) {
				}
				;
				Thread.sleep(500);
				while (!room.patientLeave(p4)) {
				}
				;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread patient5 = new Thread(() -> {
			try {
				while (!room.patientEnter(p5)) {
				}
				;
				Thread.sleep(500);
				while (!room.patientLeave(p5)) {
				}
				;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		doctor1.start();
		doctor2.start();
		patient1.start();
		patient2.start();
		patient3.start();
		patient4.start();
		patient5.start();

	}
}