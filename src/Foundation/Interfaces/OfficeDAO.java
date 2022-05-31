package Foundation.Interfaces;

import Domain.Office;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public interface OfficeDAO
{
    public ObservableList<Office> getAllOffices();
    public LinkedList<String> getOfficeNames();
    public Office getOffice(String OfficeName);
    public void updateOrInsertOffice(Office office);
    public void deleteOffice(Office office);
    public void moveUpOffice(Office office);
    public void moveDownOffice(Office office);


}
