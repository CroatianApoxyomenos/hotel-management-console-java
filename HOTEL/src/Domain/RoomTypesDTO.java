package Domain;

public class RoomTypesDTO {
    public String roomType;
    public float bedsOverRoomTypes;
    public int totalNumberOfRooms;
    public int totalNumberOfBeds;

    public RoomTypesDTO(String roomType, float bedsOverRoomTypes, int totalNumberOfRooms, int totalNumberOfBeds) {
        this.roomType = roomType;
        this.bedsOverRoomTypes = bedsOverRoomTypes;
        this.totalNumberOfRooms = totalNumberOfRooms;
        this.totalNumberOfBeds = totalNumberOfBeds;
    }

    @Override
    public String toString() {
        return "RoomTypesDTO{" +
                "roomType='" + roomType + '\'' +
                ", totalNumberOfRooms=" + totalNumberOfRooms +
                ", totalNumberOfBeds=" + totalNumberOfBeds +
                ", bedsOverRoomTypes=" + bedsOverRoomTypes +
                '}';
    }
}
