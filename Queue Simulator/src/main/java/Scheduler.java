import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    private List<Queue> queues;
    private int maxNoQueues;
    private int maxClientsPerServer;

    public Scheduler(int maxNoQueues, int maxClientsPerServer){
        queues = new ArrayList<>();
        this.maxNoQueues = maxNoQueues;
        this.maxClientsPerServer = maxClientsPerServer;

        for(int i = 0; i < maxNoQueues; i++)
            queues.add(new Queue(i+1));
    }

    public void sendClientToQueue(Client newClient){
        Collections.sort(queues);
        int waitingTimeOnQueue = queues.get(0).getWaitingPeriodOnQueue().intValue();
        newClient.setTimeSpentInQueue(waitingTimeOnQueue + newClient.getServiceTime());
        queues.get(0).addClientInQueue(newClient);
    }
    public List<Queue> getQueues() {
        return queues;
    }
}
