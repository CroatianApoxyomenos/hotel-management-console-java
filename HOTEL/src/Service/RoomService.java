package Service;

import Domain.Room;
import Domain.RoomProfitabilityDTO;
import Domain.RoomTypesDTO;
import Repository.RoomRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Creates a Room object.
     * @param id the ID of the Room object. ID must be unique.
     * @param name the NAME of the Room object. NAME is not null.
     * @param price the PRICE of the Room object. PRICE is a positive float.
     * @param beds the BEDS of the Room object. BEDS is a positive integer between 1 and 5.
     * @param type the TYPE of the Room object. TYPE must be [Regular, Business, Executive, VIP]
     * @throws Exception if params requirements are not met.
     */
    public void addOrUpdate(int id, String name, float price, int beds, String type, String option) throws Exception {
        Room room = new Room(id, name, price, beds, type);
        RoomValidate roomValidate = new RoomValidate(room);

        if (!roomValidate.isNameValid())
            throw new Exception("Name is not valid!");
        if (!roomValidate.isPriceValid())
            throw new Exception("Price must be a positive float!");
        if (!roomValidate.isBedsValid())
            throw new Exception("Number of beds must be between 1 and 5!");
        if (!roomValidate.isTypeValid())
            throw new Exception("Type must be [Regular, Business, Executive, VIP]!");

        if (option.equals("1"))
            this.roomRepository.create(room);
        else
            this.roomRepository.update(room);
    }

    public void delete(int id) throws Exception {
        this.roomRepository.delete(id);
    }

    public List<RoomProfitabilityDTO> getRoomsSortedByProfitability() {
        List<Room> allRooms = this.getAll();
        List<RoomProfitabilityDTO> result = new ArrayList<>();
        for (Room room : allRooms) {
            result.add(new RoomProfitabilityDTO(room, room.getPrice() / room.getBeds()));
        }

        result = result.stream()
                .sorted(Comparator.comparing(RoomProfitabilityDTO::getPriceOverBeds))
                .collect(Collectors.toList());

        return result;
    }

    public List<RoomTypesDTO> getAverageBedsByRoomType() {
        int averageRegular = 0, averageBusiness = 0, averageExecutive = 0, averageVIP = 0;
        int numRegular = 0, numBusiness = 0, numExecutive = 0, numVIP = 0;
        List<Room> allRooms = this.getAll();
        List<RoomTypesDTO> result = new ArrayList<>();
        for (Room room : allRooms) {
            if (room.getType().equals("Regular")) {
                numRegular += 1;
                averageRegular += room.getBeds();
            }
            if (room.getType().equals("Business")) {
                numBusiness += 1;
                averageBusiness += room.getBeds();
            }
            if (room.getType().equals("Executive")) {
                numExecutive += 1;
                averageExecutive += room.getBeds();
            }
            if (room.getType().equals("VIP")) {
                numVIP += 1;
                averageVIP += room.getBeds();
            }
        }
        result.add(new RoomTypesDTO("Regular", (float) averageRegular / numRegular, numRegular, averageRegular));
        result.add(new RoomTypesDTO("Business", (float) averageBusiness / numBusiness, numBusiness, averageBusiness));
        result.add(new RoomTypesDTO("Executive", (float) averageExecutive / numExecutive, numExecutive, averageExecutive));
        result.add(new RoomTypesDTO("VIP", (float) averageVIP / numVIP, numVIP, averageVIP));
        return result;
    }

    /**
     * Gets a list with all Room objects.
     * @return a list with all Room objects.
     */
    public List<Room> getAll() {
        return this.roomRepository.readAll();
    }
}
