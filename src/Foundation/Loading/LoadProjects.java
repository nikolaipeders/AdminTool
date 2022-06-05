package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWProjects;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class LoadProjects extends Thread
{
    /**
     * Runnable thread for loading all data from the Projects' table in DB to an observable list found belonging to TWProjects.
     */
    public void run()
    {
        DBController dbController = new DBController();
        TWProjects.projects = dbController.getAllProjects();
        System.out.println("projects started");
    }
}
