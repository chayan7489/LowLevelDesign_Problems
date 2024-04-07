package ParkingLotMain;

import ParkingLotMain.entity.Command;
import ParkingLotMain.entity.DisplayType;
import ParkingLotMain.entity.ParkingLot;
import ParkingLotMain.entity.VehicleType;
import ParkingLotMain.service.ParkingLotService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ParkingLotService parkingLotService = new ParkingLotService();

        while (true) {
            Scanner scan = new Scanner(System.in);
            String str = scan.next();
            // Command is "static"
            // Command type = Command.of(scan.next());
            Command type = Command.of(str);
            switch (type) {
                case CREATE_PARKING_LOT: parkingLotService.createParkingLot(new ParkingLot(scan.next(),scan.nextInt(),scan.nextInt()));
                    break;
                case PARK_VEHICLE: parkingLotService.parkVehicle(VehicleType.valueOf(scan.next()),scan.next(),scan.next());
                    break;
                case UNPARK_VEHICLE: parkingLotService.unParkVehicle(scan.next());
                    break;
                case DISPLAY: parkingLotService.display(DisplayType.of(scan.next()),VehicleType.valueOf(scan.next()));
                    break;
                case EXIT:
                    return;
            }
        }
    }
}
