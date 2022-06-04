package Foundation.Interfaces;

import Domain.Project;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public interface ProjectDAO
{
    ObservableList<Project> getAllProjects();
    LinkedList<String> getProjectNames();
    void updateOrInsertProject(Project project);
    void deleteProject(Project project);
    void moveUpProject(Project project);
    void moveDownProject(Project project);
}
