package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWOffices;

public class LoadOffices extends Thread
{
        public void run()
        {
            DBController dbController = new DBController();
            TWOffices.offices = dbController.getAllOffices();
            System.out.println("offices started");
        }
}
