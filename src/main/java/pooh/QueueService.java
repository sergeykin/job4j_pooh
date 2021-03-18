package pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String nameQueue = req.gettype();
        if (req.getmethod().equals("POST")) {
            queue.putIfAbsent(nameQueue, new ConcurrentLinkedQueue<>());
            queue.get(nameQueue).add(req.getdata());
            return new Resp(String.format("Added in %s with type %s client %s: %s", req.getmode(), req.gettype(), req.getClient(), req.getdata()), Resp.OK);
        } else {
            String text = queue.getOrDefault(nameQueue, new ConcurrentLinkedQueue<>()).poll();
            return new Resp(text == null ? "There is nothing for you" : text, Resp.OK);
        }
    }
}
