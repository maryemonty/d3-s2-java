public class Eserciziouno {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable("#"));
        Thread thread2 = new Thread(new MyRunnable("*\n"));

        thread1.start();
        thread2.start();
    }
}

class MyRunnable implements Runnable {
    private String symbol;

    public MyRunnable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.print(symbol);
            try {
                Thread.sleep(1000); // Attende 1 secondo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

