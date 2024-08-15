import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import custompriorityqueue.CustomPriorityQueueImpl;
import custompriorityqueue.api.CustomPriorityQueue;
import lombok.*;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<User> users = findUsers();

        Comparator<User> userByIdComparator = (o1, o2) -> {
            return Long.compare(o1.getId(), o2.getId());
        };

        CustomPriorityQueue<User> priorityUsersQueue = new CustomPriorityQueueImpl<>(userByIdComparator);
        users.forEach(priorityUsersQueue::add);
        System.out.println(priorityUsersQueue);
    }


    private static List<User> findUsers() throws IOException {
        Path path = Paths.get("src/main/resources/initialData.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(path.toFile(), new TypeReference<List<User>>() {
        });
    }
}




