package kim.nzxy.ly.hound.dialog;

import com.intellij.database.model.DasObject;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 生成器代码注释
 *
 * @author xuyf
 * @since 2022/11/22 16:56
 */
public class LyHoundGeneratorDialog extends DialogWrapper {

    private static final Logger log = LoggerFactory.getLogger(LyHoundGeneratorDialog.class);
    /**
     * 预选中的表
     */
    private final List<? extends DasObject> preSelectedTableList;

    /**
     * 所有表
     */
    private final List<? extends DasObject> tableList;


    public LyHoundGeneratorDialog(List<? extends DasObject> tableList, List<? extends DasObject> preSelectedTableList) {
        super(true);
        this.tableList = tableList;
        this.preSelectedTableList = preSelectedTableList;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth()/2;
        int screenHeight = (int) screenSize.getHeight()/2;
        log.info("screenWidth: {}, screenHeight: {}", screenWidth, screenHeight);
        setSize(screenWidth, screenHeight);
        setTitle("LyHound 代码生成");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JBPanel<?> dialogPanel = new JBPanel<>(new BorderLayout());
        dialogPanel.add(new TableListPanel(this.tableList, this.preSelectedTableList), BorderLayout.WEST);

        JBLabel label = new JBLabel("Testing");
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}
