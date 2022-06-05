package UI;

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
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat formatterFile = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
    private Date date = new Date();

    public Report()
    {

    }

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


        // Save the document
        document.saveToFile("Project " + subject + " " + formatterFile.format(date) + ".docx", FileFormat.Docx);
    }

    public void consultantsReport()
    {

    }

    public void officesReport()
    {

    }

    public void projectsReport()
    {
        // Create a Document instance
        Document document = new Document();

        // Add a section
        Section section = document.addSection();

        // -- First paragraph which is a title --
        Paragraph titleParagraph = section.addParagraph();
        titleParagraph.appendText("Report: Projects");

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

        // Add bullet list of tasks
        for (int i = 0; i < TWProjects.projects.size(); i++)
        {
            Paragraph paragraph = section.addParagraph();
            paragraph.appendText(TWProjects.projects.get(i).getName());
            paragraph.applyStyle(styleTitle.getName());

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

        // Save the document
        document.saveToFile("Project report " + formatterFile.format(date) + ".docx", FileFormat.Docx);
    }

    public void tasksReport()
    {

        // Create a Document instance
        Document document = new Document();

        // Add a section
        Section section = document.addSection();

        // -- First paragraph which is a title --
        Paragraph titleParagraph = section.addParagraph();
        titleParagraph.appendText("Report: Tasks");

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

        // Add bullet list of tasks
        for (int i = 0; i < TWProjects.projects.size(); i++)
        {
            Paragraph paragraph = section.addParagraph();
            paragraph.appendText(TWProjects.projects.get(i).getName());
            paragraph.applyStyle(styleTitle.getName());

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

        // Save the document
        document.saveToFile("Task report " + formatterFile.format(date) + ".docx", FileFormat.Docx);
    }
}
