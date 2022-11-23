package kim.nzxy.ly.hound.dialog;

import com.intellij.database.model.DasObject;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.border.IdeaTitledBorder;
import com.intellij.ui.components.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author xuyf
 * @since 2022/11/22 17:09
 */
public class TableListPanel extends JBPanel<TableListPanel> {
    private static final Logger log = LoggerFactory.getLogger(TableListPanel.class);
    private List<? extends DasObject> tableList;

    public TableListPanel(List<? extends DasObject> tableList, List<? extends DasObject> preSelectedTableList) {
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
        new JBLabel("Search:").setLabelFor(searchField);
        this.add(searchArea);


        // 复选组
        JBScrollPane scrollPane = new JBScrollPane();

        JBPanel<?> panel = new JBPanel<>();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (DasObject dasObject : tableList) {
            JBCheckBox checkBox = new JBCheckBox(dasObject.getName());
            if (preSelectedTableList.contains(dasObject)) {
                checkBox.setSelected(true);
            }
            panel.add(checkBox);
        }
        scrollPane.setViewportView(panel);
        this.add(scrollPane);
        // this.tableList = tableList;
        // log.info("init table list: {}", tableList);
        // JBPanel<?> panel = new JBPanel<>();
        // panel.withMaximumWidth(600);
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // for (DasObject dasObject : tableList) {
        //     JBCheckBox checkBox = new JBCheckBox(dasObject.getName());
        //     if (preSelectedTableList.contains(dasObject)) {
        //         checkBox.setSelected(true);
        //     }
        //     panel.add(checkBox);
        // }
        // this.setViewportView(panel);
    }
}
