import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable, Comparable<Queue> {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriodOnQueue;
    private int queueNumber;
    private static final int maxNoQueues = 100;
    private boolean threadIsWorking;

    public Queue(int id){
        this.threadIsWorking = true;
        this.queueNumber = id;
        clients = new ArrayBlockingQueue<Client>(maxNoQueues);
        waitingPeriodOnQueue = new AtomicInteger(0);
    }
    public void addClientInQueue(Client newClient) {
        clients.add(newClient);
        waitingPeriodOnQueue.getAndAdd(newClient.getServiceTime());
    }

    public void stopThread() {
        this.threadIsWorking = false;
    }

    public boolean isEmpty(){
        return clients.isEmpty();
    }

    public void removeClientFromQueue(){
        clients.poll();
    }

    @Override
    public int compareTo(Queue otherQueue) {
        return this.waitingPeriodOnQueue.intValue() - otherQueue.getWaitingPeriodOnQueue().intValue();
    }

    @Override
    public void run() {
        while(threadIsWorking){
            Client headClient = clients.peek();
            if(headClient != null) {
                for(int i = 0; i < headClient.getServiceTime(); i++){
                    try {
                        Thread.sleep( 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waitingPeriodOnQueue.decrementAndGet();
                }
            }
        }
    }

    public BlockingQueue<Client> getClients(){
        return clients;
    }



    public AtomicInteger getWaitingPeriodOnQueue() {
        return waitingPeriodOnQueue;
    }

    public void setWaitingPeriodOnQueue(AtomicInteger waitingPeriodOnQueue) {
        this.waitingPeriodOnQueue = waitingPeriodOnQueue;
    }



    public String toString(){
        String s = "";
        if(clients.isEmpty()){
            return "closed";
        }else{
            for(Client c: clients){
                s = s + c.toString() + " ";
            }
        }
        return s;
    }

    public int getQueueNumber() {
        return queueNumber;
    }
}
