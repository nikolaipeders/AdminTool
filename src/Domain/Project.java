package Domain;

public class Project
{
    int id;
    String name;
    int order;

    public Project(int id, String name, int order)
    {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public Project()
    {
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
