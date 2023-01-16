public class Job {
    public Integer start_time;
    public Integer end_time;
    public Integer cost;
    
    public Job() {
        super();
    }

    public Job(Integer start_time, Integer end_time, Integer cost) {
        super();
        this.start_time = start_time;
        this.end_time = end_time;
        this.cost = cost;
    }
}