import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Analizer {
    private static int capacity = 100;
    public final static BlockingQueue<String> blockingQueueA = new ArrayBlockingQueue<>(capacity, true);
    public final static BlockingQueue<String> blockingQueueB = new ArrayBlockingQueue<>(capacity, true);
    public final static BlockingQueue<String> blockingQueueC = new ArrayBlockingQueue<>(capacity, true);

    private static Printable printer;


    public Analizer(int capacity, Printable printer) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Значение аргумента не может быть <=0");
        }
        Analizer.capacity = capacity;
        Analizer.printer = printer;
    }

    public Analizer(Printable printer) {
        Analizer.printer = printer;
    }

    public void fillQueue() {
        for (int i = 0; i < 10_000; i++) {
            String genString = Generator.generateText("abc", 100_000);
            try {
                blockingQueueA.put(genString);
                printer.print("Заполнил blockingQueueA " + blockingQueueA.size());

                blockingQueueB.put(genString);
                printer.print("Заполнил blockingQueueB " + blockingQueueB.size());

                blockingQueueC.put(genString);
                printer.print("Заполнил blockingQueueC " + blockingQueueC.size());

            } catch (InterruptedException ex) {
                return;
            }
            printer.print("Добавили = " + i);
        }
    }

    public void analize(char chr) {
        if (chr == 'a' || chr == 'b' || chr == 'c') {

            SymbolInfo info = getAnalize(Character.toLowerCase(chr));
            String resultAnalize = String.join("\n",
                    String.format("\nМаксимальное количество повторений символов %s в стороке %d", info.getSymbol(), info.getCount()),
                    "Строка выглядит следующим образом: \n" + info.getText()
            );
            printer.print(resultAnalize);
        } else {
            throw new IllegalArgumentException(String.format("Введен неразрешенный символ %s разрешены только символы a|b|c", chr));
        }
    }

    private SymbolInfo getAnalize(char chr) {
        BlockingQueue<String> arrayBlockingQueue = getBlockingQueue(chr);
        int cntChars = 0;
        String currentText = "";
        String text = "";

        printer.print("символ = " + chr);

        for (int i = 0; i < 10_000; i++) {
            try {
                text = arrayBlockingQueue.take();
                int currentCount = getRepetitionSymbol(text, chr);

                if (currentCount >= cntChars) {
                    cntChars = currentCount;
                    currentText = text;
                }
                printer.print("Взяли " + i + " (" + currentCount + ") " + Thread.currentThread().getName());

            } catch (InterruptedException ex) {
                printer.print(ex.getMessage());
            }
        }
        return new SymbolInfo(currentText, cntChars, chr);
    }

    private BlockingQueue<String> getBlockingQueue(char chr) {
        return switch (chr) {
            case 'a' -> blockingQueueA;
            case 'b' -> blockingQueueB;
            case 'c' -> blockingQueueC;
            default -> new ArrayBlockingQueue<String>(1);
        };
    }

    private int getRepetitionSymbol(String line, char chrSearch) {
        int cnt = 0;
        for (char item : line.toCharArray()) {
            if (item == chrSearch) {
                cnt++;
            }
        }
        return cnt;
    }
}
