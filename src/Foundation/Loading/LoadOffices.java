package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWOffices;

/**
 * Created by Nikolai P on 05-06-2022.
 */

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
