package igorsdev.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskModel task){
        System.out.println("Chegou no controller");
        try {
            taskService.create(task);
            return ResponseEntity.ok().body(task);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }   

    }
}
