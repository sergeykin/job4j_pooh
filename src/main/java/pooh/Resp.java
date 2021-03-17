package pooh;


public class Resp {
    public static final int OK = 200;
    private final String text;
    private final int status;

    public Resp(String text, int status) {
        this.text = System.lineSeparator() + text;
        this.status = status;
    }

    public String gettext() {
        return text;
    }

    public int getstatus() {
        return status;
    }
}
