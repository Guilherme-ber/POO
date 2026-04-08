package models;

public class Team {
    private String name;
    private String city;
    private int fundationYear;
    private Coach coach;

    public Team(String name, String city, int fundationYear) {
        this.name = name;
        this.city = city;
        this.fundationYear = fundationYear;
    }

    public void takeCoach(Coach t) {
        this.coach = new Coach(t);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Time: ").append(name).append(" - ").append(city);
        sb.append("\nFundado em: ").append(fundationYear);
        sb.append("\nTecnico Atual: ").append(coach != null ? coach.toString() : "Nenhum");
        return sb.toString();
    }

    // Getter's and Setter's
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public int getFundationYear() {
        return fundationYear;
    }
    public Coach getCoach() {
        return coach;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setFundationYear(int fundationYear) {
        this.fundationYear = fundationYear;
    }
    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}