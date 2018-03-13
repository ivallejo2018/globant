package globant;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class CounterThread implements Runnable {

	private int counter;
	private int addValue;
	private int limit;
	
	public CounterThread(int counter, int addValue, int limit) {
		this.counter = counter;
		this.addValue = addValue;
		this.limit = limit;
	}
	
	@Override
	public void run() {
		while(counter != limit) {
			counter = counter + addValue;
			System.out.println("Value: " + counter);	
		}
	}
	
}

class PingPongPlayer implements Runnable {

	private PingPongBall ball;
	private boolean playerStarts;
	private boolean withReentrantLock;
	
	public PingPongPlayer(PingPongBall ball, boolean playerStarts, boolean withReentrantLock) {
		this.ball = ball;
		this.playerStarts = playerStarts;
		this.withReentrantLock = withReentrantLock;
	}

	@Override
	public void run() {
		try {
			if(playerStarts) 
				if(withReentrantLock) ball.hitLockBall();
				else ball.hitBall();
			else	
				if(withReentrantLock) ball.waitLockBall();
				else ball.waitBall();	
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class PingPongBall {

	public boolean hasBall;
	public boolean hasEnded;
	public ReentrantLock lock = new ReentrantLock();
	
	public void waitLockBall() throws InterruptedException {
		if(hasEnded) return;
		lock.lock();
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			hitLockBall();
		}
	}
	
	public void hitLockBall() throws InterruptedException {
		if(hasEnded) return;
		lock.lock();
		try {
			Thread.sleep(3000);	
			System.out.println("Ball hit by: " + Thread.currentThread().getName());
			notifyAll();			
		} finally {
			lock.unlock();
			Thread.sleep(1000);				
			waitLockBall();			
		}
	}
	
	public void waitBall() throws InterruptedException {
		if(hasEnded) return;
		hasBall = false;
		synchronized(this) {
			while(!hasBall) {
				try {
					System.out.println("Waiting the ball: " + Thread.currentThread().getName());
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}

		hitBall();
	} 
	   
	public void hitBall() throws InterruptedException {
		if(hasEnded) return;
		Thread.sleep(3000);
		synchronized(this) {
			System.out.println("Ball hit by: " + Thread.currentThread().getName());
			hasBall = true;
			notifyAll();
		}
		Thread.sleep(1000);
				
		waitBall();
	}
	
}

class Singleton {
	
	private static Singleton singleton = null;
	private final int id;
	private final String something;
	
	private Singleton() {
		this.id = 1;
		this.something = "Something";
	}
	
	public static synchronized Singleton getInstance() {
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
}

public class ThreadSample {
	
	public static void main(String[] args) throws InterruptedException {

//		CounterThread c1 = new CounterThread(0, 1, 100);
//		CounterThread c2 = new CounterThread(100, -1, 0);
//		new Thread(c1).start();
//		new Thread(c2).start();
		int time = 20000;
		
		PingPongBall lockball = new PingPongBall();
		PingPongBall ball = new PingPongBall();
		
		long start = System.currentTimeMillis();
		System.out.println("Invoking threads using Executor");
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(new PingPongPlayer(lockball, true, true));
		executor.submit(new PingPongPlayer(lockball, false, true));		
		while(System.currentTimeMillis() - start < time){}
		lockball.hasEnded = true;
		executor.shutdown();
		
		start = System.currentTimeMillis();
		System.out.println("Invoking threads in normal way");
		new Thread(new PingPongPlayer(ball, true, false)).start();
		new Thread(new PingPongPlayer(ball, false, false)).start();
		while(System.currentTimeMillis() - start < time){}
		ball.hasEnded = true;
		
		Singleton s = Singleton.getInstance();
		System.out.println(s);
		System.out.println(Singleton.getInstance());
	}

}
