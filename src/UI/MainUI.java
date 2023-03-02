package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {
  private JPanel timerPanel;
  private JPanel stopwatchPanel;
  private JButton timerButton;
  private JButton stopwatchButton;

  public MainUI() {

    //change the background color

    setTitle("Timer and Stopwatch");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(2, 1));
    
    
    timerPanel = new JPanel();
    stopwatchPanel = new JPanel();
    timerButton = new JButton("Timer");
    timerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        TimerUI timerUI = new TimerUI();
        timerUI.setVisible(true);
      }
    });
    timerPanel.add(timerButton);
    

    stopwatchButton = new JButton("Stopwatch");
    stopwatchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.setVisible(true);
        
      }
      
    });
    stopwatchPanel.add(stopwatchButton);

    add(timerPanel);
    add(stopwatchPanel);
  }

  public static void main(String[] args) {
    MainUI mainUI = new MainUI();
    mainUI.setVisible(true);
  }
}
