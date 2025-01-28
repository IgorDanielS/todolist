package igorsdev.todolist.task;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public TaskModel create(TaskModel task) throws Exception{
        Optional<TaskModel> taskToFind = taskRepository.findByTitle(task.getTitle());
        if(taskToFind.isPresent()){
            throw new Exception("Task with same title already existed!");
        }
        return taskRepository.save(task);
    }
}
