package lab3.authorization;

public class Response {
    String title;
    String text;
    String status;

    String personId;
    String personPass;

    public Response(String title, String text, String status) {
        this.title = title;
        this.text = text;
        this.status = status;
    }

    public Response(String personId, String personPass) {
        this.personId = personId;
        this.personPass = personPass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
