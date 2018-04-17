package xin.zhaohong.ConcurrentClass;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class CountDownLatchDemo {
	/**
	 * 测试CountDownLatch
	 * CountDownLatch初始化的计数值只能通过countDown方法递减，但是不能修改该值
	 * 
	 */
	@Test
	public void countDownLatchTest() {
		int N = 3;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch endSignal = new CountDownLatch(N);
		new Thread(new Target1(startSignal, endSignal)).start();
		new Thread(new Target2(startSignal, endSignal)).start();
		new Thread(new Target3(startSignal, endSignal)).start();
		//以上三个线程等待开始口令
		startSignal.countDown();//释放开始口令
		try {
			endSignal.await();
			//等到以上三个线程都结束了，再继续执行
			System.out.println("三个线程执行完毕,继续执行下面代码");
			System.out.println("开始做其他事情:::::::::::");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
 
/**
 * 封装任务1
 * @author zhaohong
 *
 */
class Target1 implements Runnable{
	private CountDownLatch startSignal;
	private CountDownLatch endSignal;
	public Target1(CountDownLatch startSignal,CountDownLatch endSignal) {
		this.startSignal = startSignal;
		this.endSignal = endSignal;
		
	}
	public void run() {
		try {
			startSignal.await();//等待发放开始口令
			System.out.println("当前线程:"+Thread.currentThread().getName()+";任务1开始执行;");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			endSignal.countDown();
		}
	}
}

/**
 * 封装任务2
 * @author zhaohong
 *
 */
class Target2 implements Runnable{
	private CountDownLatch startSignal;
	private CountDownLatch endSignal;
	public Target2(CountDownLatch startSignal,CountDownLatch endSignal) {
		this.startSignal = startSignal;
		this.endSignal = endSignal;
		
	}
	public void run() {
		try {
			startSignal.await();//等待发放开始口令
			System.out.println("当前线程:"+Thread.currentThread().getName()+";任务2开始执行;");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			endSignal.countDown();
		}
	}
}

/**
 * 封装任务3
 * @author zhaohong
 *
 */
class Target3 implements Runnable{
	private CountDownLatch startSignal;
	private CountDownLatch endSignal;
	public Target3(CountDownLatch startSignal,CountDownLatch endSignal) {
		this.startSignal = startSignal;
		this.endSignal = endSignal;
		
	}
	public void run() {
		try {
			startSignal.await();//等待发放开始口令
			System.out.println("当前线程:"+Thread.currentThread().getName()+";任务3开始执行;");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			endSignal.countDown();
		}
	}
}
