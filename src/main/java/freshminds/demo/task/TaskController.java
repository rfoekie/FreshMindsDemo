package freshminds.demo.task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskManager taskManager;

    TaskController() {
        this(new TaskManager());
    }

    TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @GetMapping("/tasks/{id}")
    Task one(@PathVariable Integer id) {
        return taskManager.getTaskById(id);
    }

    @GetMapping("/tasks")
    List<Task> all() {
        return taskManager.getAllTasks();
    }

    @PostMapping("/tasks")
    Task loop() {
        Task task = new LoopTask();
        taskManager.schedule(task);
        return task;
    }
}
