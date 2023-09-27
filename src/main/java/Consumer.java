public class Consumer implements Runnable {
    private final char chr;
    private final Printable printer;

    public Consumer(char chr, Printable printer) {
        this.chr = chr;
        this.printer = printer;
    }

    @Override
    public void run() {
        System.out.println("Старт расчета Consumer =" + Thread.currentThread().getName());

        Analizer analizer = new Analizer(printer);
        analizer.analize(chr);

        System.out.println("Конец расчета Consumer =" + Thread.currentThread().getName());
    }
}
