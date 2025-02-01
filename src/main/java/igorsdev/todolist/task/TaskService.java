package igorsdev.todolist.task;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public TaskModel create(TaskModel task) throws Exception{
        Optional<TaskModel> taskToFind = taskRepository.findByTitle(task.getTitle());
        var currentDate = LocalDateTime.now();
        if(taskToFind.isPresent()){
            throw new Exception("Task with same title already existed!");
        }
        if(currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())){
            throw new Exception("Cant attribute past dates to taks!");
        }
        if(task.getStartAt().isAfter(task.getEndAt())){
            throw new Exception("Cant attribute End At before Start At date");
        }
    
        return taskRepository.save(task);
    }
}
