package kim.nzxy.ly.hound.dialog;

import com.intellij.database.model.DasObject;
import com.intellij.ui.components.*;
import kim.nzxy.ly.hound.ui.LyListSelectionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

/**
 * @author xuyf
 * @since 2022/11/22 17:09
 */
public class TableListPanel extends JBPanel<TableListPanel> {
    private static final Logger log = LoggerFactory.getLogger(TableListPanel.class);
    private List<? extends DasObject> tableList;

    private JBLabelDecorator searchDecorator;

    public TableListPanel(List<? extends DasObject> tableList, List<? extends DasObject> preSelectedTableList) {
        this.tableList = tableList;
        this.setPreferredSize(new Dimension(200, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 检索框
        JBPanel<?> searchArea = new JBPanel<>();
        searchArea.setLayout(new BoxLayout(searchArea, BoxLayout.X_AXIS));
        searchArea.setMaximumSize(new Dimension(200, 30));
        JBTextField searchField = new JBTextField();
        searchField.setToolTipText("Search...");
        searchField.setMaximumSize(new Dimension(200, 30));
        searchArea.add(searchField);
        this.searchDecorator = JBLabelDecorator.createJBLabelDecorator();
        searchArea.add(this.searchDecorator);
        this.add(searchArea);
        // 复选组
        JBList<? extends DasObject> list = new JBList<>(tableList);
        list.setSelectionModel(new LyListSelectionModel());
        list.addListSelectionListener(e -> {
            System.out.println("e.getValueIsAdjusting() = " + e.getValueIsAdjusting());
        });

        list.setCellRenderer(new TableListCheckboxRenderer());
        JBScrollPane scrollPane = new JBScrollPane(list);

        scrollPane.setViewportView(list);
        this.add(scrollPane);
    }

    static class TableListCheckboxRenderer extends JBCheckBox implements ListCellRenderer<DasObject> {
        @Override
        public Component getListCellRendererComponent(JList<? extends DasObject> list, DasObject value, int index, boolean isSelected, boolean cellHasFocus) {
            log.info("render list name: {}, selected: {}", value.getName(), isSelected);
            setComponentOrientation(list.getComponentOrientation());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setSelected(isSelected);
            setEnabled(list.isEnabled());
            setText(value.getName());
            return this;
        }
    }
}
