package kim.nzxy.ly.hound.dialog;

import com.intellij.application.options.codeStyle.arrangement.ArrangementConstants;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.MultiRowFlowPanel;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * 生成器代码注释
 *
 * @author xuyf
 * @since 2022/11/22 16:56
 */
public class LyHoundGeneratorDialog extends DialogWrapper {
    public LyHoundGeneratorDialog() {
        super(true);
        setTitle("LyHound 代码生成");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new GridLayout(1, 2));

        dialogPanel.add(new MultiRowFlowPanel(FlowLayout.LEFT, ArrangementConstants.HORIZONTAL_GAP, ArrangementConstants.VERTICAL_GAP));

        JLabel label = new JLabel("testing");
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}
