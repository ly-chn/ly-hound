package kim.nzxy.ly.hound.dialog;

import com.intellij.database.model.DasNamed;
import com.intellij.database.model.DasObject;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBPanel;
import kim.nzxy.ly.hound.ui.LyCheckBoxList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author xuyf
 * @since 2022/11/22 17:09
 */
public class TableListPanel extends JBPanel<TableListPanel> {
    private static final Logger log = LoggerFactory.getLogger(TableListPanel.class);

    //    private final JBList<? extends DasObject> checkboxList;
    private final LyCheckBoxList<DasObject> checkBoxList;

    public TableListPanel(List<? extends DasObject> tableList, List<? extends DasObject> preSelectedTableList) {
        this.setPreferredSize(new Dimension(200, 500));
        this.setLayout(new BorderLayout());
        checkBoxList = new LyCheckBoxList<>(tableList, preSelectedTableList, DasNamed::getName);
        this.add(checkBoxList, BorderLayout.CENTER);
    }
}
