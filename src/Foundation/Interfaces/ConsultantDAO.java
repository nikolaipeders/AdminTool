package Foundation.Interfaces;

import Domain.Consultant;
import javafx.collections.ObservableList;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public interface ConsultantDAO
{
    public ObservableList<Consultant> getAllConsultants();
    public void updateOrInsertConsultant(Consultant consultant);
    public void deleteConsultant(Consultant consultant);
    public void moveUpConsultant(Consultant consultant);
    public void moveDownConsultant(Consultant consultant);
}
