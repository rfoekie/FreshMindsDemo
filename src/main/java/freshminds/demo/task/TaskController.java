package freshminds.demo.task;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    private final TaskManager taskManager;
    private final TaskModelAssembler assembler;

    TaskController() {
        this(new TaskManager(), new TaskModelAssembler());
    }

    TaskController(TaskManager taskManager, TaskModelAssembler assembler) {
        this.taskManager = taskManager;
        this.assembler = assembler;
    }

    @GetMapping("/tasks/{id}")
    EntityModel<Task> one(@PathVariable Integer id) {
        return assembler.toModel(taskManager.getTaskById(id));
    }

    @GetMapping("/tasks")
    CollectionModel<EntityModel<Task>> all() {
        List<EntityModel<Task>> tasks = taskManager.getAllTasks().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(
                tasks,
                linkTo(methodOn(TaskController.class).all()).withSelfRel());
    }

    @PostMapping("/tasks")
    EntityModel<Task> loop() {
        Task task = new LoopTask();
        taskManager.schedule(task);
        return assembler.toModel(task);
    }
}
