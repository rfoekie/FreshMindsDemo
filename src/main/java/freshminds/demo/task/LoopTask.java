package freshminds.demo.task;

public class LoopTask extends Task {

    private TaskState state = TaskState.CREATED;

    @Override
    public synchronized void run() {
        if (state != TaskState.CREATED) {
            throw new IllegalStateException("task has already run");
        }
        state = TaskState.RUNNING;
        for (int idx = 0; idx < 1000; idx++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        state = TaskState.COMPLETED;
    }

    @Override
    public TaskState getState() {
        return state;
    }
}
