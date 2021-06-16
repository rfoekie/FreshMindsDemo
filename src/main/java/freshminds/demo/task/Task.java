package freshminds.demo.task;

public abstract class Task implements Runnable {

    int id = -1;

    public int getId() {
        return id;
    }

    public abstract TaskState getState();
}
