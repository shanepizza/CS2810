public class UselessThreader {
    public static void main(String[] args) {
        ThreadedClass threadOne = new ThreadedClass("one");
        ThreadedClass threadTwo = new ThreadedClass("two");
        ThreadedClass threadThree = new ThreadedClass("three");

        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }//End main
}//End Useless Theader

class ThreadedClass extends Thread {
    public String _name;
    public ThreadedClass(String name){
        _name = name;
    }//End Threaded constructor 
    public void run(){
        System.out.println("Hi there! My name is "+ _name);
    }//End Run
}//End Threaded Class
