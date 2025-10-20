package domain;

public class Status {
    private int id;
    private String status;

    public Status(int id) {
        this.id = id;

        switch (id) {
            case 1 -> this.status = "Abierto";
            case 2 -> this.status = "En Proceso";
            case 3 -> this.status = "Cerrado";
            default -> this.status = "Desconocido";
        }
    }

    public Status(String status) {
        this.status = status;

        switch (status.toLowerCase()) {
            case "abierto" -> this.id = 1;
            case "en proceso" -> this.id = 2;
            case "cerrado" -> this.id = 3;
            default -> this.id = 0;
        }
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
