import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import custompriorityqueue.CustomPriorityQueueImpl;
import custompriorityqueue.api.CustomPriorityQueue;
import model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {

        List<User> users = findUsers();

        Comparator<User> userByIdComparator = Comparator.comparingLong(User::getId);

        //CustomPriorityQueue<User> priorityUsersQueue = new CustomPriorityQueueImpl<>(userByIdComparator.reversed());
        CustomPriorityQueue<User> priorityUsersQueue = new CustomPriorityQueueImpl<>();
        users.forEach(priorityUsersQueue::add);

        User user = null;
        int queueSize = priorityUsersQueue.getSize();

        // ВЫВОДИМ СПИСОК ПОЛЬЗОВАТЕЛЕЙ ОТ МАЛА ДО ВЕЛИКА

        for (int i = 0; i < queueSize; i++) {
            user = priorityUsersQueue.poll();
            System.out.println(user);
        }
    }

    private static List<User> findUsers() throws IOException {
        Path path = Paths.get("src/main/resources/initialData.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(path.toFile(), new TypeReference<List<User>>() {
        });
    }
}





