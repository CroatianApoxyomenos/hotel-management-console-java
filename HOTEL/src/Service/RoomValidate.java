package Service;

import Domain.Room;

public class RoomValidate {
    private Room room;

    public RoomValidate(Room room) {
        this.room = room;
    }

    public boolean isNameValid() {
        return this.room.getName().matches("[A-Za-z]*");
    }

    public boolean isPriceValid() {
        return this.room.getPrice() > 0f;
    }

    public boolean isBedsValid() {
        return this.room.getBeds() >= 1 && this.room.getBeds() <= 5;
    }

    public boolean isTypeValid() {
        return this.room.getType().equals("Regular") || this.room.getType().equals("Business")
                ||this.room.getType().equals("Executive") || this.room.getType().equals("VIP");
    }
}
