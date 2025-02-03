package igorsdev.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskModel create(TaskModel task, HttpServletRequest request) throws Exception {
        var currentDate = LocalDateTime.now();
        var idRequest = (UUID) request.getAttribute("idUser");

        List<TaskModel> taskFromUser = taskRepository.findByIdUser(idRequest);

        Optional<TaskModel> taskToFind = taskFromUser.stream()
                .filter(t -> t.getTitle().equalsIgnoreCase(task.getTitle()))
                .findFirst();

        if (taskToFind.isPresent()) {
            throw new Exception("Task with same title already existed!");
        }
        if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            throw new Exception("Cant attribute past dates to taks!");
        }
        if (task.getStartAt().isAfter(task.getEndAt())) {
            throw new Exception("Cant attribute End At before Start At date");
        }
        task.setIdUser(idRequest);
        return taskRepository.save(task);
    }

    public List<TaskModel> getAll(HttpServletRequest request) throws Exception {
        var idRequest = request.getAttribute("idUser");
        List<TaskModel> tasks = taskRepository.findByIdUser((UUID) idRequest);
        if (tasks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No tasks found.");
        }
        return tasks;
    }

    public TaskModel updateTask(HttpServletRequest request, UUID id, TaskModel newTask){
        Optional<TaskModel> taskToFind = taskRepository.findById(id);
        System.out.println(taskToFind.get());
        if(taskToFind.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No tasks where found with id:"+id);
        }
        var idRequest = (UUID) request.getAttribute("idUser");
        System.out.println(idRequest);
        newTask.setIdUser(idRequest);
        newTask.setId(id);
        System.out.println(newTask.getIdUser());
        return taskRepository.save(newTask);
    }
}
