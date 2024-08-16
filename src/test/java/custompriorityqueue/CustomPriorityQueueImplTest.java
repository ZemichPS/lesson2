package custompriorityqueue;

import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomPriorityQueueImplTest {

    private CustomPriorityQueueImpl<User> customPriorityQueue;
    private CustomPriorityQueueImpl<User> customPriorityQueueWithComparator;

    @BeforeEach
    public void setUp() {
        customPriorityQueue = new CustomPriorityQueueImpl<>();

        Comparator<User> userByIdComparator = Comparator.comparingLong(User::getId);
        customPriorityQueueWithComparator = new CustomPriorityQueueImpl<>(userByIdComparator);
    }

    @Test
    void testAdd(){
        User user1 = new User(1L, "Jack", "Diaz", LocalDateTime.of(1998, Month.APRIL, 15, 1, 4 ));
        User user2 = new User(2L, "Grace", "Bell", LocalDateTime.of(1985, Month.AUGUST, 18, 0, 1 ));

        customPriorityQueue.add(user1);
        customPriorityQueue.add(user2);

        assertEquals(2, customPriorityQueue.getSize());

    }

    @Test
    void testPeek(){
        User user1 = new User(1L, "Jack", "Diaz", LocalDateTime.of(1998, Month.APRIL, 15, 1, 4 ));
        User user2 = new User(2L, "Grace", "Bell", LocalDateTime.of(1985, Month.AUGUST, 18, 0, 1 ));
        User user3 = new User(3L, "Henry", "Ward", LocalDateTime.of(1997, Month.DECEMBER, 12, 7, 35 ));
        User user4 = new User(4L, "Ivy", "Cox", LocalDateTime.of(2012, Month.APRIL, 15, 1, 4 ));
        User user5 = new User(5L, "Kelly", "Hughes", LocalDateTime.of(2018, Month.DECEMBER, 7, 11, 28 ));

        customPriorityQueue.add(user1);
        customPriorityQueue.add(user2);
        customPriorityQueue.add(user3);
        customPriorityQueue.add(user4);
        customPriorityQueue.add(user5);

        assertEquals(5L, customPriorityQueue.peek().getId());
        assertEquals(5, customPriorityQueue.getSize());

    }

    @Test
    void testPeekWithComparator(){
        User user2 = new User(2L, "Grace", "Bell", LocalDateTime.of(1985, Month.AUGUST, 18, 0, 1 ));
        User user3 = new User(3L, "Henry", "Ward", LocalDateTime.of(1997, Month.DECEMBER, 12, 7, 35 ));
        User user4 = new User(4L, "Ivy", "Cox", LocalDateTime.of(2012, Month.APRIL, 15, 1, 4 ));
        User user1 = new User(1L, "Jack", "Diaz", LocalDateTime.of(1998, Month.APRIL, 15, 1, 4 ));
        User user5 = new User(5L, "Kelly", "Hughes", LocalDateTime.of(2018, Month.DECEMBER, 7, 11, 28 ));

        customPriorityQueueWithComparator.add(user1);
        customPriorityQueueWithComparator.add(user2);
        customPriorityQueueWithComparator.add(user3);
        customPriorityQueueWithComparator.add(user4);
        customPriorityQueueWithComparator.add(user5);

        Long userId = customPriorityQueueWithComparator.peek().getId();

        assertEquals(1L, userId);

    }

    @Test
    void testPoll(){
        User user1 = new User(1L, "Jack", "Diaz", LocalDateTime.of(1998, Month.APRIL, 15, 1, 4 ));
        User user2 = new User(2L, "Grace", "Bell", LocalDateTime.of(1985, Month.AUGUST, 18, 0, 1 ));
        User user3 = new User(3L, "Henry", "Ward", LocalDateTime.of(1997, Month.DECEMBER, 12, 7, 35 ));
        User user4 = new User(4L, "Ivy", "Cox", LocalDateTime.of(2012, Month.APRIL, 15, 1, 4 ));
        User user5 = new User(5L, "Kelly", "Hughes", LocalDateTime.of(2018, Month.DECEMBER, 7, 11, 28 ));

        customPriorityQueue.add(user1);
        customPriorityQueue.add(user2);
        customPriorityQueue.add(user3);
        customPriorityQueue.add(user4);
        customPriorityQueue.add(user5);


        User youngestUser = customPriorityQueue.poll();
        assertNotNull(youngestUser);
        assertEquals(2018, youngestUser.getBirthDay().getYear());
        assertEquals(4, customPriorityQueue.getSize());
    }

    @Test
    void testGetSize(){

        User user1 = new User(1L, "Jack", "Diaz", LocalDateTime.of(1998, Month.APRIL, 15, 1, 4 ));
        User user2 = new User(2L, "Grace", "Bell", LocalDateTime.of(1985, Month.AUGUST, 18, 0, 1 ));

        customPriorityQueue.add(user1);
        customPriorityQueue.add(user2);

        assertEquals(2, customPriorityQueue.getSize());

    }


}
