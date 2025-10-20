package domain;

public class User {
    private int id;
    private String name;
    private Role role;

    public User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public User(String name, Role role) {
        this.id = 0;
        this.name = name;
        this.role = role;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public String getInfo(){
        return " - "+getName()+": "+getRole().getRole()+" | ID: "+getId();
    }
}
