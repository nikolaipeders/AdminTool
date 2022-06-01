package Foundation.Loading;

import Foundation.DAO.DBController;
import UI.TableViews.TWConsultants;

public class LoadConsultants extends Thread
{
    public void run() {

        DBController dbController = new DBController();
            TWConsultants.consultants = dbController.getAllConsultants();
            System.out.println("consultants started");
    }
}
