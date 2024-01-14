import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RaceCarRunnable extends Car implements Runnable {
    private int passed;
    private int distance;
    private boolean isFinish;
    private CountDownLatch latch;
    //-----------------------------------
    private long finishTime;

    public RaceCarRunnable(String name, int maxSpeed, int distance, CountDownLatch latch) {
        super(name, maxSpeed);
        this.distance = distance;
        this.latch = latch;
    }

    private int getRandomSpeed() {
        Random random = new Random();
        int minSpeed = getMaxSpeed() / 2;
        int maxSpeed = getMaxSpeed();
        return random.nextInt((maxSpeed - minSpeed) + 1) + minSpeed;
    }

    public long getFinishTime() {
        return finishTime;
    }

    @Override
    public void run() {
        while (!isFinish()) {
            try {
                Thread.sleep(1000);
                int currentSpeed = getRandomSpeed();
                passed += currentSpeed;
                if (passed >= distance) {
                    isFinish = true;
                    latch.countDown();
                }
                System.out.println(getName() + " => speed: " + currentSpeed + "; progress: " + passed + "/" + distance);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isFinish() {
        return isFinish;
    }
}
