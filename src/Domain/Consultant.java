package Domain;

public class Consultant
{
    String name;
    String mail;
    String office;
    String workTime;
    String breakTime;
    String longBreakTime;
    Boolean active;

    public Consultant(String name, String mail, String office, String workTime, String breakTime, String longBreakTime, Boolean active)
    {
        this.name = name;
        this.mail = mail;
        this.office = office;
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public String getLongBreakTime() {
        return longBreakTime;
    }

    public void setLongBreakTime(String longBreakTime) {
        this.longBreakTime = longBreakTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
