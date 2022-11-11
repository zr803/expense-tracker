package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RemoveEntryGUI extends JPanel {

    public RemoveEntryGUI() {
        super();
        JPanel removeEntry = new JPanel();
        removeEntry.setOpaque(true);
        removeEntry.setBackground(Color.LIGHT_GRAY);
        removeEntry.setLayout(new BoxLayout(removeEntry, BoxLayout.Y_AXIS));
        removeEntry.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        removeEntry.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK,1),
                "Remove an entry",
                TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION));
        removeEntry.add(removeBlock());
        removeEntry.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton removeButton = new JButton("Remove Entry");
        removeButton.setBackground(Color.WHITE);
        removeEntry.add(removeButton);
        removeEntry.add(Box.createRigidArea(new Dimension(0, 8)));

        super.add(removeEntry);
    }

    private JPanel removeBlock() {
        JPanel removeLabel = new JPanel();
        removeLabel.setLayout(new BoxLayout(removeLabel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Enter entry label:");
        removeLabel.add(label);
        JTextField labelRemove = new JTextField();
        removeLabel.add(labelRemove);

        return removeLabel;
    }

}
