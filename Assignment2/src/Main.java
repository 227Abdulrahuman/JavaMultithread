import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        List<Thread> AllCars = new ArrayList<>();

        System.out.println("Enter the file path: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] part = line.split(", ");
                int g = Integer.parseInt(part[0].split(" ")[1]);
                int i = Integer.parseInt(part[1].split(" ")[1]);
                int at = Integer.parseInt(part[2].split(" ")[1]);
                int pt = Integer.parseInt(part[3].split(" ")[1]);


                Car car = new Car(g,i,at,pt);
                AllCars.add(car);
                car.start();
            }
        }

        catch (IOException e ) {
            System.out.println("File doesn't exist in running directory");
        }


        for(Thread car : AllCars) {
            car.join();
        }

        System.out.println("Total Cars Served: " + parkingManager.totalServedCars);
        System.out.println("Current Cars in Parking: " + parkingManager.currentlyParkedCars);
        System.out.println("Detals:");
        System.out.println("- Gate 1 Served "+ parkingManager.gates[0]+" cars.");
        System.out.println("- Gate 2 Served "+ parkingManager.gates[1]+" cars.");
        System.out.println("- Gate 3 Served "+ parkingManager.gates[2]+" cars.");
    }
}