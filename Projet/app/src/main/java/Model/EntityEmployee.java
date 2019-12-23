package Model;

public class EntityEmployee {

    private int idEmployee;
    private String name;
    private String sex;
    private String password;
    private EntityRole role;

    public EntityEmployee(){}

    public EntityEmployee(int idEmployee, String name, String sex, String password, EntityRole role){
        this.idEmployee = idEmployee;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.role = role;
    }

    /*Getters  & Setters*/
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EntityRole getRole() {
        return role;
    }

    public void setRole(EntityRole role) {
        this.role = role;
    }


}
