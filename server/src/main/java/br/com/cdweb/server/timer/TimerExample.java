package br.com.cdweb.server.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerExample extends TimerTask {

    private Timer timer;
    private String textToDisplay;

    public TimerExample(int executionsPerSecond) {
        this.textToDisplay = "executionsPerSecond = " + executionsPerSecond;
        timer = new Timer();
            //period represents the period between each timer execution (call of run method")
        long period = (long) (1000.0 / executionsPerSecond);
        System.out.println(period);
        System.out.println(1000 / executionsPerSecond);
        timer.schedule(this, 200, period);
    }

    public TimerExample(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    public void functionToRepeat() {
        System.out.println(textToDisplay);
    }

    public void run() {
        functionToRepeat();
    }

    public static void main(String args[]) {
        Timer timer = new Timer("TEST", true);
        timer.schedule(new TimerExample("Test execution after 2s"), 2000);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}