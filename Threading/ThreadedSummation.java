public class ThreadedSummation {
    public static void main(String[] args){
        SummationThread threadOne = new SummationThread(5);
        SummationThread threadTwo = new SummationThread(5000);
        SummationThread threadThree = new SummationThread(500_000);

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        System.out.println(threadOne.Result);
        System.out.println(threadTwo.Result);
        System.out.println(threadThree.Result);
        
    }
}


class SummationThread extends Thread{
    public int Result;
    private long _initialvalue;
    public SummationThread(int initialValue) {_initialvalue = initialValue;}

    public void run(){
        System.out.println("I'm running");
        for(int i = 0; i <= _initialvalue; i++){
            Result += i;
        }
    }
}
