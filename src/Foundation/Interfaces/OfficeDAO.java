package Foundation.Interfaces;

import Domain.Office;
import javafx.collections.ObservableList;
import java.util.LinkedList;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public interface OfficeDAO
{
    ObservableList<Office> getAllOffices();
    LinkedList<String> getOfficeNames();
    void updateOrInsertOffice(Office office);
    void deleteOffice(Office office);
    void moveUpOffice(Office office);
    void moveDownOffice(Office office);


}
