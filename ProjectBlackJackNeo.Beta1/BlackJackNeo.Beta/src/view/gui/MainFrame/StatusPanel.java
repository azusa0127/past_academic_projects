package view.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eiphi_000 on 2014-11-29.
 */
public class StatusPanel extends JPanel {
    private JList<String> list;
    private JScrollPane scroll;
    private DefaultListModel<String> msgHistory;

    public StatusPanel() {
        setLayout(new BorderLayout());
        setSize(580, 130);
        setPreferredSize(new Dimension(580, 130));
        setBorder(BorderFactory.createTitledBorder("Status"));
        msgHistory = new DefaultListModel<String>();
        list = new JList<String>(msgHistory);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroll = new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scroll,BorderLayout.CENTER);
    }

    public void updateText(String msg){
        msgHistory.addElement(msg);
        while (msgHistory.getSize() > 50){
            msgHistory.remove(0);}

        final int lastIndex = msgHistory.getSize() - 1;
        if (lastIndex >=0){
            list.ensureIndexIsVisible(lastIndex);
        }

        this.updateUI();
    }
}