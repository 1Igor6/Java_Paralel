import java.util.Scanner;

    public class Main {
    public static void main(String[] args) {
        System.out.print("Enter amount of threads: ");
        int amount = new Scanner(System.in).nextInt();

        ThreadStopper threadStopper = new ThreadStopper();

        for (int i = 0; i < amount; i++) {
            ThreadCalculator thread = new ThreadCalculator(i + 1);
            threadStopper.addThread(thread);
            thread.start();
        }

        threadStopper.start();
    }
}