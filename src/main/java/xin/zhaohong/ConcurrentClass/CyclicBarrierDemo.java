package xin.zhaohong.ConcurrentClass;

import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

public class CyclicBarrierDemo {
	
	//@Test
	public void CyclicBarrierTest() {
		int N = 3;
		BarrierAction barrierAction = new BarrierAction();
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(N,barrierAction);
		for(int i = 0;i < N;i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						cyclicBarrier.await();
						System.out.println("当前线程:"+Thread.currentThread().getName());
					} catch (InterruptedException e) {
						System.out.println(cyclicBarrier.isBroken());
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}).start();
			
		}
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(cyclicBarrier.isBroken());
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(cyclicBarrier.isBroken());
		new Scanner(System.in).next();//阻塞主线程
		
	}


@Test
public void CyclicBarrierIsBrokenTest() {
	int N = 2;
	BarrierAction barrierAction = new BarrierAction();
	final CyclicBarrier cyclicBarrier = new CyclicBarrier(N,barrierAction);
	Thread thread = new Thread(new Runnable() {
		public void run() {
			try {
				cyclicBarrier.await();
				System.out.println("当前线程:"+Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	});
	thread.start();
	thread.interrupt();
	try {
		cyclicBarrier.await();
	} catch (InterruptedException e) {
//		e.printStackTrace();
		System.out.println(cyclicBarrier.isBroken());
	} catch (BrokenBarrierException e) {
//		e.printStackTrace();
	}
	new Scanner(System.in).next();//阻塞主线程	
}

}


class BarrierAction implements Runnable{

	public BarrierAction(){
		
	}
	public void run() {
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("I'm BarrierAction.");
	}
	
}
