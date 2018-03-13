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
	private boolean playerStarts;
	
	public PingPongPlayer(PingPongBall ball, boolean playerStarts) {
		this.ball = ball;
		this.playerStarts = playerStarts;
	}

	@Override
	public void run() {
		try {
			if(playerStarts) 
				ball.hitBall();
			else	
				ball.waitBall();			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class PingPongBall {

	public boolean hasBall;
	public boolean hasEnded;
	
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
		
		long start = System.currentTimeMillis();
		PingPongBall ball = new PingPongBall();
		new Thread(new PingPongPlayer(ball, true)).start();
		new Thread(new PingPongPlayer(ball, false)).start();
		while(System.currentTimeMillis() - start < 30000){}
		ball.hasEnded = true;
		
		Singleton s = Singleton.getInstance();
		System.out.println(s);
		System.out.println(Singleton.getInstance());
	}

}
