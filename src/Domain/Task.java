package Domain;

public class Task
{
    int id;
    String consultantMail;
    int projectId;
    String name;
    String timeSpent;
    Boolean completed;

    public Task(int id, String consultantMail, int projectId, String name, String timeSpent, Boolean completed)
    {
        this.id = id;
        this.consultantMail = consultantMail;
        this.projectId = projectId;
        this.name = name;
        this.timeSpent = timeSpent;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsultantMail() {
        return consultantMail;
    }

    public void setConsultantMail(String consultantMail) {
        this.consultantMail = consultantMail;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
