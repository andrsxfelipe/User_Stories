package domain;

import java.time.LocalDate;

public class Ticket {
    private int id;
    private String title;
    private String description;
    private User reporter;
    private User asignee;
    private Category category;
    private Status status;
    private LocalDate createdDate;

    public Ticket(String title, String description, User reporter, Category category) {
        this.title = title;
        this.description = description;
        this.reporter = reporter;
        this.category = category;
        this.status = new Status(1);
    }

    public Ticket(String title, String description, User reporter, User asignee, Category category, Status status){
        this.title = title;
        this.description = description;
        this.reporter = reporter;
        this.asignee = asignee;
        this.category = category;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getReporter() {
        return reporter;
    }

    public Category getCategory() {
        return category;
    }

    public String getNameReporter(){
        return reporter.getName();
    }

    public String getNameAsignee(){
        return asignee.getName();
    }

    public String getCategoryName(){
        return category.getCategory();
    }

    public String getStatusName(){
        return status.getStatus();
    }

    public String getInfo(){
        return " - Titulo: "+getTitle()+" | Descripcion: "+getDescription()+" | Reporter: "+getNameReporter()+
                " | Asignado: "+getNameAsignee()+" | Categoria: "+getCategoryName()+"| Estado: "+getStatusName();
    }
}

