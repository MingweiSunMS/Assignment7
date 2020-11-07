import java.util.Random;


public class SumValue {
	
	static long sumResult;
	
	static class SumThread extends Thread{
		int[] arr;
		int start;
		
		SumThread(int start, int[] arr){
			this.start=start;
			this.arr=arr;
		}
		
		@Override
		public void run() {
			System.out.println("Thread "+start+" start");
			for(int i=(start-1)*1000000;i<start*1000000;i++) {
				sumResult+=this.arr[i];
			}
		}
	}
	
	/* generate array of random numbers */
	static void generateRandomArray(int[] arr) {
		Random rd=new Random();
		for(int i = 0;i<arr.length;i++) {
			arr[i]=rd.nextInt();
		}
	
	}

	/* get sum of an array using 4 threads */
	static long sum(int[] arr) {
		
		SumThread sumThread1=new SumThread(1,arr);
		SumThread sumThread2=new SumThread(2,arr);
		SumThread sumThread3=new SumThread(3,arr);
		SumThread sumThread4=new SumThread(4,arr);
		
		sumThread1.start();
		sumThread2.start();
		sumThread3.start();
		sumThread4.start();
		
		try {
			sumThread1.join();
			sumThread2.join();
			sumThread3.join();
			sumThread4.join();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		
		return sumResult;
	}

	public static void main(String[] args) {

		int[] arr = new int[4000000];
		generateRandomArray(arr);
		long sum = sum(arr);
		System.out.println("Sum: " + sum);
	}

}
