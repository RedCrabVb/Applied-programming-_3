package test;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

public class TextFormantNumber {
    public static TextFormatter getTextFormatNumber() {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            } else if ("-".equals(change.getText())) {
                if (change.getControlText().startsWith("-")) {
                    change.setText("");
                    change.setRange(0, 1);
                    change.setCaretPosition(change.getCaretPosition() - 2);
                    change.setAnchor(change.getAnchor() - 2);
                } else {
                    change.setRange(0, 0);
                }
                return change;
            }
            return null;
        };

        StringConverter<Integer> converter = new IntegerStringConverter() {
            @Override
            public Integer fromString(String s) {
                if (s.isEmpty()) return 0;
                return super.fromString(s);
            }
        };

        TextFormatter<Integer> textFormatter =
                new TextFormatter<Integer>(converter, 0, integerFilter);
        return textFormatter;
    }
}
