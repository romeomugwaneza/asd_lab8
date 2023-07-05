package bank.integration;

public class StopWatch {
    private long start = 0;
    private long finish = 0;
    private long timeElapsed =0;
    public void start(){
        start = System.currentTimeMillis();
    }
    public void stop(){
        finish = System.currentTimeMillis();
    }
    public long getMillis(){
        timeElapsed = finish - start;
        return timeElapsed;
    }
}
