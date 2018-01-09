package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;
import com.javarush.task.task32.task3209.listeners.UndoMenuListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by leha on 2017-07-11.
 */
public class Controller {
    private File currentFile;

    private View view;

    private HTMLDocument document;

    public HTMLDocument getDocument() {
        return document;
    }

    public Controller(View view) {
        this.view = view;
    }

    public void init() {
        createNewDocument();
    }

    public void resetDocument() {
        if(document != null) {
            document.removeUndoableEditListener(view.getUndoListener());}

            document = (HTMLDocument) (new HTMLEditorKit()).createDefaultDocument();
            document.addUndoableEditListener(view.getUndoListener());

        view.update();
        return;
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        int dialog = JOptionPane.showConfirmDialog(view, "For choose file from hard disc, click YES, from URL click NO"
                , "Choice of source", JOptionPane.YES_NO_CANCEL_OPTION);
        String inputURL;
        if (dialog == 1)  {
            inputURL = JOptionPane.showInputDialog("Please paste your URL");
            resetDocument();
            view.resetUndo();
            URL url;

            try {
                url = new URL(inputURL);
                URLConnection conn = url.openConnection();

                // open the stream and put it into BufferedReader
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                String inputLine;
                String textHTMLfromURL = "";
                while ((inputLine = br.readLine()) != null) {
                    textHTMLfromURL = textHTMLfromURL + inputLine;
                }
                System.out.println(textHTMLfromURL);
                br.close();
                setPlainText(textHTMLfromURL);
            }
            catch (Exception e) {ExceptionHandler.log(e);}


           // view.setURL(inputURL);
            return;
        }

        if (dialog == 2) return;


        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        int choise = chooser.showOpenDialog(view);
        if (choise == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            view.resetUndo();

            try (FileReader reader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(reader, document, 0);
                reader.close();
            }
            catch (Exception e) {ExceptionHandler.log(e);}
        }
     else {
        System.out.println("File Chooser was cancelled");}
    }

    public void saveDocument() {
        if (currentFile != null) {
        view.selectHtmlTab();
            try (FileWriter writer = new FileWriter(currentFile)){
                new HTMLEditorKit().write(writer,document,0,document.getLength());
                writer.flush();
                writer.close();
            }
            catch (Exception e) {ExceptionHandler.log(e);}
            return;
        }
        saveDocumentAs();
    }


    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        int choise = chooser.showSaveDialog(view);
        if (choise == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());

            try (FileWriter writer = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(writer,document,0,document.getLength());
                writer.flush();
                writer.close();
            }
            catch (Exception e) {ExceptionHandler.log(e);}
        }
    }

    public void setPlainText(String text) {
        resetDocument();

        try (StringReader reader = new StringReader(text)){
            new HTMLEditorKit().read(reader, document, 0);
            reader.close();
        }
        catch (Exception e) {ExceptionHandler.log(e);}
    }

    public void exit() {
        System.exit(0);
    }

    public String getPlainText() {
        String result = "";
        try (StringWriter writer = new StringWriter()){
            new HTMLEditorKit().write(writer,document,0,document.getLength());
            result = writer.toString();
            writer.flush();
            writer.close();

        }
        catch (Exception e) {ExceptionHandler.log(e);}
    return result;
    }

    public static void main(String[] args) {
            View view = new View();
        Controller controller = new Controller(view);

        view.setController(controller);

        view.init();
        controller.init();
    }



}
