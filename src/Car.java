import static java.lang.Thread.sleep;

public class Car {// implements Runnable
    private String name;
    private int maxSpeed;

    public Car(String name, int maxSpeed) {
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    public String getName() {
        return name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

//    @Override
//    public void run() {
//        while (!isFinish) {
//            try {
//                Thread.sleep(1000);
//                int speed = getRandomSpeed();
//                passed += speed;
//                System.out.println(getName() + " => speed: " + speed + "; progress: " + passed + "/" + distance);
//                if (passed >= distance) {
//                    isFinish = true;
//                    latch.countDown();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(getName() + " FINISHED !");
//    }
}
