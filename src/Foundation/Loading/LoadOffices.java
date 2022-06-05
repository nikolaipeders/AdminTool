package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWOffices;

public class LoadOffices extends Thread
{
    /**
     * Runnable thread for loading all data from the Offices' table in DB to an observable list belonging to TWOffices.
     */
    public void run()
    {
        DBController dbController = new DBController();
        TWOffices.offices = dbController.getAllOffices();
        System.out.println("offices started");
    }
}
