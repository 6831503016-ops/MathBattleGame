import java.io.*;
import java.util.*;

public class GameManager {

    private final Queue<Question> questions = new LinkedList<>();
    private final Stack<String> history = new Stack<>();
    private final Leaderboard leaderboard = new Leaderboard();

    public void startGame() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            Player player = new Player(name);
            Enemy enemy = new Enemy();

            // โหลดคำถามเริ่มต้น
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0)
                    questions.add(new AddQuestion());
                else
                    questions.add(new MultiplyQuestion());
            }

            // 🔥 เล่นจนกว่าจะมีคน HP = 0
            while (player.getHp() > 0 && enemy.getHp() > 0) {

                // ✅ ถ้าคำถามหมด → สร้างใหม่ (ไม่ให้ DRAW)
                if (questions.isEmpty()) {
                    for (int i = 0; i < 5; i++) {
                        if (i % 2 == 0)
                            questions.add(new AddQuestion());
                        else
                            questions.add(new MultiplyQuestion());
                    }
                }

                Question q = questions.poll();
                int correct = q.ask();

                try {
                    System.out.print("Answer: ");
                    int ans = sc.nextInt();
                    if (ans < 0) {
                        throw new InvalidChoiceException("Answer must be positive");
}
                    history.push("Correct: " + correct + " Your: " + ans);
                    int damage = 10;
                    if (ans == correct) {
                        System.out.println("Correct! You hit enemy!");
                        enemy.takeDamage(damage);
                        player.addScore(10);
                    } else {
                        System.out.println("Wrong! Enemy hits you!");
                        player.takeDamage(damage);
                    }

                } catch (InvalidChoiceException e) {
                    System.out.println(e.getMessage());
                    } catch (Exception e) {
                    System.out.println("number only ):<");
                    sc.next(); 
                }

                System.out.println("Player HP: " + player.getHp() +
                            " | Enemy HP: " + enemy.getHp());
            }

            
            if (player.getHp() > 0) {
                System.out.println("YOU WIN!");
            } else {
                System.out.println("ENEMY WINS!");
            }

            System.out.println("Final Score: " + player.getScore());

            leaderboard.add(player);
            leaderboard.display();

            saveScore(player);
        }

        showHistory();
    }

    // 📁 File I/O
    private void saveScore(Player p) {
        try {
            try (FileWriter fw = new FileWriter("score.txt", true)) {
                fw.write(p.name + " : " + p.getScore() + "\n");
            }
        } catch (IOException e) {
            System.out.println("File error");
        }
    }

    // 📚 Stack (history)
    private void showHistory() {
        System.out.println("\nHistory:");
        while (!history.isEmpty()) {
            System.out.println(history.pop());
        }
    }

    public Queue<Question> getQuestions() {
        return questions;
    }
}