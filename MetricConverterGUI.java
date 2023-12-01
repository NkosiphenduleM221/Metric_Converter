// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MetricConverterGUI extends JFrame {
    private JTextField quantityField;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JLabel resultLabel;

    private Map<String, Map<String, Double>> conversionMap;

    public MetricConverterGUI() {
        setTitle("Metric Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setBackground(new Color(173, 216, 230));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        ImageIcon imageIcon = resizeImage("E.png", 400, 400);
        JLabel imageLabel = new JLabel(imageIcon);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(imageLabel, constraints);
        constraints.gridwidth = 1;

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setForeground(Color.black);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(quantityLabel, constraints);

        quantityField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(quantityField, constraints);

        String[] conversions = { "Feet", "Meters", "Pounds", "Kgs", "Fahrenheit", "Celsius" };

        fromComboBox = new JComboBox<>(conversions);
        fromComboBox.setBackground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(fromComboBox, constraints);

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.black);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(toLabel, constraints);

        toComboBox = new JComboBox<>(conversions);
        toComboBox.setBackground(Color.WHITE);
        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(toComboBox, constraints);

        JButton convertButton = new JButton("Convert");
        convertButton.setBackground(Color.green);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(convertButton, constraints);

        resultLabel = new JLabel();
        resultLabel.setForeground(Color.black);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        panel.add(resultLabel, constraints);
        constraints.gridwidth = 1;

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        initializeConversionMap();

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private void initializeConversionMap() {
        conversionMap = new HashMap<>();

        // Feet to Meters
        Map<String, Double> feetToMeters = new HashMap<>();
        feetToMeters.put("Meters", 0.3048);
        conversionMap.put("Feet", feetToMeters);

        // Meters to Feet
        Map<String, Double> metersToFeet = new HashMap<>();
        metersToFeet.put("Feet", 3.28084);
        conversionMap.put("Meters", metersToFeet);

        // Pounds to Kgs
        Map<String, Double> poundsToKgs = new HashMap<>();
        poundsToKgs.put("Kgs", 0.453592);
        conversionMap.put("Pounds", poundsToKgs);

        // Kgs to Pounds
        Map<String, Double> kgsToPounds = new HashMap<>();
        kgsToPounds.put("Pounds", 2.20462);
        conversionMap.put("Kgs", kgsToPounds);

        // Fahrenheit to Celsius
        Map<String, Double> fahrenheitToCelsius = new HashMap<>();
        fahrenheitToCelsius.put("Celsius", -32 * 5 / 9.0);
        conversionMap.put("Fahrenheit", fahrenheitToCelsius);

        // Celsius to Fahrenheit
        Map<String, Double> celsiusToFahrenheit = new HashMap<>();
        celsiusToFahrenheit.put("Fahrenheit", 9 / 5.0);
        conversionMap.put("Celsius", celsiusToFahrenheit);
    }

    private void convert() {
        try {
            double inputValue = Double.parseDouble(quantityField.getText());
            String fromUnit = (String) fromComboBox.getSelectedItem();
            String toUnit = (String) toComboBox.getSelectedItem();

            if (conversionMap.containsKey(fromUnit) && conversionMap.get(fromUnit).containsKey(toUnit)) {
                double conversionFactor = conversionMap.get(fromUnit).get(toUnit);
                double result = inputValue * conversionFactor;
                resultLabel.setText("Result: " + result);
            } else {
                resultLabel.setText("Conversion not supported");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MetricConverterGUI());
    }
}
