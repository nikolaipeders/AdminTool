package Foundation.Interfaces;

import Domain.Project;
import javafx.collections.ObservableList;

public interface ProjectDAO
{
    ObservableList<Project> getAllProjects();
    void updateOrInsertProject(Project project);
    void deleteProject(Project project);
    void moveUpProject(Project project);
    void moveDownProject(Project project);
}
