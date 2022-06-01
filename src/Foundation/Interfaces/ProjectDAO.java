package Foundation.Interfaces;

import Domain.Project;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public interface ProjectDAO
{
    public ObservableList<Project> getAllProjects();
    public LinkedList<String> getProjectNames();
    public Project getProject(String projectName);
    public void updateOrInsertProject(Project project);
    public void deleteProject(Project project);
    public void moveUpProject(Project project);
    public void moveDownProject(Project project);
}
