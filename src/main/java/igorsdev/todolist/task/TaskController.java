package igorsdev.todolist.task;

import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskModel task, HttpServletRequest request){
        try {
            TaskModel savedTask = taskService.create(task, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }   

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTask(HttpServletRequest request){
        try {
            List<TaskModel> allTasks = taskService.getAll(request);
            return ResponseEntity.status(HttpStatus.OK).body(allTasks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskModel newTask, @PathVariable UUID id, HttpServletRequest request){
        try {
            taskService.updateTask(request, id, newTask);
            return ResponseEntity.status(HttpStatus.OK).body(newTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
