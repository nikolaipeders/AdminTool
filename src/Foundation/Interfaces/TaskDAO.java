package Foundation.Interfaces;

import Domain.Task;
import javafx.collections.ObservableList;

public interface TaskDAO
{
    public ObservableList<Task> getAllTasks();
    public void updateOrInsertTask(Task task);
    public void deleteTask(Task task);
    public void moveUpTask(Task task);
    public void moveDownTask(Task task);
}
