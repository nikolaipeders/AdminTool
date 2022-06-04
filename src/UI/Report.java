package UI;

import UI.TableViews.TWProjects;
import UI.TableViews.TWTasks;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report
{
    public Report()
    {

    }

    public BorderPane getView() throws IOException
    {
        BorderPane subRoot = new BorderPane();

        Button generateReportButton = new Button("Generate Report!");

        generateReportButton.setOnAction(event -> tasksReport());

        subRoot.setBottom(generateReportButton);

        return subRoot;
    }

    public void tasksReport()
    {

        // Create a Document instance
        Document document = new Document();

        // Current date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

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
        document.saveToFile("Tasks.docx", FileFormat.Docx);
    }
}
