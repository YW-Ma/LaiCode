package com.interview.Google;

import java.util.*;
import java.util.Queue;
import java.util.Set;

public class LoggerRateLimiter {
    // HashMap version is easy
    // Practice with queue version

    public static void main(String[] args) {
        Logger logger = new Logger();
        System.out.println(logger.shouldPrintMessage(0, "a"));
        System.out.println(logger.shouldPrintMessage(0, "b"));
        System.out.println(logger.shouldPrintMessage(0, "c"));

        System.out.println(logger.shouldPrintMessage(0, "a"));
        System.out.println(logger.shouldPrintMessage(0, "b"));
        System.out.println(logger.shouldPrintMessage(0, "c"));

        System.out.println(logger.shouldPrintMessage(11, "a"));
        System.out.println(logger.shouldPrintMessage(11, "b"));
        System.out.println(logger.shouldPrintMessage(11, "c"));
    }
}
class Pair<K, V> {
    public K msg;
    public V time;

    public Pair(K msg, V time) {
        this.msg = msg;
        this.time = time;
    }
}

class Logger {
    private Queue<Pair<String, Integer>> queue;
    private Set<String> set;
    // plain queue doesn't support the "contains" operation, so we also need a set to track the content of queue

    public Logger() {
        queue = new ArrayDeque<>();
        set = new HashSet<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        // remove all expired msg  --> can also make queue become empty
        while (!queue.isEmpty()) {
            Pair<String, Integer> headMsg = queue.peek();
            if (timestamp - headMsg.time < 10) {
                break;
            }
            queue.poll();
            set.remove(headMsg.msg);
        }

        // check if there are any duplicate for the incoming msg (empty --> no dup)
        Pair<String, Integer> newMsg = new Pair<>(message, timestamp);
        if (set.contains(message)) {
            return false;
        } else {
            queue.offer(newMsg);
            set.add(message);
            return true;
        }
    }
}