import java.util.Random;

public class Esercizio2 {
    public static void main(String[] args) throws InterruptedException {
        int[] values = generateRandomArray(3000);

        // Divide l'array in tre parti
        int[][] partitions = new int[3][1000];
        for (int i = 0; i < 3; i++) {
            partitions[i] = getPartition(values, i);
        }

        // Crea i tre thread e assegna a ognuno una partizione dell'array
        SumThread[] threads = new SumThread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new SumThread(partitions[i]);
            threads[i].start();
        }

        // Aspetta che i thread terminino e raccoglie i risultati parziali
        int[] partialSums = new int[3];
        for (int i = 0; i < 3; i++) {
            threads[i].join();
            partialSums[i] = threads[i].getSum();
        }

        // Somma i risultati parziali e li stampa su console
        int totalSum = 0;
        for (int i = 0; i < 3; i++) {
            totalSum += partialSums[i];
        }
        System.out.println("La somma totale dell'array è: " + totalSum);
    }

    // Metodo per generare un array di numeri casuali
    private static int[] generateRandomArray(int length) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    // Metodo per ottenere una partizione di un array
    private static int[] getPartition(int[] array, int part) {
        int[] partition = new int[1000];
        System.arraycopy(array, part * 1000, partition, 0, 1000);
        return partition;
    }
}

class SumThread extends Thread {
    private int[] values;
    private int sum;

    public SumThread(int[] values) {
        this.values = values;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
    }
}
