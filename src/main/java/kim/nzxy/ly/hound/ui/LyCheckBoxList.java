package kim.nzxy.ly.hound.ui;

import com.intellij.openapi.module.ResourceFileUtil;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.speedSearch.SpeedSearch;
import com.intellij.util.Function;
import com.intellij.util.ResourceUtil;
import com.intellij.util.io.URLUtil;
import kim.nzxy.ly.hound.util.ImgUtil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xy
 */
public class LyCheckBoxList<T> extends JBPanel<LyCheckBoxList<T>> {
    private static final Logger log = LoggerFactory.getLogger(LyCheckBoxList.class);
    private final Map<JBCheckBox, T> map = new LinkedHashMap<>();

    public LyCheckBoxList(List<? extends T> items, List<? extends T> selectedItems, Function<? super T, String> namer) {
        this(items, selectedItems, namer, true);
    }


    public LyCheckBoxList(List<? extends T> items, List<? extends T> selectedItems, Function<? super T, String> namer, boolean searchAble) {
        this.setLayout(new BorderLayout());

        JBPanel<?> panel = new JBPanel<>();
        panel.setLayout(new GridLayout(0, 2));


        JBScrollPane scrollPane = new JBScrollPane();
        scrollPane.setViewportView(panel);
        Icon icon = ImgUtil.getIcon();
        for (T item : items) {
            JBCheckBox checkBox = new JBCheckBox(namer.fun(item));
            JBLabel label = new JBLabel(icon);
            label.setLabelFor(checkBox);
            if (selectedItems.contains(item)) {
                checkBox.setSelected(true);
            }
            map.put(checkBox, item);
            panel.add(label);
            panel.add(checkBox);
        }
        this.add(scrollPane, BorderLayout.CENTER);

        if (searchAble) {
            SearchTextField searchTextField = new SearchTextField(false);
            this.add(searchTextField, BorderLayout.NORTH);
            SpeedSearch speedSearch = new SpeedSearch();
            searchTextField.addDocumentListener(new DocumentAdapter() {
                @Override
                protected void textChanged(@NotNull DocumentEvent e) {
                    log.info("text change: {}", searchTextField.getText());
                    speedSearch.updatePattern(searchTextField.getText());
                }
            });
            speedSearch.addChangeListener(evt -> {
                log.info("search change");
                map.keySet().forEach(it -> {
                    log.info("speedSearch.isPopupActive: {}", speedSearch.isPopupActive());
                    it.setVisible(!speedSearch.isPopupActive() && speedSearch.shouldBeShowing(it.getText()));
                });
            });
        }
    }

    public List<? extends T> getSelectedListItem() {
        return map.entrySet().stream()
                .filter(it -> it.getKey().isSelected())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private void initSearch() {

    }
}
