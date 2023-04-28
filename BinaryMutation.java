import java.util.Random;

public class BinaryMutation {
    private static final int CHROMOSOME_LENGTH = 10; // długość chromosomu
    private static final double MIN_X = -2; // minimalna wartość x
    private static final double MAX_X = 2; // maksymalna wartość x
    private static final double MUTATION_PROBABILITY = 0.1; // prawdopodobieństwo mutacji

    public static void main(String[] args) {
        // 1.1 Utworzenie losowego rozwiązania
        boolean[] solution = generateRandomSolution();
        double x1 = decode(solution, 0, CHROMOSOME_LENGTH/2, MIN_X, MAX_X);
        double x2 = decode(solution, CHROMOSOME_LENGTH/2, CHROMOSOME_LENGTH, MIN_X, MAX_X);
        
        String x1Bin = generateRandomBinaryString(16); // 16-bitowa reprezentacja binarna x1
        String x2Bin = generateRandomBinaryString(16); // 16-bitowa reprezentacja binarna x2

        System.out.printf("Losowe rozwiązanie: x1 = %.5f, x2 = %.5f\n", x1, x2);
        System.out.printf("x1 (bin): " + x1Bin);
        System.out.println(", x2 (bin): " + x2Bin);

        // 1.2 Obliczenie wartości funkcji f dla rozwiązania
        double f = calculateFunction(x1, x2);
        System.out.printf("Wartość funkcji dla rozwiązania: f = %.5f\n", f);

        // 1.3 Projektowanie operatora mutacji
        boolean[] mutatedSolution = mutate(solution, MUTATION_PROBABILITY);
        double mutatedX1 = decode(mutatedSolution, 0, CHROMOSOME_LENGTH/2, MIN_X, MAX_X);
        double mutatedX2 = decode(mutatedSolution, CHROMOSOME_LENGTH/2, CHROMOSOME_LENGTH, MIN_X, MAX_X);

        // 1.4 Obliczenie wartości funkcji dla rozwiązania po mutacji
        double mutatedF = calculateFunction(mutatedX1, mutatedX2);
        System.out.printf("Wartość funkcji dla rozwiązania po mutacji: f = %.5f\n", mutatedF);

        // Sprawdzenie czy wartość funkcji uległa zmianie
        if (f == mutatedF) {
            System.out.println("Wartość funkcji nie zmieniła się po mutacji");
        } else {
            System.out.println("Wartość funkcji uległa zmianie po mutacji");
        }
    }

    // Generowanie losowego rozwiązania
    private static boolean[] generateRandomSolution() {
        Random random = new Random();
        boolean[] solution = new boolean[CHROMOSOME_LENGTH];
        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            solution[i] = random.nextBoolean();
        }
        return solution;
    }
    
    // Generuje losowy ciąg zer i jedynek o podanej długości.
   private static String generateRandomBinaryString(int CHROMOSOME_LENGTH) {
       Random random = new Random();
       StringBuilder builder = new StringBuilder();
       for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
           builder.append(random.nextInt(2));
       }
       return builder.toString();
   }

    // Dekodowanie wartości x z reprezentacji binarnej
    private static double decode(boolean[] solution, int start, int end, double min, double max) {
        int value = 0;
        int powerOfTwo = 1;
        for (int i = end-1; i >= start; i--) {
            if (solution[i]) {
                value += powerOfTwo;
            }
            powerOfTwo *= 2;
        }
        return min + ((max - min) * value) / (Math.pow(2, end-start) - 1);
    }

    // Obliczenie wartości funkcji f dla danych x1 i x2
    private static double calculateFunction(double x1, double x2) {
        return -Math.pow(x1, 2) - Math.pow(x2, 2) + 2;
    }
    
    // Operator mutacji
    private static boolean[] mutate(boolean[] solution, double mutationProbability) {
        Random random = new Random();
        boolean[] mutatedSolution = solution.clone();
        for (int i = 0; i < solution.length; i++) {
            if (random.nextDouble() < mutationProbability) {
                mutatedSolution[i] = !mutatedSolution[i]; // zmiana bitu
            }
        }
        return mutatedSolution;
    }
}
