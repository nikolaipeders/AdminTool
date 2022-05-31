package Foundation.Interfaces;

import Domain.Consultant;
import javafx.collections.ObservableList;

public interface ConsultantDAO
{
    public ObservableList<Consultant> getAllConsultants();
    public Consultant getConsultant(int ID);
    public void updateOrInsertConsultant(Consultant consultant);
    public void deleteConsultant(Consultant consultant);
    public void moveUpConsultant(Consultant consultant);
    public void moveDownConsultant(Consultant consultant);
}
