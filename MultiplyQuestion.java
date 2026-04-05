import java.util.Random;

public class MultiplyQuestion implements Question {
    int a, b;

    public MultiplyQuestion() {
        Random r = new Random();
        a = r.nextInt(10) + 1;
        b = r.nextInt(10) + 1;
    }

    
    @Override
    public int ask() {
        System.out.println(a + " * " + b + " = ?");
        return a * b;
    }
}