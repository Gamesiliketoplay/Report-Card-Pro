package project;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Panel extends JFrame {

    private int height = 500;
    private int width = 500;
    private int columns = 6;
    private int rows = 2;
    private JPanel gridPanel;

    public Panel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        // Create a menu bar with FlowLayout
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        // Create menu items
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem addRow = new JMenuItem("Add Row");

        // Add ActionListener for Exit
        exitItem.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        // Add ActionListener for Open
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        for (int i = 0; i < columns; i++) {
                            String data = scanner.next();
                            ((JComponent) gridPanel.getComponent(rows - 1 + i)).add(new JLabel(data));
                        }
                    }
                    scanner.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add menu items to the menu
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Adding items to File
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        // Adding items to Edit
        editMenu.add(addRow);

        // Create Grid with multiple rows and columns
        gridPanel = new JPanel(new GridLayout(rows, columns));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gridPanel.setPreferredSize(new Dimension(width - 15, height - 100));

        // Adding basic elements to Grid
        ((JComponent) gridPanel.add(new JLabel(" Name "))).setLayout(new FlowLayout(FlowLayout.LEFT));
        ((JComponent) gridPanel.add(new JLabel(" Subject 1 "))).setLayout(new FlowLayout(FlowLayout.LEFT));
        ((JComponent) gridPanel.add(new JLabel(" Subject 2 "))).setLayout(new FlowLayout(FlowLayout.LEFT));
        ((JComponent) gridPanel.add(new JLabel(" Subject 3 "))).setLayout(new FlowLayout(FlowLayout.LEFT));
        ((JComponent) gridPanel.add(new JLabel(" Subject 4 "))).setLayout(new FlowLayout(FlowLayout.LEFT));
        ((JComponent) gridPanel.add(new JLabel(" "))).setLayout(new FlowLayout(FlowLayout.LEFT));

        // Adding New Rows
        for (int j = 0; j < rows - 1; j++) {
            newRow(gridPanel, columns);
        }

        // Create a JPanel to help with the Layout of the GUI.

        // Create a JScrollPane and add the gridPanel to it
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.EAST); // Place the scroll pane on the Right

        setJMenuBar(menuBar);

        // Set visibility after adding components
        setVisible(true);
    }

    public void newRow(JPanel gridPanel, int columns) {
        for (int i = 0; i < columns - 1; i++) {
            JTextArea textArea = new JTextArea(" N/A ");
            textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gridPanel.add(textArea);
        }
        JButton button = new JButton("More Info");
        gridPanel.add(button);
    }

}
