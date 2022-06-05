package Foundation.Interfaces;

import Domain.Office;
import javafx.collections.ObservableList;
import java.util.LinkedList;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public interface OfficeDAO
{
    public ObservableList<Office> getAllOffices();
    public LinkedList<String> getOfficeNames();
    public void updateOrInsertOffice(Office office);
    public void deleteOffice(Office office);
    public void moveUpOffice(Office office);
    public void moveDownOffice(Office office);


}
