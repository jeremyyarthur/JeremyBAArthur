import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ClassProject2 {
    public static void main(String[] args) {
        // Create the main application frame
        JFrame frame = new JFrame("Class Project 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create components
        JLabel label = new JLabel("Enter file path:");
        JTextField filePathField = new JTextField(20);
        JButton loadButton = new JButton("Load File");
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        // Panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.add(label);
        inputPanel.add(filePathField);
        inputPanel.add(loadButton);

        // Scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listener for the Load File button
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathField.getText();
                try {
                    // Attempt to read the file
                    String content = readFile(filePath);
                    textArea.setText(content);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, "File not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "An error occurred while reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    /**
     * Reads the content of a file and returns it as a string.
     * @param filePath The path to the file.
     * @return The content of the file.
     * @throws IOException If an I/O error occurs.
     */
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        
        return content.toString();
    }
}