package model;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String name;
    private String phone;
    private LocalDate birth;

    public Usuario(String name, String phone, LocalDate birth) {
        this.birth = birth;
        this.phone = phone;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }
}
