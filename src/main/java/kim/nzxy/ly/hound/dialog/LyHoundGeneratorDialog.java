package kim.nzxy.ly.hound.dialog;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Liaoliao
 */
@Slf4j
public class LyHoundGeneratorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public LyHoundGeneratorDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e->onOK());

        buttonCancel.addActionListener(e->onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        log.info("user click ok");
        // add your code here
        dispose();
    }

    private void onCancel() {
        log.info("user click cancel");
        // add your code here if necessary
        dispose();
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public static void main(String[] args) {
        LyHoundGeneratorDialog dialog = new LyHoundGeneratorDialog();
        dialog.pack();
        dialog.setVisible(true);
        // System.exit(0);
    }
}
