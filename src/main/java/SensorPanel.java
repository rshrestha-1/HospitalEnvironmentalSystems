import Feeds.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SensorPanel extends JFrame {
    EnvironmentalSystem system = new EnvironmentalSystem();
    int i;
    ArrayList<Ward> wards = new ArrayList<>();
    North north = new North(23);
    South south = new South(22);
    Central central = new Central(20);


    public SensorPanel() {
        setTitle("Sensor Readings");
        // size
        setSize(500, 500);


        // GridLayout
        JPanel content = new JPanel(new GridLayout(1, 5));
        setContentPane(content);


        // Temperature
        JLabel temperatureInfo = new JLabel();
        temperatureInfo.setVerticalAlignment(JLabel.TOP);
        for (i=1; i <= 3; i++) {
            system.out.printf ("%.1f %s %s"),
            Ward(i).getTempFeed(), Ward(i).getWardname(), Ward(i).getIdealTemp();
            String info = new StringBuilder().append("<html><h2>").append(info).append(system.getTempFeed()).append("</h2>").append(system.isHeatingOn(i)).append("<br><br></html>").toString();

            temperatureInfo.setText(info);
            content.add(temperatureInfo);
        }

        // Humidity
        JLabel humidityInfo = new JLabel();
        for (i=1; i <= 3; i++) {
            String info = new StringBuilder().append(info).append("<html><h2>").append(system.getTempFeed()).append("</h2>").append(system.isHeatingOn(i)).append("<br><br></html>").toString();

            humidityInfo.setText(info);
            content.add(humidityInfo);
        }
    }


    public void checkFeed(){
        // Alerts as per spec:
        if (TemperatureFeed.getTemperature(0) <= ((North.getIdealTemp())-0.5) ||
                TemperatureFeed.getTemperature(0) >= ((North.getIdealTemp())+0.5)) {
            System.out.println("Temperature Warning at North Ward");
        }
        // Alerts as per spec:
        if (TemperatureFeed.getTemperature(2) <= ((South.getIdealTemp())-0.5) ||
                TemperatureFeed.getTemperature(2) >= ((South.getIdealTemp())+0.5)) {
            System.out.println("Temperature Warning at South Ward");
        }
        // Alerts as per spec:
        if (TemperatureFeed.getTemperature(1) <= ((Central.getIdealTemp())-0.5) ||
                TemperatureFeed.getTemperature(1) >= ((Central.getIdealTemp())+0.5)) {
            System.out.println("Temperature Warning at Central Ward");
        }

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }
}
