package konversibilangannn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class CalculatorTubes extends JFrame {
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
    private JButton readFileButton;
    private JButton writeFileButton;
    private JLabel statusLabel;

    public CalculatorTubes() {
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

        writeFileButton = new JButton("SAVE KE FILE");
        writeFileButton.addActionListener(this::writeFileButtonActionPerformed);

        readFileButton = new JButton("BACA DARI FILE");
        readFileButton.addActionListener(this::readFileButtonActionPerformed);

        statusLabel = new JLabel("Status: ");

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
                                                .addGap(242, 242, 242)))
                                .addContainerGap()
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(writeFileButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(readFileButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel)
                                .addContainerGap()
                        )
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
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(readFileButton)
                                        .addComponent(writeFileButton)
                                        .addComponent(statusLabel)
                                )
                                .addContainerGap()
                        )
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

    private void readFileButtonActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih File untuk Dibaca");

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                String content = reader.readLine();
                inputField.setText(content);


                updateComboBoxes(content);


                convertAndDisplay();


                statusLabel.setText("Status: File berhasil di baca");
            } catch (IOException ex) {

                statusLabel.setText("Status: Gagal membaca file");
                JOptionPane.showMessageDialog(null, "Gagal membaca file: " + ex.getMessage());
            }
        } else {

            statusLabel.setText("Status: Pembacaan file dibatalkan atau tidak ada file yang dipilih");
        }
    }

    private void writeFileButtonActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Lokasi untuk Menyimpan File");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                // Simpan input dan output dalam format yang sesuai
                String returnToFile = fromComboBox.getSelectedItem() + " : " + inputField.getText() + System.lineSeparator() +
                        toComboBox.getSelectedItem() + " : " + outputField.getText();
//                String content = toComboBox.getSelectedItem() + inputField.getText() + System.lineSeparator() +
//                        outputField.getText();

                writer.write(returnToFile);
                JOptionPane.showMessageDialog(null, "File berhasil disimpan");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }

    private void updateComboBoxes(String content) {

        String numberType = determineNumberType(content);

        switch (numberType) {
            case "Hexadecimal":
                fromComboBox.setSelectedItem("Hexadecimal");
                break;
            case "Decimal":
                fromComboBox.setSelectedItem("Decimal");
                break;
            case "Biner":
                fromComboBox.setSelectedItem("Biner");
                break;
            case "Oktal":
                fromComboBox.setSelectedItem("Oktal");
                break;
            default:
                // Handle jika tidak dapat menentukan jenis bilangan
                break;
        }

        // Set toComboBox sesuai kebutuhan
        toComboBox.setSelectedItem("Decimal");
    }

    private String determineNumberType(String content) {
        // Menggunakan ekspresi reguler untuk menentukan jenis bilangan dari input
        // Misalnya, jika input mengandung karakter 'A-F', maka itu mungkin merupakan bilangan heksadesimal
        if (content.matches("[0-9A-Fa-f]+")) {
            if (content.matches("[01]+")) {
                return "Biner";
            } else if (content.matches("[0-7]+")) {
                return "Oktal";
            } else if (content.matches("[0-9A-Fa-f]+")) {
                return "Hexadecimal";
            } else if (content.matches("[0-9]+")) {
                return "Decimal";
            }
        }

        
        return "TIDAK VALID";
    }



    private void convertButtonActionPerformed(ActionEvent evt) {
        try {
            convertAndDisplay();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void updateInputLabel(String from) {
        switch (from) {
            case "Hexadecimal":
                inputLabel.setText("INPUT HEXADECIMAL : "); // 1-9, A-F, a-f
                break;
            case "Biner":
                inputLabel.setText("INPUT BINER : "); // (0,1)
                break;
            case "Decimal":
                inputLabel.setText("INPUT DECIMAL : "); // (0-999)
                break;
            case "Oktal":
                inputLabel.setText("INPUT OKTAL : "); // (0-7)
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

        
        String result = convert(from, to, inputValue);

        
        outputField.setText(result);
    }

    private String convert(String from, String to, String inputValue) {
        String result = "";
        try {
            switch (from) {
                case "Hexadecimal":
                    if (inputValue.matches("[0-9A-Fa-f]+") && inputValue.length() <= 8) {
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
                            case "Hexadecimal":
                                result = inputValue;  // Langsung mengambil nilai input
                                break;
                        }
                    } else {
                        result = "Error: Input tidak valid, tidak boleh lebih dari 8 karakter";
                    }
                    break;
                case "Decimal":
                    if (inputValue.matches("[0-9]+") && inputValue.length() <= 8 && Integer.parseInt(inputValue) <= 99999999) {
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
                            case "Decimal":
                                result = inputValue;  
                                break;
                        }
                    } else {
                        result = "Error: Input tidak valid, tidak boleh lebih dari 8 karakter";
                    }
                    break;
                case "Biner":
                    if (inputValue.matches("[01]+") && inputValue.length() <= 8) {
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
                            case "Biner":
                                result = inputValue;  
                                break;
                        }
                    } else {
                        result = "Error: Input tidak valid, tidak boleh lebih dari 8 karakter";
                    }
                    break;
                case "Oktal":
                    if (inputValue.matches("[0-7]+") && inputValue.length() <= 8) {
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
                            case "Oktal":
                                result = inputValue;  
                                break;
                        }
                    } else {
                        result = "Error: Input tidak valid, tidak boleh lebih 8 karakter";
                    }
                    break;
            }
        } catch (NumberFormatException ex) {
            result = "Error: Input tidak valid";
        }

        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorTubes();
        });
    }
}
