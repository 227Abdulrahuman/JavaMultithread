import java.util.concurrent.Semaphore;

public class parkingManager {
    public static final int spotsNumber = 4;
    public static final Semaphore Spots = new Semaphore(spotsNumber);
    public static int totalServedCars = 0;
    public static int currentlyParkedCars = 0;
    public static int[] gates = new int[3];

    public static synchronized void sendMessage (String parkMessage) {
        System.out.println(parkMessage);
    }




}
