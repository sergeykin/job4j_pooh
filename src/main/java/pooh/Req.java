package pooh;

public class Req {
    private final String text;
    private final String mode;
    private final String method;
    private final String type;
    private final String data;
    private final String client;

    public Req(String text) {
        this.text = text;
        this.method = text.lines().findFirst().get().split(" ")[0];
        String[] strings = text.lines().findFirst().get().split(" ")[1].split("/");
        if (strings.length > 1) {
            this.mode = strings[1];
        }else {
            this.mode = "";
        }

        if (strings.length > 2) {
            this.type = strings[2];
        }else {
            this.type = "";
        }
        if (strings.length > 3) {
            this.client = strings[3];
        }else {
            this.client = "";
        }

        this.data = text.lines().dropWhile(s -> !s.isEmpty()).skip(1).findFirst().orElse("");
    }

    public String valueOf(String key) {
        return null;
    }

    public String getmode() {
        return mode;
    }

    public String getmethod() {
        return method;
    }

    public String gettype() {
        return type;
    }

    public String getdata() {
        return data;
    }
    public String getClient() {
        return client;
    }
}