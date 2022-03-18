import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;

public class UI extends JFrame {
    private JPanel contentPane;
    private JTextField queuesNumber;
    private JTextField simulationTime;
    private JTextField minArrivalTime;
    private JTextField maxArrivalTime;
    private JTextField minServiceTime;
    private JTextField maxServiceTime;
    private JTextField clientsNumber;
    private JButton startSimulationButton;

    public UI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 330);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 192, 203));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("Queue Simulator");

        JLabel title = new JLabel("Queue Simulator");
        title.setBackground(new Color(255, 218, 185));
        title.setBounds(115, 10, 185, 30);
        title.setFont(new Font("Times New Roman", Font.BOLD, 25));
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(title);

        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setBackground(new Color(255, 222, 173));
        startSimulationButton.setBounds(150, 255, 130, 30);
        contentPane.add(startSimulationButton);

        JLabel noOfClientsLabel = new JLabel("Number of Clients:");
        noOfClientsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        noOfClientsLabel.setBounds(100, 50, 125, 20);
        contentPane.add(noOfClientsLabel);

        queuesNumber = new JTextField();
        queuesNumber.setBounds(230, 80, 70, 20);
        contentPane.add(queuesNumber);
        queuesNumber.setColumns(10);

        JLabel numberOfQueuesLabel = new JLabel("Number of Queues:");
        numberOfQueuesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numberOfQueuesLabel.setBounds(100, 80, 125, 20);
        contentPane.add(numberOfQueuesLabel);

        simulationTime = new JTextField();
        simulationTime.setColumns(10);
        simulationTime.setBounds(230, 110, 70, 20);
        contentPane.add(simulationTime);

        JLabel timeForSimulationLabel = new JLabel("Time for Simulation:");
        timeForSimulationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timeForSimulationLabel.setBounds(100, 110, 125, 20);
        contentPane.add(timeForSimulationLabel);

        minArrivalTime = new JTextField();
        minArrivalTime.setColumns(10);
        minArrivalTime.setBounds(230, 140, 70, 20);
        contentPane.add(minArrivalTime);

        JLabel minArrivalTimeLabel = new JLabel("Min Arrival Time:");
        minArrivalTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        minArrivalTimeLabel.setBounds(100, 140, 125, 20);
        contentPane.add(minArrivalTimeLabel);

        maxArrivalTime = new JTextField();
        maxArrivalTime.setColumns(10);
        maxArrivalTime.setBounds(230, 170, 70, 20);
        contentPane.add(maxArrivalTime);

        JLabel maxArrivalTimeLabel = new JLabel("Max Arrival Time:");
        maxArrivalTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        maxArrivalTimeLabel.setBounds(100, 170, 125, 20);
        contentPane.add(maxArrivalTimeLabel);

        minServiceTime = new JTextField();
        minServiceTime.setBounds(230, 200, 70, 20);
        contentPane.add(minServiceTime);
        minServiceTime.setColumns(10);

        JLabel minServiceTimeLabel = new JLabel("Min Service Time:");
        minServiceTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        minServiceTimeLabel.setBounds(100, 200, 125, 20);
        contentPane.add(minServiceTimeLabel);

        maxServiceTime = new JTextField();
        maxServiceTime.setColumns(10);
        maxServiceTime.setBounds(230, 230, 70, 20);
        contentPane.add(maxServiceTime);

        JLabel maxServiceTimeLabel = new JLabel("Max Service Time:");
        maxServiceTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        maxServiceTimeLabel.setBounds(100, 230, 125, 20);
        contentPane.add(maxServiceTimeLabel);

        clientsNumber = new JTextField();
        clientsNumber.setColumns(10);
        clientsNumber.setBounds(230, 50, 70, 20);
        contentPane.add(clientsNumber);
    }

    public void addStartSimulationButtonActionListener(ActionListener actionListener){
        startSimulationButton.addActionListener(actionListener);
    }

    public int getNumberOfClients(){
        int input;
        try{
            input = Integer.parseInt(clientsNumber.getText());
            return input;
        }catch(NumberFormatException ex){
            printError("Input must be positive integer!");
            return -1;
        }
    }

    public int getNumberOfQueues(){
        int input;
        try{
            input = Integer.parseInt(queuesNumber.getText());
            return input;
        }catch(NumberFormatException ex){
            printError("Input must be positive integer!");
            return -1;
        }
    }

    public int getSimulationTime(){
        int input;
        try{
            input = Integer.parseInt(simulationTime.getText());
            return input;
        }catch(NumberFormatException ex){
            printError("Input must be positive integer!");
            return -1;
        }
    }

    public int getMinArrivalTime(){
        int input;
        try{
            input = Integer.parseInt(minArrivalTime.getText());
            return input;
        }catch(NumberFormatException ex){
            printError("Input must be positive integer!");
            return -1;
        }
    }

    public int getMaxArrivalTime(){
        int input;
        try{
            input = Integer.parseInt(maxArrivalTime.getText());
            return input;
        }catch(NumberFormatException ex){
            printError("Input must be positive integer!");
            return -1;
        }
    }

    public int getMinServiceTime(){
        int input;
        try{
            input = Integer.parseInt(minServiceTime.getText());
            return input;
        }catch(NumberFormatException ex){
            printError("Input must be positive integer!");
            return -1;
        }
    }

    public int getMaxServiceTime(){
        int input;
        try{
            input = Integer.parseInt(maxServiceTime.getText());
            return input;
        }catch(NumberFormatException ex){
            printError("Input must be positive integer!");
            return -1;
        }
    }

    public void printError(String message){
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
