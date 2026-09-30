import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentGUI {

    // ---------- MYSQL DATABASE DETAILS ----------

    private static final String URL =
        "jdbc:mysql://localhost:3306/student_management";

private static final String USER =
        System.getenv("STUDENT_DB_USER");

private static final String PASSWORD =
        System.getenv("STUDENT_DB_PASSWORD");


    // ---------- DATABASE CONNECTION ----------

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Management System");

        frame.setSize(900, 650);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);


        // ---------- TITLE ----------

        JLabel title =
                new JLabel("STUDENT MANAGEMENT SYSTEM");

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setBounds(250, 20, 450, 40);

        frame.add(title);


        // ---------- INPUT LABELS ----------

        JLabel nameLabel =
                new JLabel("Name:");

        nameLabel.setBounds(
                50, 90, 100, 30
        );


        JLabel ageLabel =
                new JLabel("Age:");

        ageLabel.setBounds(
                50, 140, 100, 30
        );


        JLabel usnLabel =
                new JLabel("USN:");

        usnLabel.setBounds(
                450, 90, 100, 30
        );


        JLabel marksLabel =
                new JLabel("Marks:");

        marksLabel.setBounds(
                450, 140, 100, 30
        );


        frame.add(nameLabel);
        frame.add(ageLabel);
        frame.add(usnLabel);
        frame.add(marksLabel);


        // ---------- TEXT FIELDS ----------

        JTextField nameField =
                new JTextField();

        nameField.setBounds(
                120, 90, 250, 30
        );


        JTextField ageField =
                new JTextField();

        ageField.setBounds(
                120, 140, 250, 30
        );


        JTextField usnField =
                new JTextField();

        usnField.setBounds(
                520, 90, 250, 30
        );


        JTextField marksField =
                new JTextField();

        marksField.setBounds(
                520, 140, 250, 30
        );


        frame.add(nameField);
        frame.add(ageField);
        frame.add(usnField);
        frame.add(marksField);


        // ---------- BUTTONS ----------

        JButton addButton =
                new JButton("Add Student");

        addButton.setBounds(
                50, 200, 150, 40
        );


        JButton searchButton =
                new JButton("Search");

        searchButton.setBounds(
                220, 200, 120, 40
        );


        JButton updateButton =
                new JButton("Update");

        updateButton.setBounds(
                360, 200, 120, 40
        );


        JButton deleteButton =
                new JButton("Delete");

        deleteButton.setBounds(
                500, 200, 120, 40
        );


        JButton clearButton =
                new JButton("Clear");

        clearButton.setBounds(
                640, 200, 120, 40
        );


        frame.add(addButton);
        frame.add(searchButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(clearButton);


        // ---------- TABLE ----------

        String[] columns = {
                "Name",
                "Age",
                "USN",
                "Marks"
        };


        DefaultTableModel tableModel =
                new DefaultTableModel(columns, 0);


        JTable table =
                new JTable(tableModel);


        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(
                50, 270, 710, 280
        );


        frame.add(scrollPane);


        // ==================================================
        // LOAD STUDENTS FROM MYSQL
        // ==================================================

        loadStudents(tableModel);


        // ==================================================
        // ADD STUDENT
        // ==================================================

        addButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText().trim();

                String ageText =
                        ageField.getText().trim();

                String usn =
                        usnField.getText().trim();

                String marksText =
                        marksField.getText().trim();


                if (name.isEmpty()
                        || ageText.isEmpty()
                        || usn.isEmpty()
                        || marksText.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Please fill all fields."
                    );

                    return;
                }


                int age =
                        Integer.parseInt(ageText);


                double marks =
                        Double.parseDouble(marksText);


                if (age <= 0) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Age must be greater than 0."
                    );

                    return;
                }


                if (marks < 0 || marks > 100) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Marks must be between 0 and 100."
                    );

                    return;
                }


                String sql =
                        "INSERT INTO students " +
                        "(name, age, usn, marks) " +
                        "VALUES (?, ?, ?, ?)";


                Connection con =
                        getConnection();


                PreparedStatement ps =
                        con.prepareStatement(sql);


                ps.setString(1, name);

                ps.setInt(2, age);

                ps.setString(3, usn);

                ps.setDouble(4, marks);


                ps.executeUpdate();


                ps.close();

                con.close();


                JOptionPane.showMessageDialog(
                        frame,
                        "Student added successfully!"
                );


                clearFields(
                        nameField,
                        ageField,
                        usnField,
                        marksField
                );


                loadStudents(tableModel);


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter valid age and marks."
                );

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Database Error:\n" +
                        ex.getMessage()
                );
            }
        });


        // ==================================================
        // SEARCH STUDENT
        // ==================================================

        searchButton.addActionListener(e -> {

            String searchUsn =
                    usnField.getText().trim();


            if (searchUsn.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Enter USN to search."
                );

                return;
            }


            String sql =
                    "SELECT name, age, usn, marks " +
                    "FROM students WHERE usn = ?";


            try {

                Connection con =
                        getConnection();


                PreparedStatement ps =
                        con.prepareStatement(sql);


                ps.setString(1, searchUsn);


                ResultSet rs =
                        ps.executeQuery();


                if (rs.next()) {

                    nameField.setText(
                            rs.getString("name")
                    );


                    ageField.setText(
                            String.valueOf(
                                    rs.getInt("age")
                            )
                    );


                    usnField.setText(
                            rs.getString("usn")
                    );


                    marksField.setText(
                            String.valueOf(
                                    rs.getDouble("marks")
                            )
                    );


                    JOptionPane.showMessageDialog(
                            frame,
                            "Student Found!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Student not found!"
                    );
                }


                rs.close();

                ps.close();

                con.close();


            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Database Error:\n" +
                        ex.getMessage()
                );
            }
        });


        // ==================================================
        // UPDATE STUDENT
        // ==================================================

        updateButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText().trim();

                String ageText =
                        ageField.getText().trim();

                String usn =
                        usnField.getText().trim();

                String marksText =
                        marksField.getText().trim();


                if (name.isEmpty()
                        || ageText.isEmpty()
                        || usn.isEmpty()
                        || marksText.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Please fill all fields."
                    );

                    return;
                }


                int age =
                        Integer.parseInt(ageText);


                double marks =
                        Double.parseDouble(marksText);


                if (marks < 0 || marks > 100) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Marks must be between 0 and 100."
                    );

                    return;
                }


                String sql =
                        "UPDATE students " +
                        "SET name = ?, age = ?, marks = ? " +
                        "WHERE usn = ?";


                Connection con =
                        getConnection();


                PreparedStatement ps =
                        con.prepareStatement(sql);


                ps.setString(1, name);

                ps.setInt(2, age);

                ps.setDouble(3, marks);

                ps.setString(4, usn);


                int rows =
                        ps.executeUpdate();


                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Student updated successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Student not found!"
                    );
                }


                ps.close();

                con.close();


                loadStudents(tableModel);


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter valid age and marks."
                );

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Database Error:\n" +
                        ex.getMessage()
                );
            }
        });


        // ==================================================
        // DELETE STUDENT
        // ==================================================

        deleteButton.addActionListener(e -> {

            String deleteUsn =
                    usnField.getText().trim();


            if (deleteUsn.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Enter USN to delete."
                );

                return;
            }


            String sql =
                    "DELETE FROM students " +
                    "WHERE usn = ?";


            try {

                Connection con =
                        getConnection();


                PreparedStatement ps =
                        con.prepareStatement(sql);


                ps.setString(1, deleteUsn);


                int rows =
                        ps.executeUpdate();


                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Student deleted successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Student not found!"
                    );
                }


                ps.close();

                con.close();


                clearFields(
                        nameField,
                        ageField,
                        usnField,
                        marksField
                );


                loadStudents(tableModel);


            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Database Error:\n" +
                        ex.getMessage()
                );
            }
        });


        // ==================================================
        // CLEAR BUTTON
        // ==================================================

        clearButton.addActionListener(e -> {

            clearFields(
                    nameField,
                    ageField,
                    usnField,
                    marksField
            );
        });


        // ---------- SHOW WINDOW ----------

        frame.setVisible(true);
    }


    // ==================================================
    // LOAD STUDENTS
    // ==================================================

    private static void loadStudents(
            DefaultTableModel tableModel) {

        tableModel.setRowCount(0);


        String sql =
                "SELECT name, age, usn, marks " +
                "FROM students";


        try {

            Connection con =
                    getConnection();


            Statement stmt =
                    con.createStatement();


            ResultSet rs =
                    stmt.executeQuery(sql);


            while (rs.next()) {

                tableModel.addRow(
                        new Object[]{

                                rs.getString("name"),

                                rs.getInt("age"),

                                rs.getString("usn"),

                                rs.getDouble("marks")
                        }
                );
            }


            rs.close();

            stmt.close();

            con.close();


        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Could not load students from database.\n\n"
                    + ex.getMessage()
            );
        }
    }


    // ==================================================
    // CLEAR FIELDS
    // ==================================================

    private static void clearFields(
            JTextField nameField,
            JTextField ageField,
            JTextField usnField,
            JTextField marksField) {

        nameField.setText("");

        ageField.setText("");

        usnField.setText("");

        marksField.setText("");
    }
}