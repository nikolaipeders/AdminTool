package Testing;

import Domain.Consultant;
import Foundation.Database.DatabaseConnection;
import Foundation.Modified.TextFieldAutoCompletion;
import Foundation.Modified.TextFieldValidation;
import javafx.embed.swing.JFXPanel;
import org.junit.*;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class Testing
{
    public static DatabaseConnection instance; // Refers to the original (production) code


    @Before
    public void setUp()
    {
        System.out.println("Before ...");
        instance = new DatabaseConnection();
    }

    @After
    public void tearDown()
    {
        System.out.println("After ...");
        instance = null;
    }

    /**
     * Test if successfully connecting to DB set up in properties file
     */
    @Test
    public void testGetConnection() throws SQLException
     {
        Connection con = DatabaseConnection.connect();
        Assert.assertNotNull(con);
        con.close();
    }

    /**
     * Test if regex for text input returns false when only integers are entered WRONG INPUT
     */
    @Test
    public void testRegexTextWrongInput()
    {
        TextFieldValidation textFieldValidation = new TextFieldValidation();
        textFieldValidation.setText("1234"); // Integers instead of A String
        textFieldValidation.validate("text");
        Assert.assertTrue(textFieldValidation.isWrongInput);
    }

    /**
     * Test if regex for text input returns false when only integers are entered CORRECT INPUT
     */
    @Test
    public void textRegexTextCorrectInput()
    {
        TextFieldValidation textFieldValidation = new TextFieldValidation();
        textFieldValidation.setText("This is a String");
        textFieldValidation.validate("text");
        Assert.assertFalse(textFieldValidation.isWrongInput);
    }

    /**
     * Test if regex for text input returns false when only integers are entered WRONG INPUT
     */
    @Test
    public void testRegexNumberWrongInput()
    {
        TextFieldValidation textFieldValidation = new TextFieldValidation();
        textFieldValidation.setText("This is text and note integers"); // String instead of Integers
        textFieldValidation.validate("numbers");
        Assert.assertTrue(textFieldValidation.isWrongInput);
    }

    /**
     * Test if regex for text input returns false when only integers are entered CORRECT INPUT
     */
    @Test
    public void testRegexNumberCorrectInput()
    {
        TextFieldValidation textFieldValidation = new TextFieldValidation();
        textFieldValidation.setText("1234");
        textFieldValidation.validate("numbers");
        Assert.assertFalse(textFieldValidation.isWrongInput);
    }

    /**
     * Test if regex for text input returns false when only integers are entered WRONG INPUT
     */
    @Test
    public void testRegexTimeWrongInput()
    {
        TextFieldValidation textFieldValidation = new TextFieldValidation();
        textFieldValidation.setText("14:00:00"); // Should be MM:SS
        textFieldValidation.validate("mm:ss");
        Assert.assertTrue(textFieldValidation.isWrongInput);
    }

    /**
     * Test if regex for text input returns false when only integers are entered CORRECT INPUT
     */
    @Test
    public void testRegexTimeCorrectInput()
    {
        TextFieldValidation textFieldValidation = new TextFieldValidation();
        textFieldValidation.setText("59:59");
        textFieldValidation.validate("mm:ss");
        Assert.assertFalse(textFieldValidation.isWrongInput);
    }

    /**
     * Test if regex for text input returns false when only integers are entered WRONG INPUT
     */
    @Test
    public void testRegexMailWrongInput()
    {
        TextFieldValidation textFieldValidation = new TextFieldValidation();
        textFieldValidation.setText("nikolai@mail."); // Missing the .com
        textFieldValidation.validate("mail");
        Assert.assertTrue(textFieldValidation.isWrongInput);
    }

    /**
     * Test if regex for text input returns false when only integers are entered CORRECT INPUT
     */
    @Test
    public void testRegexMailCorrectInput()
    {
        TextFieldValidation textFieldValidation = new TextFieldValidation();
        textFieldValidation.setText("nikolai@mail.com");
        textFieldValidation.validate("mail");
        Assert.assertFalse(textFieldValidation.isWrongInput);
    }

    /**
     * Test if context menu of a text field with auto-completion actually contains data
     */
    @Test
    public void testSearchContextMenu()
    {
        TextFieldAutoCompletion textFieldAutoCompletion = new TextFieldAutoCompletion();
        textFieldAutoCompletion.getResults().add("a string");
        Assert.assertNotNull(textFieldAutoCompletion.resultMenu);
    }
}