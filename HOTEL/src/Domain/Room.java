package Domain;

public class Room implements Comparable<Room> {
    private int id;
    private String name;
    private float price;
    private int beds;
    private String type;

    public Room(int id, String name, float price, int beds, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.beds = beds;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", beds=" + beds +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(Room o) {
        return Float.compare(price / beds, o.price / o.beds);
    }
}
