import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import custompriorityqueue.CustomPriorityQueueImpl;
import custompriorityqueue.api.CustomPriorityQueue;
import lombok.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        CustomPriorityQueue<Integer> numbers = new CustomPriorityQueueImpl<>();

        numbers.add(1);
        numbers.add(5);
        numbers.add(10);
        numbers.add(7);
        numbers.add(17);
        numbers.add(21);
        numbers.add(4);
        numbers.add(6);
        numbers.add(12);
        numbers.add(3);
        numbers.add(3);
        numbers.add(15);
        numbers.add(20);
        numbers.add(15);
        numbers.add(48);
        numbers.add(55);
        numbers.add(61);
        numbers.add(67);
        numbers.add(88);
        numbers.add(96);
        numbers.add(1654);
        numbers.add(6464);
        numbers.add(6445);
        numbers.add(694);

        System.out.println(numbers);
        Integer lowPriorityElement = numbers.poll();
        System.out.println(numbers);



    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    private static class User implements Comparable<User> {
        private long id;
        private String firsName;
        private String LastName;
        private LocalDateTime birthDay;

        @Override
        public int compareTo(User user) {
            return birthDay.compareTo(user.getBirthDay());
        }

        public long getId() {
            return id;
        }

        public String getFirsName() {
            return firsName;
        }

        public String getLastName() {
            return LastName;
        }

        public LocalDateTime getBirthDay() {
            return birthDay;
        }
    }

}


