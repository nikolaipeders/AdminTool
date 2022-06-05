package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWTasks;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class LoadTasks extends Thread
{
    /**
     * Runnable thread for loading all data from the Tasks' table in DB to an observable list found belonging to TWTasks.
     */
    public void run()
    {
        DBController dbController = new DBController();
        TWTasks.tasks = dbController.getAllTasks();
        System.out.println("tasks started");
    }
}
