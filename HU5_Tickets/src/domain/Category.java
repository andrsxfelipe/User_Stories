package domain;

public class Category {
    private int id;
    private String category;
    private int tickets;

    public Category(int id) {
        this.id = id;
        switch (id) {
            case 1 -> this.category = "Infraestructura";
            case 2 -> this.category = "Aplicación";
            case 3 -> this.category = "Cuenta";
            default -> this.category = "Desconocida";
        }
    }

    public Category(String category, int tickets) {
        this.category = category;
        this.tickets = tickets;
    }

    public Category(String category){
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getTickets() {
        return tickets;
    }

    public String getInfo(){
        return "- Categoria: "+getCategory()+" Nro tickets: "+getTickets();
    }
}
