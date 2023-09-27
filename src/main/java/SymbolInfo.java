public class SymbolInfo {
    private final String text;
    private final int count;

    private final char symbol;

    public SymbolInfo(String text, int count, char chr) {
        this.text = text;
        this.count = count;
        this.symbol = chr;
    }

    public String getText() {
        return text;
    }

    public int getCount() {
        return count;
    }

    public char getSymbol() {
        return symbol;
    }
}
