package Domain;

public class RoomTypesDTO {
    public String roomType;
    public float bedsOverRoomTypes;


    public RoomTypesDTO(String roomType, float bedsOverRoomTypes) {
        this.roomType = roomType;
        this.bedsOverRoomTypes = bedsOverRoomTypes;
    }

    @Override
    public String toString() {
        return "RoomTypesDTO{" +
                "roomType='" + roomType + '\'' +
                ", bedsOverRoomTypes=" + bedsOverRoomTypes +
                '}';
    }
}
