package freshminds.demo.task;

public interface Task extends Runnable {

    public abstract TaskState getState();
}
