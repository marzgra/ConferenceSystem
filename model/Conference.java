package model;


import java.util.Date;
import java.util.List;

public class Conference {
private  int id;
    private Date data;
    private String description;
    private String name;

    List<Conference> conferences;


    public List<Conference> getConferences() {
        return conferences;
    }

    public Conference(int id, Date data, String description, String name) {
        this.data = data;
        this.description = description;
        this.name = name;

        this.id=id;

    }
    public Conference( int id,  String name,String description) {
        this.description = description;
        this.name = name;
        this.id=id;

    }
    public Conference(List<Conference> conferences) {

        this.conferences=conferences;

    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


}
