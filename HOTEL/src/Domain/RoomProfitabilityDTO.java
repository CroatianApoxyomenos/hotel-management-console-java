package Domain;

public class RoomProfitabilityDTO {

    public Room room;
    public float priceOverBeds;

    public RoomProfitabilityDTO(Room room, float priceOverBeds) {
        this.room = room;
        this.priceOverBeds = priceOverBeds;
    }

    public float getPriceOverBeds() {
        return priceOverBeds;
    }

    @Override
    public String toString() {
        return "RoomProfitabilityDTO{" +
                "room=" + room +
                ", priceOverBeds=" + priceOverBeds +
                '}';
    }
}
