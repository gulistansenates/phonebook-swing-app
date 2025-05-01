import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;

class Contact {
    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String toString() {
        return name + " - " + phone;
    }
}

public class PhoneBookSwingApp extends JFrame {
    private final JTextField nameField = new JTextField(15);
    private final JTextField phoneField = new JTextField(15);
    private final DefaultListModel<Contact> contactListModel = new DefaultListModel<>();
    private final JList<Contact> contactList = new JList<>(contactListModel);
    private final JTextField searchField = new JTextField(15);

    public PhoneBookSwingApp() {
        setTitle("📞 Phone Book");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        JPanel listPanel = new JPanel(new BorderLayout());
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPanel.add(new JScrollPane(contactList), BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            if (!name.isEmpty() && !phone.isEmpty()) {
                contactListModel.addElement(new Contact(name, phone));
                nameField.setText("");
                phoneField.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = contactList.getSelectedIndex();
            if (selectedIndex != -1) {
                contactListModel.remove(selectedIndex);
            }
        });

        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim().toLowerCase();
            for (int i = 0; i < contactListModel.size(); i++) {
                if (contactListModel.get(i).name.toLowerCase().contains(query)) {
                    contactList.setSelectedIndex(i);
                    contactList.ensureIndexIsVisible(i);
                    break;
                }
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PhoneBookSwingApp::new);
    }
}
