package Service;

import Domain.Room;
import Domain.RoomProfitabilityDTO;
import Domain.RoomTypesDTO;
import Repository.RoomRepository;

import java.util.*;
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
        Map<String, List<Integer>> typesWithBeds = new HashMap<>();
        for (Room r : this.getAll()) {
            String type = r.getType();
            int beds = r.getBeds();
            if (!typesWithBeds.containsKey(type)) {
                typesWithBeds.put(type, new ArrayList<>());
            }
            typesWithBeds.get(type).add(beds);
        }
        List<RoomTypesDTO> result = new ArrayList<>();
        for (String type : typesWithBeds.keySet()) {
            float average = 0;
            for (float beds : typesWithBeds.get(type)) {
                average += beds;
            }
            average /= typesWithBeds.get(type).size();
            result.add(new RoomTypesDTO(type, average));
        }
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
