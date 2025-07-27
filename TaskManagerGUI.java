
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class TaskManagerGUI {
    private TaskManager manager;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public TaskManagerGUI() {
        manager = new TaskManager();
        JFrame frame = new JFrame("SmartTask - Java Task Manager");
        frame.setSize(600, 400);
frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
frame.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
        manager.saveTasksToFile();
        System.out.println("Tasks saved to file.");
        frame.dispose(); // close the window
        System.exit(0);
    }
});

        // Task list display
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        refreshTaskList();

        JScrollPane scrollPane = new JScrollPane(taskList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom buttons
        JPanel panel = new JPanel();
        JButton addBtn = new JButton("Add Task");
        JButton doneBtn = new JButton("Mark Done");
        JButton deleteBtn = new JButton("Delete");

        panel.add(addBtn);
        panel.add(doneBtn);
        panel.add(deleteBtn);
        frame.add(panel, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> showAddTaskDialog());
        doneBtn.addActionListener(e -> markSelectedDone());
        deleteBtn.addActionListener(e -> deleteSelectedTask());

        frame.setVisible(true);
    }

    private void refreshTaskList() {
        taskListModel.clear();
        for (Task t : manager.getAllTasks()) {
            taskListModel.addElement(t.toString());
        }
    }

    private void showAddTaskDialog() {
        JTextField titleField = new JTextField();
        JTextField descField = new JTextField();
        JTextField dateField = new JTextField("2025-08-01");
        String[] priorities = {"High", "Medium", "Low"};
        JComboBox<String> priorityBox = new JComboBox<>(priorities);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(descField);
        panel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel("Priority:"));
        panel.add(priorityBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Task",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String desc = descField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            String priority = (String) priorityBox.getSelectedItem();

            Task task = new Task(title, desc, date, priority);
            manager.addTask(task);
            refreshTaskList();
        }
    }

    private void markSelectedDone() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            manager.markTaskCompleted(index);
            refreshTaskList();
        }
    }

    private void deleteSelectedTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            manager.deleteTask(index);
            refreshTaskList();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerGUI::new);
    }
}
