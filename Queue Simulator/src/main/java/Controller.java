import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    private final UI userInterface;

    public Controller(UI userInterface) {
        this.userInterface = userInterface;
        userInterface.addStartSimulationButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter myWriter = new FileWriter("output1.txt");
                    int numberOfClients = userInterface.getNumberOfClients();
                    int numberOfQueues = userInterface.getNumberOfQueues();
                    int simulationTime = userInterface.getSimulationTime();
                    int minArrivalTime = userInterface.getMinArrivalTime();
                    int maxArrivalTime = userInterface.getMaxArrivalTime();
                    int minServiceTime = userInterface.getMinServiceTime();
                    int maxServiceTime = userInterface.getMaxServiceTime();
                    if (numberOfClients >= 1 && numberOfQueues >= 1 && simulationTime >= 1 && minArrivalTime >= 1
                            && maxArrivalTime >= 1 && minServiceTime >= 1 && maxServiceTime >= 1) {
                        if (minArrivalTime > maxArrivalTime && minServiceTime <= maxServiceTime) {
                            userInterface.printError("Minimum arrival time must be lower or equal to maximum arrival time!");
                        } else {
                            if (minServiceTime > maxServiceTime)
                                userInterface.printError("Minimum service time must be lower or equal to maximum service time!");
                            else {
                                SimulationManager gen = new SimulationManager(numberOfClients, numberOfQueues, simulationTime,
                                        minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, myWriter);
                                Thread t = new Thread(gen);
                                t.start();
                            }
                        }
                    } else {
                        userInterface.printError("Input must be positive integer!");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
