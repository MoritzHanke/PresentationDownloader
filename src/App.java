import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            ConfigInterpreter interpreter = new ConfigInterpreter();
            int i = 1;
            while (interpreter.hasNext()){
                boolean successful = interpreter.readNext();
                if (!successful){
                    System.out.println("Line "+ i + " couldn't be read, interpreted and downloaded successfully");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("an error occurred, please try again later");
        }
    }
}
