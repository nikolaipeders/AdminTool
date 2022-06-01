package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWTasks;

public class LoadTasks extends Thread
{
    public void run() {

        DBController dbController = new DBController();
        TWTasks.tasks = dbController.getAllTasks();
        System.out.println("tasks started");
    }
}
