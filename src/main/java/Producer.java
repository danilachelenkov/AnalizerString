public class Producer implements Runnable {
    @Override
    public void run() {
        Analizer analizer = new Analizer(new Printer());
        analizer.fillQueue();
    }
}
