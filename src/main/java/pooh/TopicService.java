package pooh;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class TopicService implements Service {
    final ConcurrentHashMap<String, LinkedList<String>> queue = new ConcurrentHashMap<>();
    final Map<String, Function<Req, Resp>> processor = new HashMap<>();
    {
        processor.put("POST", (req) -> {
            queue.compute(req.gettype()+"_client_"+req.getClient(), (k, data) -> {
                if (data == null) {
                    data = new LinkedList<>();
                }
                data.add(req.getdata());
                return data;
            });
            return new Resp(String.format("Added in %s with type %s client %s: %s", req.getmode(), req.gettype(), req.getClient(),req.getdata()), Resp.OK);
        });

        processor.put("GET", (req) -> {
            String[] res = {null};
            queue.compute(req.gettype()+"_client_"+req.getClient(), (k, data) -> {
                if (data != null) {
                    res[0] = data.poll();
                }
                return data;
            });
            return new Resp(res[0] == null ? "There is nothing for you" : res[0], Resp.OK);
        });
    }
    @Override
    public Resp process(Req req) {
        return processor.get(req.getmethod()).apply(req);
    }
}
