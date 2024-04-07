package ParkingLotMain.entity;

import java.util.HashMap;
public enum Command {
    CREATE_PARKING_LOT("create_parking_lot"),
    DISPLAY("display"),
    PARK_VEHICLE("park_vehicle"),
    UNPARK_VEHICLE("unpark_vehicle"),
    EXIT("exit");

    private final String command;

    Command(String s) {
        command = s;
    }

    public String toString() {
        return this.command;
    }

//    private static final HashMap<String, Command> map = new HashMap<>(values().length, 1);

    static HashMap<String, Command> map = new HashMap<>();

//    static {
//        for (Command c : values()) {
//            map.put(c.command, c);
//        }
//    }


//    Iteration 1 (CREATE_PARKING_LOT):
//
//    c is CREATE_PARKING_LOT.
//    c.command is "create_parking_lot".
//    (c.command, c) is added to the map.

//        After completing the loop, the map will contain the following key-value pairs:
//        "create_parking_lot" maps to CREATE_PARKING_LOT
//       "display" maps to DISPLAY
//       "park_vehicle" maps to PARK_VEHICLE
//       "unpark_vehicle" maps to UNPARK_VEHICLE
//       "exit" maps to EXIT

    static {
        for (Command c : Command.values()) {
            map.put(c.command, c);
        }
    }

    public static Command of(String name){
        return map.get(name);
    }

}
