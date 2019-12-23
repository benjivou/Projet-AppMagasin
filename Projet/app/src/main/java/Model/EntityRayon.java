package Model;

public class EntityRayon {

    private int idRayon;
    private String name;
    private EntityEmployee employee;


    public EntityRayon(){}

    public EntityRayon(int idRayon, String name){
        this.idRayon = idRayon;
        this.name = name;

    }

    public EntityRayon(int idRayon, String name, EntityEmployee employee){
        this.idRayon = idRayon;
        this.name = name;
        this.employee = employee;
    }

    /*Getters & Setters*/
    public int getIdRayon() {
        return idRayon;
    }

    public void setIdRayon(int idRayon) {
        this.idRayon = idRayon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(EntityEmployee employee) {
        this.employee = employee;
    }

}
