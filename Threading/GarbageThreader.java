public class GarbageThreader {
    public static main(String[] args){
        GarbageThread thread = new GarbageThread(5,10);
        GarbageThread threadTwo = new GarbageThread(2,4);
        GarbageThread threadThree = new GarbageThread(-12, 9);
        thread.start();
        threadTwo.start();
        threadThree.start();
    }
}

class GarbageThread extends thread {
    public int NumOne, NumTwo;
    public garbageThread(int numOne, int numTwo) {
        System.out.println("I've been constructed");
        NumOne = numOne;
        NumTwo = numTwo;
    }
    public void run() {
        System.out.println(NumOne + " + " +)
    }
}

