package konversibilangannn;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FormKonversiBilangan extends JFrame {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JLabel jLabel5;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JTextField inputField;
    private JTextField outputField;
    private JButton convertButton;

    public FormKonversiBilangan() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1 = new JLabel("Pilihan");
        jLabel2 = new JLabel("Dari:");
        inputLabel = new JLabel("INPUT");
        outputLabel = new JLabel("OUTPUT");
        jLabel5 = new JLabel("Ke:");

        fromComboBox = new JComboBox<>(new String[]{"Hexadecimal", "Decimal", "Biner", "Oktal"});
        fromComboBox.addActionListener(this::fromComboBoxActionPerformed);

        toComboBox = new JComboBox<>(new String[]{"Hexadecimal", "Decimal", "Biner", "Oktal"});
        toComboBox.addActionListener(this::toComboBoxActionPerformed);

        inputField = new JTextField();
        outputField = new JTextField();

        convertButton = new JButton("KONVERSI");
        convertButton.addActionListener(this::convertButtonActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(fromComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39)
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(toComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(outputLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(outputField, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(inputLabel)
                                                        .addGap(77, 77, 77)
                                                        .addComponent(inputField, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(66, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(convertButton)
                                                .addGap(218, 218, 218))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(242, 242, 242))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5)
                                        .addComponent(fromComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputLabel)
                                        .addComponent(inputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(outputLabel)
                                        .addComponent(outputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addComponent(convertButton)
                                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
    }

    private void fromComboBoxActionPerformed(ActionEvent evt) {
        String from = (String) fromComboBox.getSelectedItem();
        updateInputLabel(from);
    }

    private void toComboBoxActionPerformed(ActionEvent evt) {
        String to = (String) toComboBox.getSelectedItem();
        updateOutputLabel(to);
    }

    private void outputFieldActionPerformed(ActionEvent evt) {
        // Add your handling code here
    }

    private void convertButtonActionPerformed(ActionEvent evt) {
        try {
            convertAndDisplay();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input Tidak Sesuai Spesifikasi");
        }
    }

    private void updateInputLabel(String from) {
        switch (from) {
            case "Hexadecimal":
                inputLabel.setText("INPUT HEXADECIMAL : ");

                break;
            case "Biner":
                inputLabel.setText("INPUT BINER : ");
                break;
            case "Decimal":
                inputLabel.setText("INPUT DECIMAL : ");
                break;
            case "Oktal":
                inputLabel.setText("INPUT OKTAL : ");
                break;
        }
    }

    private void updateOutputLabel(String to) {
        switch (to) {
            case "Hexadecimal":
                outputLabel.setText("OUTPUT HEXADECIMAL : ");
                break;
            case "Biner":
                outputLabel.setText("OUTPUT BINER : ");
                break;
            case "Decimal":
                outputLabel.setText("OUTPUT DECIMAL : ");
                break;
            case "Oktal":
                outputLabel.setText("OUTPUT OKTAL : ");
                break;
        }
    }

    private void convertAndDisplay() {
        String from = (String) fromComboBox.getSelectedItem();
        String to = (String) toComboBox.getSelectedItem();
        String inputValue = inputField.getText();

        // Implement your conversion logic here
        String result = convert(from, to, inputValue);

        // Display the result in outputField
        outputField.setText(result);
    }

    private String convert(String from, String to, String inputValue) {
        String result = "";
        try {
            switch (from) {
                case "Hexadecimal":
                    switch (to) {
                        case "Decimal":
                            result = String.valueOf(Integer.parseInt(inputValue, 16));
                            break;
                        case "Biner":
                            result = Integer.toBinaryString(Integer.parseInt(inputValue, 16));
                            break;
                        case "Oktal":
                            result = Integer.toOctalString(Integer.parseInt(inputValue, 16));
                            break;
                    }
                    break;
                case "Decimal":
                    switch (to) {
                        case "Hexadecimal":
                            result = Integer.toHexString(Integer.parseInt(inputValue));
                            break;
                        case "Biner":
                            result = Integer.toBinaryString(Integer.parseInt(inputValue));
                            break;
                        case "Oktal":
                            result = Integer.toOctalString(Integer.parseInt(inputValue));
                            break;
                    }
                    break;
                case "Biner":
                    switch (to) {
                        case "Hexadecimal":
                            result = Integer.toHexString(Integer.parseInt(inputValue, 2));
                            break;
                        case "Decimal":
                            result = String.valueOf(Integer.parseInt(inputValue, 2));
                            break;
                        case "Oktal":
                            result = Integer.toOctalString(Integer.parseInt(inputValue, 2));
                            break;
                    }
                    break;
                case "Oktal":
                    switch (to) {
                        case "Decimal":
                            result = String.valueOf(Integer.parseInt(inputValue, 8));
                            break;
                        case "Biner":
                            result = Integer.toBinaryString(Integer.parseInt(inputValue, 8));
                            break;
                        case "Hexadecimal":
                            result = Integer.toHexString(Integer.parseInt(inputValue, 8));
                            break;
                    }
                    break;
            }
        } catch (NumberFormatException ex) {
            result = "Error: Input tidak valid";
            // Jika membutuhkan informasi tambahan tentang kesalahan:
            // result = "Error: " + ex.getMessage();
        } finally {
            // Lakukan tindakan penutupan atau pelepasan sumber daya di sini jika diperlukan
        }
        return result;
    }

    public static void main(String[] args) {
        new FormKonversiBilangan();
    }
}
