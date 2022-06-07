package Foundation.Interfaces;

import Domain.Task;
import javafx.collections.ObservableList;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public interface TaskDAO
{
    ObservableList<Task> getAllTasks();
    void updateOrInsertTask(Task task);
    void deleteTask(Task task);
    void moveUpTask(Task task);
    void moveDownTask(Task task);
}
