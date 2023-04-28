import java.util.Random;

public class RastriginFunction {
    private static final int N = 10;
    private static final double A = 10.0;
    private static final double MIN_X = -5.21;
    private static final double MAX_X = 5.21;
    private static final int DECIMAL_PRECISION = 3;

    public static void main(String[] args) {
        Random random = new Random();
        double[] x = new double[N];

        // Wygeneruj losowe wartości x z zakresu określonego wcześniej.
        for (int i = 0; i < N; i++) {
            x[i] = roundToPrecision(MIN_X + (MAX_X - MIN_X) * random.nextDouble());
        }

        // Oblicz wartość funkcji Rastrigina dla wygenerowanych wartości x        
        double f = A * N;
        for (int i = 0; i < N; i++) {
            f += Math.pow(x[i], 2) - A * Math.cos(2 * Math.PI * x[i]);
        }

        System.out.println("Wartości x: " + arrayToString(x));
        System.out.println("Wartość funkcji f(x): " + roundToPrecision(f));
    }

    private static double roundToPrecision(double value) {
        int scale = (int) Math.pow(10, DECIMAL_PRECISION);
        return (double) Math.round(value * scale) / scale;
    }

    private static String arrayToString(double[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(roundToPrecision(array[i]));
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}