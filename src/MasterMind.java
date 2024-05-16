
import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;
        import java.util.Scanner;

public class MasterMind {
    private static final int NUM_COLORS = 6;
    private static final int SEQUENCE_LENGTH = 4;

     private List<Integer> secretSequence; private List<Integer> currentGuess;
     private Random random;


    public MasterMind() {
        this.secretSequence = new ArrayList<>();
        this.currentGuess = new ArrayList<>();
        this.random = new Random();
        generateSecretSequence();
    }

    private void generateSecretSequence() {
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            secretSequence.add(random.nextInt(NUM_COLORS));
        }
    }

    private void makeRandomGuess() {
        currentGuess.clear();
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            currentGuess.add(random.nextInt(NUM_COLORS));
        } }

    private int[] gradeGuess(List<Integer> guess) {
        int correctPosition = 0;
        int correctColor = 0;
        boolean[] usedInSecret = new boolean[SEQUENCE_LENGTH];
        boolean[] usedInGuess = new boolean[SEQUENCE_LENGTH];

        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            if (guess.get(i).equals(secretSequence.get(i))) {
                correctPosition++;
                usedInSecret[i] = true;
                usedInGuess[i] = true; }
        }

        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            if (!usedInGuess[i]) {
                for (int j = 0; j < SEQUENCE_LENGTH; j++) {
                    if (!usedInSecret[j] && guess.get(i).equals(secretSequence.get(j))) {
                        correctColor++;
                        usedInSecret[j] = true;
                        break; }
                }
            }
        }

        return new int[]{correctPosition, correctColor};
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        while (true) {
            makeRandomGuess();
            attempts++;

            int[] grade = gradeGuess(currentGuess);
            System.out.println("Attempt " + attempts + ": " + currentGuess + " -> " + grade[0] + " correct positions, " + grade[1] + " correct colors");

            if (grade[0] == SEQUENCE_LENGTH) {
                System.out.println("Yayy. You've guessed the sequence in " + attempts + " attempts.");
                break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        MasterMind game = new MasterMind();
        game.playGame();




    }
}
