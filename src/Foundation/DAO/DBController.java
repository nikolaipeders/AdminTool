package Foundation.DAO;

import Domain.Consultant;
import Domain.Office;
import Foundation.Database.DatabaseConnection;
import Foundation.Interfaces.ConsultantDAO;
import Foundation.Interfaces.OfficeDAO;
import Foundation.Interfaces.ProjectDAO;
import Foundation.Interfaces.TaskDAO;
import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;

public class DBController implements ConsultantDAO, OfficeDAO, ProjectDAO, TaskDAO
{
    static Connection con = DatabaseConnection.connect();

    @Override
    public ObservableList<Consultant> getAllConsultants()
    {
        ResultSet rs = null;
        PreparedStatement cs = null;
        ObservableList<Consultant> consultants = FXCollections.observableArrayList();
        try
        {
            cs = con.prepareCall("{call Get_Consultants}");
            rs = cs.executeQuery();
            while (rs.next())
            {
                Consultant c = new Consultant();
                c.setMail(rs.getString(1));
                c.setName(rs.getString(2));
                c.setOrderNo(rs.getInt(3));
                c.setOffice(rs.getString(4));
                c.setWorkTime(rs.getTime(5));
                c.setBreakTime(rs.getTime(6));
                c.setLongBreakTime(rs.getTime(7));
                c.setActive(rs.getBoolean(8));

                consultants.add(c);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return consultants;
    }

    @Override
    public Consultant getConsultant(int ID)
    {
        return null;
    }

    @Override
    public void updateOrInsertConsultant(Consultant consultant)
    {
        PreparedStatement cs = null;
        try
        {
            cs = con.prepareCall("{call updatingConsultant (?,?,?,?,?,?,?,?)}");
            cs.setInt(1, consultant.getOrderNo());
            cs.setString(2, consultant.getName());
            cs.setString(3, consultant.getMail());
            cs.setString(4, consultant.getOffice());
            cs.setTime(5, consultant.getWorkTime());
            cs.setTime(6, consultant.getBreakTime());
            cs.setTime(7, consultant.getLongBreakTime());
            cs.setBoolean(8, consultant.getActive());
            cs.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteConsultant(Consultant consultant)
    {
        PreparedStatement cs = null;
        try
        {
            cs = con.prepareCall("{call Delete_Consultant (?)}");
            cs.setString(1, consultant.getMail());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveUpConsultant(Consultant consultant)
    {
        if (TWConsultants.consultantTableView.getSelectionModel().getSelectedItem() != null)
        {
            if (TWConsultants.consultantTableView.getSelectionModel().getSelectedIndex() != 0)
            {
                // The index of the item we want to move up
                int index = TWConsultants.consultantTableView.getSelectionModel().getSelectedIndex();

                // Swap with the index above
                Collections.swap(TWConsultants.consultants, index, index -1);

                index = TWConsultants.consultantTableView.getSelectionModel().getSelectedIndex();

                PreparedStatement cs = null;

                try
                {
                    cs = con.prepareCall("{call move_consultant_up (?,?)}");
                    cs.setInt(1, index );
                    cs.setString(2, consultant.getMail());
                    cs.execute();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void moveDownConsultant(Consultant consultant)
    {
        if (TWConsultants.consultantTableView.getSelectionModel().getSelectedItem() != null)
        {
            if (TWConsultants.consultantTableView.getSelectionModel().getSelectedIndex() != TWConsultants.consultantTableView.getItems().size())
            {
                // The index of the item we want to move up
                int index = TWConsultants.consultantTableView.getSelectionModel().getSelectedIndex();
                System.out.println(index);

                // Swap with the index above
                Collections.swap(TWConsultants.consultants, index, index +1);

                index = TWConsultants.consultantTableView.getSelectionModel().getSelectedIndex();
                System.out.println(index);

                PreparedStatement cs = null;

                try
                {
                    cs = con.prepareCall("{call move_consultant_down (?,?)}");
                    cs.setInt(1, index );
                    cs.setString(2, consultant.getMail());
                    cs.execute();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ObservableList<Office> getAllOffices()
    {
        ResultSet rs = null;
        PreparedStatement cs = null;
        ObservableList<Office> offices = FXCollections.observableArrayList();
        try
        {
            cs = con.prepareCall("{call Get_Offices}");
            rs = cs.executeQuery();
            while (rs.next())
            {
                Office o = new Office();
                o.setName(rs.getString(1));
                o.setLocation(rs.getInt(2));
                o.setCapacity(rs.getInt(3));
                o.setConsultantsConnected(rs.getInt(4));
                o.setOrder(rs.getInt(5));

                offices.add(o);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return offices;
    }

    @Override
    public LinkedList<String> getOfficeNames() {
        ResultSet rs = null;
        PreparedStatement cs = null;
        LinkedList<String> offices = new LinkedList<>();
        try
        {
            cs = con.prepareCall("{call Get_Offices}");
            rs = cs.executeQuery();
            while (rs.next())
            {
                String name = rs.getString(1);

                offices.add(name);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return offices;
    }

    @Override
    public Office getOffice(String OfficeName) {
        return null;
    }

    @Override
    public void updateOrInsertOffice(Office office)
    {
        PreparedStatement cs = null;
        try
        {
            cs = con.prepareCall("{call updatingOffice (?,?,?,?,?)}");
            cs.setInt(1, office.getOrder());
            cs.setString(2, office.getName());
            cs.setInt(3, office.getLocation());
            cs.setInt(4, office.getCapacity());
            cs.setInt(5, office.getConsultantsConnected());

            cs.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOffice(Office office) {

    }

    @Override
    public void moveUpOffice(Office office)
    {
        if (TWOffices.tableViewOffices.getSelectionModel().getSelectedItem() != null)
        {
            if (TWOffices.tableViewOffices.getSelectionModel().getSelectedIndex() != 0)
            {
                // The index of the item we want to move up
                int index = TWOffices.tableViewOffices.getSelectionModel().getSelectedIndex();
                System.out.println(index);

                // Swap with the index above
                Collections.swap(TWOffices.offices, index, index -1);

                index = TWOffices.tableViewOffices.getSelectionModel().getSelectedIndex();
                System.out.println(index);

                PreparedStatement cs = null;

                try
                {
                    cs = con.prepareCall("{call move_office_up (?,?)}");
                    cs.setInt(1, index );
                    cs.setString(2, office.getName());
                    cs.execute();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void moveDownOffice(Office office)
    {

        if (TWOffices.tableViewOffices.getSelectionModel().getSelectedItem() != null)
        {
            if (TWOffices.tableViewOffices.getSelectionModel().getSelectedIndex() != TWOffices.tableViewOffices.getItems().size())
            {
                // The index of the item we want to move up
                int index = TWOffices.tableViewOffices.getSelectionModel().getSelectedIndex();

                // Swap with the index above
                Collections.swap(TWOffices.offices, index, index +1);

                index = TWOffices.tableViewOffices.getSelectionModel().getSelectedIndex();

                PreparedStatement cs = null;

                try
                {
                    cs = con.prepareCall("{call move_office_down (?,?)}");
                    cs.setInt(1, index );
                    cs.setString(2, office.getName());
                    cs.execute();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
