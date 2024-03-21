package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter; // 导入MouseAdapter类
import java.awt.event.MouseEvent; // 导入MouseEvent类
import javax.swing.table.DefaultTableModel;

public class NewEHRSystem extends JFrame {
    private DefaultTableModel tableModel;
    private JTable casesTable;
    private int caseIdCounter = 1; // 用于跟踪CaseID的计数器

    public NewEHRSystem() {
        createUI();
    }

    private void createUI() {
        setTitle("Electronic Health Record System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centralized Patient Data
        JPanel patientDataPanel = new JPanel(new BorderLayout(10, 10)); // 增加布局间距
        patientDataPanel.setBorder(BorderFactory.createTitledBorder("Patient Cases"));

        // Column names including Case ID
        String[] columnNames = {"Case ID", "Name", "Gender", "Age", "Birth Date", "Address", "Contact", "Diagnosis", "Medical History"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the Case ID column non-editable
                return column != 0;
            }
        };
        JTable casesTable = new JTable(tableModel);
        addMouseListenerToTable(casesTable); 
        JScrollPane scrollPane = new JScrollPane(casesTable);
        patientDataPanel.add(scrollPane, BorderLayout.CENTER);
        add(patientDataPanel, BorderLayout.CENTER);

        // Insert Case Button
        JButton insertButton = new JButton("Insert Case");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionForm prescriptionForm = new PrescriptionForm(NewEHRSystem.this);
                prescriptionForm.setVisible(true);
            }
        });

        // Footer Panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 增加布局间距
        footerPanel.add(insertButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public void addNewCase(Object[] caseData) {
        // Add the case ID as the first element of the array
        Object[] rowData = new Object[caseData.length + 1];
        rowData[0] = caseIdCounter++; // Assign the next case ID
        System.arraycopy(caseData, 0, rowData, 1, caseData.length);
        tableModel.addRow(rowData);
    }

    private void addMouseListenerToTable(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 双击事件
                    int row = table.getSelectedRow();
                    if (row != -1) { // 确保选中了行
                        Object caseId = tableModel.getValueAt(row, 0); // 获取点击的Case ID
                        showCaseDetailsDialog(row); // 显示详细信息对话框
                    }
                }
            }
        });
    }

    private void showCaseDetailsDialog(int row) {
        JDialog detailsDialog = new JDialog(this, "Case Details", Dialog.ModalityType.APPLICATION_MODAL);
        
        // Use a JPanel with a GridBagLayout to control padding and spacing
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Top, left, bottom, right padding
        panel.setBackground(Color.WHITE); // Set the panel background to white
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between elements
    
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            JLabel label = new JLabel(tableModel.getColumnName(i) + ": " + tableModel.getValueAt(row, i).toString());
            label.setOpaque(true); // Make the label background visible
            label.setBackground(Color.WHITE); // Set the label background to white
            panel.add(label, gbc);
        }
    
        // Add the constructed panel to the dialog
        detailsDialog.add(panel);
        
        // Set the preferred size of the dialog
        detailsDialog.setPreferredSize(new Dimension(600, 400)); // Set your desired size
    
        detailsDialog.pack(); // Adjust dialog size
        detailsDialog.setLocationRelativeTo(this); // Center on parent window
        detailsDialog.setVisible(true); // Display the dialog
    }
}