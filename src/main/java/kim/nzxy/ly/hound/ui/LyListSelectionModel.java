package kim.nzxy.ly.hound.ui;

import javax.swing.*;

/**
 * swing list多选时需要按住Ctrl问题
 * usage
 *
 * @author xy
 */
public class LyListSelectionModel extends DefaultListSelectionModel {

    @Override
    public void setSelectionInterval(int index0, int index1) {
        //  Select multiple lines
        if (index0 != index1) {
            super.addSelectionInterval(index0, index1);
            return;
        }

        //  Toggle selection of a single line
        if (super.isSelectedIndex(index0)) {
            super.removeSelectionInterval(index0, index0);
        } else {
            super.addSelectionInterval(index0, index0);
        }
    }
}
