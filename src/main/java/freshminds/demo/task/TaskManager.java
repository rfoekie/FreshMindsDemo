package freshminds.demo.task;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskManager {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    private final static AtomicInteger count = new AtomicInteger(0);

    private final Map<Integer, Task> tasks = new LinkedHashMap<>();

    public synchronized void schedule(Task task) {
        int id = count.getAndIncrement();
        tasks.put(id, task);
        EXECUTOR_SERVICE.submit(task);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
}
