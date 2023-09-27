public class Main {
    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.run();
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
