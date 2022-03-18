public class Client implements Comparable<Client> {
    private static int clients = 0;
    private int id;
    private int arrivalTime;
    private int serviceTime;
    private int timeSpentInQueue;

    public Client(int arrivalTime, int serviceTime){
        clients++;
        this.id = clients;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Client otherClient) {
        return this.arrivalTime - otherClient.arrivalTime;
    }

    public String toString(){
        return "(" + id + ", " + arrivalTime + ", " + serviceTime + ")";
    }

    public void setTimeSpentInQueue(int timeSpentInQueue) {
        this.timeSpentInQueue = timeSpentInQueue;
    }
    public int getTimeSpentInQueue() {
        return timeSpentInQueue;
    }
}
