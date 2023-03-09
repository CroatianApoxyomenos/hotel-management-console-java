import Repository.RoomRepository;
import Service.RoomService;
import UserInterface.Console;

public class Main {
    public static void main(String[] args) throws Exception {
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository);
        Console console = new Console(roomService);

        roomService.addOrUpdate(1, "Diamond", 1000, 2, "VIP", "1");
        roomService.addOrUpdate(2, "Platinum", 500, 3, "Executive", "1");
        roomService.addOrUpdate(3, "Gold", 300, 4, "Business", "1");
        roomService.addOrUpdate(4, "Silver", 150, 4, "Regular", "1");
        roomService.addOrUpdate(5, "Wow", 1000, 2, "Executive", "1");
        roomService.addOrUpdate(6, "Mega", 250, 1, "Regular", "1");
        roomService.addOrUpdate(7, "Fain", 1500, 3, "VIP", "1");

        console.runConsole();
    }
}