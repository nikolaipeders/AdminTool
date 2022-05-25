package Domain;

public class Office
{
    String name;
    int location;
    int capacity;
    int consultantsConnected;

    public Office(String name, int location, int capacity, int consultantsConnected)
    {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.consultantsConnected = consultantsConnected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getConsultantsConnected() {
        return consultantsConnected;
    }

    public void setConsultantsConnected(int consultantsConnected) {
        this.consultantsConnected = consultantsConnected;
    }
}
