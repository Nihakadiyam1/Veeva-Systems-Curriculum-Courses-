package pojo;

public class Pet {

    private int id;
    private String name;
    private String status;

    // ✅ Default constructor
    public Pet() {}

    // ✅ Parameterized constructor
    public Pet(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // ✅ GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    // ✅ SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}