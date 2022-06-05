package Domain;

import java.sql.Time;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class Consultant
{
    int orderNo;
    String name;
    String mail;
    String office;
    Time workTime;
    Time breakTime;
    Time longBreakTime;
    Boolean active;

    public Consultant() {}

    public Consultant(int orderNo, String name, String mail, String office, Time workTime, Time breakTime, Time longBreakTime, Boolean active)
    {
        this.orderNo = orderNo;
        this.name = name;
        this.mail = mail;
        this.office = office;
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.active = active;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
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

    public Time getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Time workTime) {
        this.workTime = workTime;
    }

    public Time getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Time breakTime) {
        this.breakTime = breakTime;
    }

    public Time getLongBreakTime() {
        return longBreakTime;
    }

    public void setLongBreakTime(Time longBreakTime) {
        this.longBreakTime = longBreakTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
