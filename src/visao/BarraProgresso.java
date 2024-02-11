package visao;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;



public class BarraProgresso extends JFrame {
    
    JProgressBar pr = new JProgressBar();
    SwingWorker w ;
    
    public static void main(String[] args) {
        new BarraProgresso();
    }
    public BarraProgresso() {
        setUndecorated(true);
        setSize(250, 20);
        setLocationRelativeTo(null);
        init();
        setVisible(true);
        w.execute();
    }
    private void init() {
        pr = new JProgressBar();
       pr.setStringPainted(true);
       pr.setValue(0);
        add(pr);
       w = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    try {
                        pr.setValue(i);
                        pr.setString(i + "%");
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                dispose();
                return 0;
            }
        };
    }
}
