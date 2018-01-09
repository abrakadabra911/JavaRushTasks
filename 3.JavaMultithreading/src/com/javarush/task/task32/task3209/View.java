package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by leha on 2017-07-11.
 */
public class View extends JFrame implements ActionListener {

    public View ()  {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        catch (Exception e) {
            ExceptionHandler.log(e);
            }
    }
    private Controller controller;

    private JTabbedPane tabbedPane = new JTabbedPane();

    private JEditorPane htmlTextPane = new JEditorPane(); // WAS TEXTPANE

    private JEditorPane plainTextPane = new JEditorPane();

    public Controller getController() {
        return controller;
    }

    private UndoManager undoManager = new UndoManager();

    private UndoListener undoListener = new UndoListener(undoManager);

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Новый": controller.createNewDocument(); break;
            case "Открыть": controller.openDocument(); break;
            case "Сохранить": controller.saveDocument(); break;
            case "Сохранить как...": controller.saveDocumentAs(); break;
            case "Выход": controller.exit(); break;
            case "О программе": showAbout(); break;
        }
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);

        getContentPane().add(menuBar, BorderLayout.NORTH);
        pack();
    }

    public void initEditor() {
        htmlTextPane.setEditorKit(new HTMLEditorKit());   //new
        htmlTextPane.setContentType("text/html");


        tabbedPane.addTab("HTML",new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст",new JScrollPane(plainTextPane));

        tabbedPane.setPreferredSize(new Dimension(800, 600));
        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // colorization of background
        plainTextPane.setBackground(new Color(225,255,227));
        plainTextPane.setSelectedTextColor(new Color(1,1,227));

        pack();

    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged(){
        int numberOfSelectedTab = tabbedPane.getSelectedIndex();
        switch (numberOfSelectedTab) {
            case 0: controller.setPlainText(plainTextPane.getText());
            case 1: plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {
        try {
            undoManager.undo();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        if (tabbedPane.getSelectedIndex() == 0) return true;
        return false;
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update() {
        HTMLDocument doc = controller.getDocument();
        htmlTextPane.setDocument(doc);

    }

    public void showAbout() {

        JOptionPane.showMessageDialog(this, "Dana aplikacja zostałą stworzona w ramach online java-kursu \"javarush\" przez " +
                        "Aliaksei Zayats. Zostały również dodane kilka dodatkowych funkcjonalności.",
                "O programie", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setURL(String text) {
        try {
            plainTextPane.setPage(text);
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
            System.out.println("Wrong URL, try again");
        }
    }
}
