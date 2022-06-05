package UI;

import Application.MainWindow;
import UI.TableViews.TWConsultants;
import UI.TableViews.TWOffices;
import UI.TableViews.TWProjects;
import UI.TableViews.TWTasks;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Report
{

    // Current date
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SimpleDateFormat formatterFile = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
    private final Date date = new Date();

    public Report()
    {

    }

    /**
     * Saves a report of the subject defined as a parameter. For example consultant or project.
     * @param subject
     */
    public void saveReport(String subject)
    {
        // Create a Document instance
        Document document = new Document();

        // Add a section
        Section section = document.addSection();

        // -- First paragraph which is a title --
        Paragraph titleParagraph = section.addParagraph();
        titleParagraph.appendText("Report: " + subject);

        // The title shouldn't start at the top end of a page
        titleParagraph.getFormat().setBeforeSpacing(25f);

        // Set style for titleParagraph
        ParagraphStyle styleTitle = new ParagraphStyle(document);
        styleTitle.setName("styleTitle");
        styleTitle.getCharacterFormat().setBold(true);
        styleTitle.getCharacterFormat().setFontName("Arial");
        styleTitle.getCharacterFormat().setFontSize(12f);
        document.getStyles().add(styleTitle);
        titleParagraph.applyStyle(styleTitle.getName());

        // Horizontally align paragraph 1 to center
        titleParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        // Set spacing after paragraph
        titleParagraph.getFormat().setAfterSpacing(15f);

        //  -- Second paragraph which is a date --
        Paragraph dateParagraph = section.addParagraph();
        dateParagraph.appendText(formatter.format(date));

        // Set style for dateParagraph
        ParagraphStyle styleDate = new ParagraphStyle(document);
        styleDate.setName("paraStyle");
        styleDate.getCharacterFormat().setFontName("Arial");
        styleDate.getCharacterFormat().setFontSize(11f);
        document.getStyles().add(styleDate);
        dateParagraph.applyStyle(styleDate.getName());

        // Horizontally align paragraph 2 to right
        dateParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Right);

        // Set spacing after paragraph
        dateParagraph.getFormat().setAfterSpacing(10f);

        //  -- Third paragraph which is a field the user should change in the document --
        Paragraph inputParagraph = section.addParagraph();
        inputParagraph.appendText("** INSERT TEXT **");

        // Set spacing after paragraph
        inputParagraph.getFormat().setAfterSpacing(10f);

        // Code below depends on the chosen report subject
        switch (MainWindow.sender.getText())
        {
            case "Consultants":
                consultantsReport(section, styleTitle);
                break;

            case "Offices":
                officesReport(section, styleTitle);
                break;

            case "Projects":
                projectsReport(section, styleTitle);
                break;

            case "Tasks":
                tasksReport(section, styleTitle);
                break;
        }

        // Save the document
        document.saveToFile("Report " + subject + " " + formatterFile.format(date) + ".docx", FileFormat.Docx);
    }

    /**
     * Base report on data from the Consultant TableView.
     */
    public void consultantsReport(Section section, ParagraphStyle style)
    {
        // Add bullet list of consultants
        for (int i = 0; i < TWConsultants.consultants.size(); i++)
        {
            Paragraph paragraph = section.addParagraph();
            paragraph.appendText(TWConsultants.consultants.get(i).getName());
            paragraph.applyStyle(style.getName());

            for (int j = 0; j < TWTasks.tasks.size(); j++)
            {
                if (TWTasks.tasks.get(j).getConsultantMail().equalsIgnoreCase(TWConsultants.consultants.get(i).getMail()))
                {
                    Paragraph subParagraph = section.addParagraph();

                    if (TWTasks.tasks.get(j).getCompleted())
                    {
                        subParagraph.appendText(TWTasks.tasks.get(j).getName() + "✓");
                    }
                    else
                    {
                        subParagraph.appendText(TWTasks.tasks.get(j).getName());
                    }
                    subParagraph.getListFormat().applyBulletStyle();
                    subParagraph.getListFormat().getCurrentListLevel().setNumberPosition(-10);
                }
            }
        }
    }

    /**
     * Base report on data from the Office TableView.
     */
    public void officesReport(Section section, ParagraphStyle style)
    {
        // Add bullet list of offices
        for (int i = 0; i < TWOffices.offices.size(); i++)
        {
            Paragraph paragraph = section.addParagraph();
            paragraph.appendText(TWOffices.offices.get(i).getName());
            paragraph.applyStyle(style.getName());

            Paragraph subParagraph = section.addParagraph();
            for (int j = 0; j < TWConsultants.consultants.size(); j++)
            {
                if (TWOffices.offices.get(i).getName().equalsIgnoreCase(TWConsultants.consultants.get(j).getOffice()))
                {
                    subParagraph.appendText(TWConsultants.consultants.get(j).getName() + "\n");
                }
            }
            subParagraph.getListFormat().applyBulletStyle();
            subParagraph.getListFormat().getCurrentListLevel().setNumberPosition(-10);
        }
    }

    /**
     * Base report on data from the Project TableView.
     */
    public void projectsReport(Section section, ParagraphStyle style)
    {
        // Add bullet list of projects
        for (int i = 0; i < TWProjects.projects.size(); i++)
        {
            Paragraph paragraph = section.addParagraph();
            paragraph.appendText(TWProjects.projects.get(i).getName());
            paragraph.applyStyle(style.getName());

            int completedTasks = 0;
            int activeTasks = 0;

            Paragraph subParagraph = section.addParagraph();
            for (int j = 0; j < TWTasks.tasks.size(); j++)
            {
                if (TWTasks.tasks.get(j).getProjectId() == TWProjects.projects.get(i).getId())
                {
                    if (TWTasks.tasks.get(j).getCompleted())
                    {
                        completedTasks++;
                    }
                    else
                    {
                        activeTasks++;
                    }
                }
            }
            subParagraph.appendText("Completed tasks: " + completedTasks + "\n");
            subParagraph.appendText("Still in process: " + activeTasks);
            subParagraph.getListFormat().applyBulletStyle();
            subParagraph.getListFormat().getCurrentListLevel().setNumberPosition(-10);
        }
    }

    /**
     * Base report on data from the Task TableView.
     */
    public void tasksReport(Section section, ParagraphStyle style)
    {
        // Add bullet list of tasks
        for (int i = 0; i < TWProjects.projects.size(); i++)
        {
            Paragraph paragraph = section.addParagraph();
            paragraph.appendText(TWProjects.projects.get(i).getName());
            paragraph.applyStyle(style.getName());

            for (int j = 0; j < TWTasks.tasks.size(); j++)
            {
                if (TWTasks.tasks.get(j).getProjectId() == TWProjects.projects.get(i).getId())
                {
                    Paragraph subParagraph = section.addParagraph();

                    if (TWTasks.tasks.get(j).getCompleted())
                    {
                        subParagraph.appendText(TWTasks.tasks.get(j).getName() + "✓");
                    }
                    else
                    {
                        subParagraph.appendText(TWTasks.tasks.get(j).getName());
                    }
                    subParagraph.getListFormat().applyBulletStyle();
                    subParagraph.getListFormat().getCurrentListLevel().setNumberPosition(-10);
                }
            }
        }
    }
}
