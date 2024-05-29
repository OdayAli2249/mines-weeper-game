package utils;
import java.io.Serializable;


public class Timer  implements Serializable{

    private  long TimePassed;
    private long StartTime;
    private long LastTime;
    private long Now;
    private long TimeLimit;

    public Timer() {

        this.TimePassed = 0;
        this.StartTime = 0;
        this.LastTime = 0;
        this.Now = 0;
        this.TimeLimit = 5000;

        StartTime = System.currentTimeMillis();
        LastTime = StartTime;

    }

    public void ApdateTimer() {

        Now = System.currentTimeMillis();
        TimePassed += (Now - LastTime);
        LastTime = Now;

    }

    public void setTimeLimit(long TimeLimit) {
        this.TimeLimit = TimeLimit;
    }

    public long getTimeLimit() {
        return TimeLimit;
    }

    public boolean Warning() {
        return TimePassed > TimeLimit;
    }

    public long getTimePassed() {
        return TimePassed;
    }
   
    public void Reset() {
        TimePassed = 0;
    }

    public void ApdateLastTime() {
     LastTime = System.currentTimeMillis();
    }
    public void setLastTime(Long Last)
    {
      this.LastTime=Last;  
    }

}
