package study.socket.common;


import java.time.ZonedDateTime;

public class Message {
    private final String text;
    private ZonedDateTime sentAT;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ZonedDateTime getSentAT() {
        return sentAT;
    }

    public void setSentAT(ZonedDateTime sentAT) {
        this.sentAT = sentAT;
    }
}
