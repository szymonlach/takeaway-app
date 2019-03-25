package pl.lach.spring.takeawayapp.message;

import java.util.Objects;

public class Message {

    private String title;
    private String description;

    public Message(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(getTitle(), message.getTitle()) &&
                Objects.equals(getDescription(), message.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTitle(), getDescription());
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
