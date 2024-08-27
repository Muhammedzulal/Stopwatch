import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame("Stopwatch");
    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    JPanel pnl = new JPanel();
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_String = String.format("%02d", seconds);// önüne sıfır gelecek şekilde 2 karakterli yapma
    String minutes_String = String.format("%02d", minutes);
    String hours_String = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {// 1sn gecikmeli timer
        public void actionPerformed(ActionEvent e) {// her 1sn de olacaklar
            elapsedTime += 1000; // 1 saniye artır -> 1000ms
            hours = (elapsedTime / 3600000);// 3.600.000 milisaniyede bir saat artsın
            minutes = (elapsedTime / 60000) % 60;// 60.000 milisaniyede bir dakika artsın ama 60 olmaması içi %
            seconds = (elapsedTime / 1000) % 60;// 1.000 milisaniyede bir saniyede artsın ama 60 olmaması içi %
            seconds_String = String.format("%02d", seconds);// string formatını güncelleme
            minutes_String = String.format("%02d", minutes);
            hours_String = String.format("%02d", hours);
            timeLabel.setText(hours_String + ":" + minutes_String + ":" + seconds_String);
        }
    });

    Stopwatch() {// frame ve dizayn
        timeLabel.setText(hours_String + ":" + minutes_String + ":" + seconds_String);// labele zamanı ekleme
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setBounds(50, 30, 200, 50);

        // Start
        startButton.setBounds(50, 82, 100, 30);
        startButton.setFocusable(false);
        startButton.setBackground(new Color(160, 160, 160));
        startButton.setFont(new Font("Verdana", Font.PLAIN, 22));
        startButton.addActionListener(this);// implementsi dahil etme
        // Reset
        resetButton.setBounds(150, 82, 100, 30);
        resetButton.setFocusable(false);
        resetButton.setBackground(new Color(160, 160, 160));
        resetButton.setFont(new Font("Verdana", Font.PLAIN, 22));
        resetButton.addActionListener(this);// implementsi dahil etme
        // Panel
        pnl.setBackground(new Color(64, 64, 64));
        pnl.add(timeLabel);
        pnl.setLayout(null);
        pnl.add(startButton);
        pnl.add(resetButton);

        frame.add(pnl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(320, 200);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {// akitflik durumu kontrolü
        if (e.getSource() == startButton) {// aktiflik start butonundaysa
            start();
            if (started == false) {
                started = true;
                startButton.setText("Stop");
                start();
            } else {
                started = false;
                startButton.setText("Start");
                stop();
            }
        }
        if (e.getSource() == resetButton) {// aktiflik reset butonundaysa
            started = false;
            startButton.setText("Start");
            reset();
        }

    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {// değerleri sıfırlama
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_String = String.format("%02d", seconds);
        minutes_String = String.format("%02d", minutes);
        hours_String = String.format("%02d", hours);
        timeLabel.setText(hours_String + ":" + minutes_String + ":" + seconds_String);
    }

}