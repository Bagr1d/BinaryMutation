public class PathIndividual {
    private int[] path;
    private int pathLength;
    
    // konstruktor, getter-y i inne metody
    
    public void inverse(int point1, int point2) {
        // sprawdzenie, czy argumenty są poprawne
        if (point1 >= point2 || point1 < 0 || point2 >= pathLength) {
            throw new IllegalArgumentException("Niepoprawne argumenty operacji inwersji.");
        }
        
        // odwrócenie kolejności między wybranymi punktami
        for (int i = point1, j = point2; i < j; i++, j--) {
            int temp = path[i];
            path[i] = path[j];
            path[j] = temp;
        }
    }
}