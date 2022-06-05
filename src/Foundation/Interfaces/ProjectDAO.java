package Foundation.Interfaces;

import Domain.Project;
import javafx.collections.ObservableList;
import java.util.LinkedList;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public interface ProjectDAO
{
    ObservableList<Project> getAllProjects();
    LinkedList<String> getProjectNames();
    void updateOrInsertProject(Project project);
    void deleteProject(Project project);
    void moveUpProject(Project project);
    void moveDownProject(Project project);
}
