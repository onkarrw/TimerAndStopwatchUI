package UI;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Stopwatch extends JFrame implements ActionListener, Runnable {

  private static final long serialVersionUID = 1L;
  private JLabel timeLabel;
  private JButton startButton;
  private JButton stopButton;
  private JButton pauseButton;
  private boolean running;
  private boolean paused;
  private long startTime;
  private Thread thread;

  public Stopwatch() {
    super("Stopwatch");
    setLayout(new FlowLayout());

    timeLabel = new JLabel("00:00:00:000");
    add(timeLabel);

    startButton = new JButton("Start");
    add(startButton);
    startButton.addActionListener(this);

    stopButton = new JButton("Stop");
    add(stopButton);
    stopButton.addActionListener(this);

    pauseButton = new JButton("Pause/Resume");
    add(pauseButton);
    pauseButton.addActionListener(this);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 100);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == startButton) {
      start();
    } else if (event.getSource() == stopButton) {
      stop();
    } else if (event.getSource() == pauseButton) {
      pause();
    }
  }

  private void start() {
    startTime = System.currentTimeMillis();
    running = true;
    thread = new Thread(this);
    thread.start();
  }

  private void stop() {
    running = false;
    timeLabel.setText("00:00:00:000");
  }

  private void pause() {
    paused = !paused;
  }

  @Override
  public void run() {
    while (running) {
      if (!paused) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long seconds = (elapsedTime / 1000) % 60;
        long millis = elapsedTime % 1000;

        String time = String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, millis);
        timeLabel.setText(time);
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Stopwatch());
  }
}
