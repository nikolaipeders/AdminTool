package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWProjects;

public class LoadProjects extends Thread
{
    public void run() {

        DBController dbController = new DBController();
        TWProjects.projects = dbController.getAllProjects();
        System.out.println("projects started");
    }
}
