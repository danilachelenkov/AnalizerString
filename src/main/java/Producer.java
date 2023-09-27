public class Producer implements Runnable {
    @Override
    public void run() {
        System.out.println("Старт заполнения BlockingQueue");

        Analizer analizer = new Analizer(new Printer());
        analizer.fillQueue();

        System.out.println("Заполнил BlockingQueue");
    }
}
