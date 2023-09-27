import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Application {
    private final Printable printer;

    public Application() {
        this.printer = new Printer();
    }

    public void run() throws InterruptedException {

        List<Thread> listThread = new ArrayList<>();

        System.out.println("Заполняю очередь");

        Thread threadProducer = new Thread(new Producer());
        threadProducer.setName("threadProducer");

        Thread threadConsumerA = new Thread(new Consumer('a', printer));
        Thread threadConsumerB = new Thread(new Consumer('b', printer));
        Thread threadConsumerC = new Thread(new Consumer('c', printer));
        threadConsumerA.setName("threadConsumerA");
        threadConsumerB.setName("threadConsumerB");
        threadConsumerC.setName("threadConsumerC");

        System.out.println("Добавляем задания");

        listThread.add(new Thread(threadConsumerA));
        listThread.add(new Thread(threadConsumerB));
        listThread.add(new Thread(threadConsumerC));
        listThread.add(threadProducer);

        System.out.println(Analizer.blockingQueueA);

        for (Thread thread : listThread) {
            System.out.println("Запускаю задание " + thread.getName() + " " + thread.threadId());
            thread.start();

            if (thread.getName().equals("threadProducer")) {
                thread.join();
            }
        }

        printer.print("\nРасчет завершен");
    }
}
