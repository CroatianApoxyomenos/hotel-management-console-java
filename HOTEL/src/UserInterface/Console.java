package UserInterface;

import Domain.Room;
import Domain.RoomProfitabilityDTO;
import Domain.RoomTypesDTO;
import Service.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    private RoomService roomService;
    private Scanner scan;

    public Console(RoomService roomService) {
        this.roomService = roomService;
        this.scan = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. Add a room.");
        System.out.println("2. Update a room.");
        System.out.println("3. Delete a room.");
        System.out.println("4. Show all rooms.");
        System.out.println("5. Show profitability report.");
        System.out.println("6. Show beds over rooms report.");
        System.out.println("0. Exit");
        System.out.print("Enter your option: ");
    }

    public void handleAddOrUpdateRoom(String option) {
        try {
            System.out.print("Enter the ID: ");
            int id = scan.nextInt();

            System.out.print("Enter the name: ");
            String name = scan.next();

            System.out.print("Enter the price: ");
            float price = scan.nextFloat();

            System.out.print("Enter the number of beds: ");
            int beds = scan.nextInt();

            System.out.print("Enter the room type: ");
            String type = scan.next();

            this.roomService.addOrUpdate(id, name, price, beds, type, option);
            if (option.equals("1"))
                System.out.println("Room with ID: " + id + " was successfully added!");
            else
                System.out.println("Room with ID: " + id + " was successfully updated!");

        } catch (Exception e) {
            scan.nextLine();
            System.out.println(e.getMessage());
        }
    }

    public void handleDeleteRoom() {
        try {
            System.out.print("Enter the ID: ");
            int id = scan.nextInt();

            this.roomService.delete(id);
            System.out.println("Room with ID: " + id + " was successfully deleted!");

        } catch (Exception e) {
            scan.nextLine();
            System.out.println(e.getMessage());
        }
    }

    public void handleShowRooms() {
        for (Room room : this.roomService.getAll()) {
            System.out.println(room);
        }
    }

    public void handleShowProfitabilityReport() {
        for (RoomProfitabilityDTO rp : this.roomService.getRoomsSortedByProfitability()) {
            System.out.println(rp);
        }
    }

    public void handleShowTypesReport() {
        for (RoomTypesDTO rt : this.roomService.getAverageBedsByRoomType()) {
            System.out.println(rt);
        }
    }

    public void runConsole() {
        while (true) {
            this.showMenu();
            String option = this.scan.next();
            try {
                Integer.parseInt(option);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (option) {
                case "1":
                    this.handleAddOrUpdateRoom(option);
                    break;
                case "2":
                    this.handleAddOrUpdateRoom(option);
                    break;
                case "3":
                    this.handleDeleteRoom();
                    break;
                case "4":
                    this.handleShowRooms();
                    break;
                case "5":
                    this.handleShowProfitabilityReport();
                    break;
                case "6":
                    this.handleShowTypesReport();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }
}
