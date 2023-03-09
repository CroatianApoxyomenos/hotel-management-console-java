package Repository;

import Domain.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepository {
    private Map<Integer, Room> storage = new HashMap<>();

    public RoomRepository() {

    }

    /**
     * Adds a Room object to the repository.
     * @param room the Room object to add. The ID must be unique.
     * @throws Exception if the ID exists.
     */
    public void create(Room room) throws Exception {
        if (this.storage.containsKey(room.getId())) {
            throw new Exception("Room with ID: " + room.getId() + " already exists!");
        }
        this.storage.put(room.getId(), room);
    }

    /**
     * Returns a list of all rooms.
     * @return a list with all room objects.
     */
    public List<Room> readAll() {
        return new ArrayList<>(this.storage.values());
    }

    /**
     * Updates the room object.
     * @param room the Room object to update.
     * @throws Exception if the ID doesn't exist.
     */
    public void update(Room room) throws Exception {
        if (!this.storage.containsKey(room.getId())) {
            throw new Exception("Room with ID: " + room.getId() + " doesn't exist!");
        }
        this.storage.put(room.getId(), room);
    }

    /**
     * Deletes the room object.
     * @param id the Room object ID to be deleted.
     * @throws Exception if the ID doesn't exist.
     */
    public void delete(int id) throws Exception {
        if (!this.storage.containsKey(id)) {
            throw new Exception("Room with ID: " + id + " doesn't exist!");
        }
        this.storage.remove(id);
    }
}
