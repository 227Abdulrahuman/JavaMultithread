import java.util.concurrent.TimeUnit;

public class Car extends Thread{
    private int gate;
    private int id;
    private int arriveTime;
    private int parkingTime;

    public Car(int g, int i, int at, int pt) {
        this.gate = g; this.id =i; this.arriveTime = at; this.parkingTime = pt;


    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(arriveTime);
            parkingManager.sendMessage("Car "+ id + " from Gate " + gate + " arrived at time " + arriveTime);

            if (parkingManager.Spots.tryAcquire()) {
                parkTheCar();
            } else {
                waitThenpark();
            }
        } catch (InterruptedException e) {
            System.out.println("Car number " + id + "encountered an error");
        }
    }


    public void parkTheCar () throws InterruptedException {
        synchronized (parkingManager.class) {
            parkingManager.totalServedCars++;
            parkingManager.gates[gate - 1]++;
            parkingManager.currentlyParkedCars++;

            parkingManager.sendMessage("Car " + id + " from Gate " + gate + " parked. (Parking Status: " + parkingManager.currentlyParkedCars + " spots occupied)");

            TimeUnit.SECONDS.sleep(parkingTime);
        }

        synchronized (parkingManager.class) {
            parkingManager.currentlyParkedCars--;
            parkingManager.sendMessage("Car " + id + " from Gate "+gate+" left after " +parkingTime+" units of time. (Parking Status: "+parkingManager.currentlyParkedCars+" spots occupied)");

        }
        parkingManager.Spots.release();

    }

    public void waitThenpark() throws InterruptedException {
        parkingManager.sendMessage("Car "+id+" from Gate "+gate+" waiting for a spot.");
        parkingManager.Spots.acquire();
        parkTheCar();
    }
}
