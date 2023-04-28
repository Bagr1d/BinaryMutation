import java.util.Arrays;
import java.util.Random;

public class PMXCrossover {
    private static final int CHROMOSOME_LENGTH = 10;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        // Utwórz dwa rodziców o reprezentacji ścieżkowej
        int[] parent1 = generateRandomPath();
        int[] parent2 = generateRandomPath();

        // Wyświetl rodziców
        System.out.println("Rodzic 1: " + Arrays.toString(parent1));
        System.out.println("Rodzic 2: " + Arrays.toString(parent2));

        // Krzyżowanie PMX
        int[] child1 = pmxCrossover(parent1, parent2);
        int[] child2 = pmxCrossover(parent2, parent1);

        // Wyświetl potomków
        System.out.println("Potomek 1: " + Arrays.toString(child1));
        System.out.println("Potomek 2: " + Arrays.toString(child2));
    }

    // Losowa permutacja liczb 1-10
    private static int[] generateRandomPath() {
        int[] path = new int[CHROMOSOME_LENGTH];
        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            path[i] = i + 1;
        }
        for (int i = CHROMOSOME_LENGTH - 1; i > 0; i--) {
            int index = RANDOM.nextInt(i + 1);
            int temp = path[index];
            path[index] = path[i];
            path[i] = temp;
        }
        return path;
    }

    // Krzyżowanie PMX
    private static int[] pmxCrossover(int[] parent1, int[] parent2) {
        int[] child = new int[CHROMOSOME_LENGTH];
        int start = RANDOM.nextInt(CHROMOSOME_LENGTH - 1);
        int end = RANDOM.nextInt(CHROMOSOME_LENGTH - start - 1) + start + 1;

        // Skopiuj segment od rodzica 1
        for (int i = start; i <= end; i++) {
            child[i] = parent1[i];
        }

        // Wypełnij pozostałe miejsca od rodzica 2
        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            if (i < start || i > end) {
                int value = parent2[i];
                while (contains(child, value)) {
                    int index = indexOf(parent1, value);
                    value = parent2[index];
                }
                child[i] = value;
            }
        }

        return child;
    }

    // Czy tablica zawiera wartość?
    private static boolean contains(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    // Indeks wartości w tablicy
    private static int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
