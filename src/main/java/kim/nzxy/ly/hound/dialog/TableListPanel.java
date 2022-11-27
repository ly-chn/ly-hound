package kim.nzxy.ly.hound.dialog;

import com.intellij.database.model.DasObject;
import com.intellij.ui.components.*;
import kim.nzxy.ly.hound.ui.LyListSelectionModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.List;

/**
 * @author xuyf
 * @since 2022/11/22 17:09
 */
public class TableListPanel extends JBPanel<TableListPanel> {
    private static final Logger log = LoggerFactory.getLogger(TableListPanel.class);
    private List<? extends DasObject> tableList;

    private final JBList<? extends DasObject> checkboxList;

    private final JBLabelDecorator searchDecorator;
    private final JBTextField searchField;

    public TableListPanel(List<? extends DasObject> tableList, List<? extends DasObject> preSelectedTableList) {
        this.tableList = tableList;
        this.setPreferredSize(new Dimension(200, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 检索框
        JBPanel<?> searchArea = new JBPanel<>();
        searchArea.setLayout(new BoxLayout(searchArea, BoxLayout.X_AXIS));
        searchArea.setMaximumSize(new Dimension(200, 30));

        searchField = new JBTextField();
        searchField.setToolTipText("Search...");
        searchField.setMaximumSize(new Dimension(200, 30));
        searchArea.add(searchField);

        this.searchDecorator = JBLabelDecorator.createJBLabelDecorator(preSelectedTableList.size() + "/" + tableList.size());
        this.searchDecorator.setToolTipText("Selected/Total");
        searchArea.add(this.searchDecorator);
        this.add(searchArea);

        // 复选组
        checkboxList = new JBList<>(tableList);
        checkboxList.setSelectionModel(new LyListSelectionModel());
        for (DasObject dasObject : preSelectedTableList) {
            checkboxList.setSelectedValue(dasObject, false);
        }
        checkboxList.setCellRenderer(new TableListCheckboxRenderer(searchField));
        JBScrollPane scrollPane = new JBScrollPane(checkboxList);
        checkboxList.addListSelectionListener(e -> {
            searchDecorator.setText(checkboxList.getSelectedIndices().length + "/" + tableList.size());
        });

        searchField.addActionListener(e -> checkboxList.doLayout());


        scrollPane.setViewportView(checkboxList);
        this.add(scrollPane);
    }

    static class TableListCheckboxRenderer extends JBCheckBox implements ListCellRenderer<DasObject> {
        private final JBTextField searchField;

        public TableListCheckboxRenderer(JBTextField searchField) {
            this.searchField = searchField;
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends DasObject> list, DasObject value, int index, boolean isSelected, boolean cellHasFocus) {
            String text = StringUtils.trimToNull(searchField.getText());
            log.info("text is: {}", text);
            if (text != null && !text.contains(value.getName())) {
            }
            setVisible(false);

            setComponentOrientation(list.getComponentOrientation());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setSelected(isSelected);
            setEnabled(list.isEnabled());
            setText(value.getName());
            return null;
        }
    }
}
