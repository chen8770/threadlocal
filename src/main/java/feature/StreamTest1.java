package feature;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest1 {
    public static void main(String[] args) {
        List<Task> tasks = getTasks();

        List<String> readingTasks = tasks.stream()
                .filter(task -> task.getType().intValue() %2==0)
                .sorted((t1, t2) -> t2.getTitle().length()-t1.getTitle().length())
                .map(Task::getTitle)
                .collect(Collectors.toList());

        readingTasks.forEach(System.out::println);
    }
    private static List<Task> getTasks(){
        List<Task> tasks=new ArrayList<>();
        for(int i=0;i<12;i++){
            Task task=new Task();
            task.setTitle("mytitle"+i);
            task.setType(i);
            tasks.add(task);
        }
        return tasks;
    }
}
    class Task{
        private String title;
        private Integer type;
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }

