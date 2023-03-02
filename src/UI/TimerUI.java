package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TimerUI extends JFrame {
  private JTextField timeField;
  private Timer timer;
  private JTextField minuteField;
  private JTextField secondField;
  private int counter;

  public TimerUI() {
    setTitle("Timer");
    setSize(400, 100);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    timeField = new JTextField(5);
    timeField.setHorizontalAlignment(SwingConstants.CENTER);
    timeField.setEditable(false);
    panel.add(timeField, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);

    JButton startButton = new JButton("Start");
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          int minutes = Integer.parseInt(minuteField.getText());
          int seconds = Integer.parseInt(secondField.getText());
          counter = (minutes * 60) + seconds;
          start();
        } catch (NumberFormatException ex) {
          timeField.setText("Invalid input");
        }
      }
    });
    JButton stopButton = new JButton("Stop");
    stopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.stop();
      }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    add(buttonPanel, BorderLayout.SOUTH);

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new FlowLayout());
    inputPanel.add(new JLabel("Minutes: "));
    minuteField = new JTextField(3);
    inputPanel.add(minuteField);
    inputPanel.add(new JLabel("Seconds: "));
    secondField = new JTextField(3);
    inputPanel.add(secondField);
    add(inputPanel, BorderLayout.NORTH);

    timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int minutes = counter / 60;
        int seconds = counter % 60;
        timeField.setText(String.format("%02d:%02d", minutes, seconds));
        counter--;
        if (counter == 0) {
            timer.stop();
          }
        }
      });
    }

    public void start() {
      timer.start();
    }

    public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          TimerUI frame = new TimerUI();
          frame.setVisible(true);
        }
      });
    }
  }



