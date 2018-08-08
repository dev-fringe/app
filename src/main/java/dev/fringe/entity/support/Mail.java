package dev.fringe.entity.support;

import java.util.List;

import lombok.Data;

@Data
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;
    private List<Attachment> attachments;

    public Mail() {
    }

    public Mail(String from, String to, String subject, String content, List<Attachment> attachments) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}