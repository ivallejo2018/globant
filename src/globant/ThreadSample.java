package globant;

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
	
	public PingPongPlayer(PingPongBall ball) {
		this.ball = ball;
	}

	@Override
	public void run() {
		ball.hitBall();	
	}
	
	
}

class PingPongBall {

	public boolean hasBall;
	public boolean hasEnded;
	
	public synchronized void hitBall() {
		
		hasBall = true;
		try {
			Thread.currentThread().wait(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ball hit by: " + Thread.currentThread().getName());
		notifyAll();
	}
	
}

public class ThreadSample {
	
	private int time = 10000;
	
	public static void main(String[] args) throws InterruptedException {
		ThreadSample ts = new ThreadSample();
//		CounterThread c1 = new CounterThread(0, 1, 100);
//		CounterThread c2 = new CounterThread(100, -1, 0);
//		new Thread(c1).start();
//		new Thread(c2).start();
		long start = System.currentTimeMillis();
		
		PingPongBall ball = new PingPongBall();
		new Thread(new PingPongPlayer(ball)).start();
		new Thread(new PingPongPlayer(ball)).start();
		
		while(System.currentTimeMillis() - start < ts.time) {
			
		}
	}

}
