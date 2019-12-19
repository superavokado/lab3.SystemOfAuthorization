package lab3.authorization;

public class Person {
    private String personId;
    private String personPass;
    private String date;
    private boolean rememberMe;

    public void copyPerson(Person person) {
        this.personId = person.getPersonId();
        this.personPass = person.getDate();
        this.date = person.getDate();
        this.rememberMe = person.getRememberMe();

    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonPass() {
        return personPass;
    }

    public void setPersonPass(String personPass) {
        this.personPass = personPass;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", personPass='" + personPass + '\'' +
                ", date='" + date + '\'' +
                ", rememberMe='" + rememberMe + '\'' +
                '}';
    }
}
