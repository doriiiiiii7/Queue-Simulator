import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxServiceTime;
    public int minServiceTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfClients;
    public int numberOfQueues;
    public Scheduler scheduler;
    public DisplayArea displayArea;
    private List<Client> generatedClients;
    private FileWriter outputFile;

    String text = "";
    int numberOfClientsInQueues = 0;
    int totalTimeSpentInQueue = 0;
    int totalServiceTime = 0;
    int maxClientsOnQueues = 0;
    int peakHour = 0;
    int servedClients = 0;
    int clientsInQueues = 0;

    public SimulationManager(int numberOfClients, int numberOfQueues, int timeLimit, int minArrivalTime,
                             int maxArrivalTime, int minServiceTime, int maxServiceTime, FileWriter outputFile) {
        this.numberOfClients = numberOfClients;
        this.numberOfQueues = numberOfQueues;
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.outputFile = outputFile;

        displayArea = new DisplayArea();
        displayArea.setVisible(true);

        scheduler = new Scheduler(this.numberOfQueues, this.numberOfClients);
        for (int i = 0; i < this.numberOfQueues; i++) {
            Thread t = new Thread(scheduler.getQueues().get(i));
            t.start();
        }
        generatedClients = new ArrayList<>();
        generateClients();
    }

    private void generateClients() {
        Random rand = new Random();
        for (int i = 0; i < numberOfClients; i++) {
            //generating random numbers between minServiceTime and maxServiceTime
            int randomServiceTime = rand.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;
            //generating random number between minArrivalTime and maxArrivalTime
            int randomArrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            generatedClients.add(new Client(randomArrivalTime, randomServiceTime));
        }
        Collections.sort(generatedClients);
    }

    public String printWaitingClients(int currentTime) {
        String s = "Time: " + currentTime + "\nWaiting clients: ";
        for (Client c : generatedClients) {
            s += c + " ";
        }
        s += "\n";
        return s + printQueues();
    }

    public String printQueues() {
        String s = "";
        Queue[] auxList = new Queue[numberOfQueues];
        for (Queue q : scheduler.getQueues())
            auxList[q.getQueueNumber() - 1] = q;

        for (Queue q : auxList)
            s += "Queue " + q.getQueueNumber() + ": " + q.toString() + "\n";
        s += "\n";

        return s;
    }

    public void processAverageAndStop(int totalTimeSpentInQueue, int totalServiceTime, int peakHour, int maxClientsOnQueues) {
        for(Queue q: scheduler.getQueues())
            for(Client c: q.getClients())
                totalServiceTime -= c.getServiceTime();

        text += "\nAverage waiting time: " + String.format("%.2f", ((float) totalTimeSpentInQueue) / clientsInQueues);
        text += "\nAverage serve time: " + String.format("%.2f", ((float) totalServiceTime) / servedClients);
        text += "\nPeak hour was at time " + peakHour + ", when there were " + maxClientsOnQueues + " clients at queues";

        for (Queue q : scheduler.getQueues())
            q.stopThread();
    }

    public void processFirstClient() {
        numberOfClientsInQueues = 0;
        for (Queue q : scheduler.getQueues()) {
            numberOfClientsInQueues += q.getClients().size();
            if (!q.isEmpty()) {
                Client frontClient = (Client) q.getClients().peek();
                int currentServingTime = frontClient.getServiceTime();
                frontClient.setServiceTime(currentServingTime - 1);
                if (frontClient.getServiceTime() == 0) {
                    totalTimeSpentInQueue += frontClient.getTimeSpentInQueue();
                    servedClients++;
                    q.removeClientFromQueue();
                }
            }
        }
    }

    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit && servedClients < numberOfClients) {
            while (!generatedClients.isEmpty() && generatedClients.get(0).getArrivalTime() == currentTime) {
                scheduler.sendClientToQueue(generatedClients.get(0));
                totalServiceTime += generatedClients.get(0).getServiceTime();
                clientsInQueues++;
                generatedClients.remove(0);
            }
            text += printWaitingClients(currentTime);
            displayArea.setText(text);
            processFirstClient();
            if (servedClients == numberOfClients)
                text += printWaitingClients(currentTime + 1);
            if (numberOfClientsInQueues > maxClientsOnQueues) {
                maxClientsOnQueues = numberOfClientsInQueues;
                peakHour = currentTime;
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            processAverageAndStop(totalTimeSpentInQueue, totalServiceTime, peakHour, maxClientsOnQueues);
            outputFile.write(text);
            displayArea.setText(text);
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UI userInterface = new UI();
        Controller controller = new Controller(userInterface);
        userInterface.setVisible(true);
    }
}
