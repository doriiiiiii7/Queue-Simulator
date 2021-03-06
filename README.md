# Queue-Simulator
  Design and implement a simulation application aiming to analyse queuing based systems for determining and minimizing clientsβ waiting time.

  The application should simulate (by defining a simulation time π‘π πππ’πππ‘πππ) a series of N clients arriving for service, entering Q queues, waiting, being served and finally leaving the queues. All clients are generated when the simulation is started, and are characterized by three parameters: ID (a number between 1 and N), π‘πππππ£ππ (simulation time when they are ready to go to the queue; i.e. time when the client finished shopping) and π‘π πππ£πππ (time interval or duration needed to serve the client; i.e. waiting time when the client is in front of the queue). The application tracks the total time spent by every client in the queues and computes the average waiting time. Each client is added to the queue with minimum waiting time when its π‘πππππ£ππ time is greater than or equal to the simulation time (π‘πππππ£ππβ₯π‘π πππ’πππ‘πππ).

  The following data should be considered as input data for the application that should be inserted by the user in the applicationβs user interface:
- Number of clients (N);
- Number of queues (Q);
- Simulation interval (π‘π πππ’πππ‘πππππ΄π);
- Minimum and maximum arrival time (π‘πππππ£ππππΌπβ€π‘πππππ£ππβ€π‘πππππ£ππππ΄π);
- Minimum and maximum service time (π‘π πππ£πππππΌπβ€π‘π πππ£πππβ€π‘π πππ£πππππ΄π);
