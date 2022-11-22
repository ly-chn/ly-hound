package kim.nzxy.ly.hound.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import kim.nzxy.ly.hound.dialog.LyHoundGeneratorDialog;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 开关主入口
 *
 * @author xuyf
 * @since 2022/11/17 13:56
 */
public class LyHoundMainAction extends AnAction {

    private static final Logger log = LoggerFactory.getLogger(LyHoundMainAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        PsiElement[] tableElements = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY);
        Module[] data = e.getData(LangDataKeys.MODULE_CONTEXT_ARRAY);
        if (tableElements == null) {
            log.error("未选择表, 无法生成代码");
            return;
        }
        tableElements[0].getParent();
        LyHoundGeneratorDialog dialog = new LyHoundGeneratorDialog();
        boolean b = dialog.showAndGet();
    }
}
