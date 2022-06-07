package Foundation.Interfaces;

import Domain.Consultant;
import javafx.collections.ObservableList;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public interface ConsultantDAO
{
    ObservableList<Consultant> getAllConsultants();
    void updateOrInsertConsultant(Consultant consultant);
    void deleteConsultant(Consultant consultant);
    void moveUpConsultant(Consultant consultant);
    void moveDownConsultant(Consultant consultant);
}
