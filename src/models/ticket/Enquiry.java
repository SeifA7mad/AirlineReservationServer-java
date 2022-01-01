package models.ticket;

import java.time.LocalDate;

public class Enquiry {
    private String title;
    private String description;

    public Enquiry(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
