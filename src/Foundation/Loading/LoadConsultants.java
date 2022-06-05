package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWConsultants;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class LoadConsultants extends Thread
{
    /**
     * Runnable thread for loading all data from the Consultants' table in DB to an observable list belonging to TWConsultants.
     */
    public void run()
    {
        DBController dbController = new DBController();
            TWConsultants.consultants = dbController.getAllConsultants();
            System.out.println("consultants started");
    }
}
