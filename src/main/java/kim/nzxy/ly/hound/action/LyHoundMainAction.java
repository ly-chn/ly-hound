package kim.nzxy.ly.hound.action;

import com.intellij.database.model.DasNamed;
import com.intellij.database.model.DasObject;
import com.intellij.database.model.ObjectKind;
import com.intellij.database.psi.DbNamespaceImpl;
import com.intellij.database.psi.DbTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.JBIterable;
import kim.nzxy.ly.hound.dialog.LyHoundGeneratorDialog;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> selectedTableNameList = Arrays.stream(tableElements).map(t -> (DbTable) t).map(DasNamed::getName).collect(Collectors.toList());

        List<? extends DasObject> tableList = ((DbNamespaceImpl) tableElements[0].getParent()).getDelegate().getDasChildren(ObjectKind.TABLE).toList();
        List<? extends DasObject> selectedTableList = tableList.stream().filter(it -> selectedTableNameList.contains(it.getName())).collect(Collectors.toList());
        LyHoundGeneratorDialog dialog = new LyHoundGeneratorDialog(tableList, selectedTableList);
        boolean b = dialog.showAndGet();
    }
}
