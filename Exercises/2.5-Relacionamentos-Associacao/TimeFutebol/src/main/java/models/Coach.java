package models;

public class Coach {
    private String name;
    private String nationality;
    private int birthYear;

    // Constructors
    public Coach() {}
    public Coach(String name, String nationality, int birthYear) {
        this.name = name;
        this.nationality = nationality;
        this.birthYear = birthYear;
    }

    // Copy
    public Coach(Coach other) {
        if (other != null) {
            this.name = other.name;
            this.nationality = other.nationality;
            this.birthYear = other.birthYear;
        }
    }

    @Override
    public String toString() {
        return name + " (" + nationality + "), Nascido em: " + birthYear;
    }

    // Getter's and Setter's
    public String getName() {
        return name;
    }
    public String getNationality() {
        return nationality;
    }
    public int getBirthYear() {
        return birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}