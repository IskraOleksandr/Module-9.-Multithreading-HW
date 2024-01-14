import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class Race {
    public static AtomicLong startRaceTime;
    public static CountDownLatch latch;

    public static void startRace(List<Thread> cars) {
        Thread startThread = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 3; i >= 0; i--) {
                        Thread.sleep(500);
                        if (i == 0) {
                            System.out.println("GO!!!");
                            for (Thread car : cars) {
                                car.start();
                            }
                        } else {
                            System.out.println(i + "...");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        startThread.start();
    }

    public static void main(String[] args) {
        List<RaceCarRunnable> cars = new ArrayList<>();
        int carCount = 5; // Number of race cars
        int distance = 500; // Length of the track

        latch = new CountDownLatch(carCount);
        startRaceTime = new AtomicLong();

        for (int i = 1; i <= carCount; i++) {
            cars.add(new RaceCarRunnable("Car " + i, 200, distance, latch));
        }

        List<Thread> carThreads = new ArrayList<>();
        for (RaceCarRunnable car : cars) {
            carThreads.add(new Thread(car));
        }

        startRace(carThreads);

        try {
            latch.await();
            System.out.println("\nRace Results:");
            for (RaceCarRunnable car : cars) {
                long finishTime = car.getFinishTime();
                System.out.println(car.getName() + " finished in " + convertToTime(finishTime));
            }
            System.out.println("\nWinner: " + getWinner(cars).getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String convertToTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        return format.format(time);
    }

    public static RaceCarRunnable getWinner(List<RaceCarRunnable> cars) {
        long minFinishTime = Long.MAX_VALUE;
        RaceCarRunnable winner = null;
        for (RaceCarRunnable car : cars) {
            long finishTime = car.getFinishTime();
            if (finishTime < minFinishTime) {
                minFinishTime = finishTime;
                winner = car;
            }
        }
        return winner;
    }
}
