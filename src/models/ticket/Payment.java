package models.ticket;

import java.time.LocalDate;

public class Payment {
    private String creditcardNumber;
    private String nameOnCard;
    private LocalDate expiredDate;

    public Payment(String creditcardNumber, String nameOnCard, String expiredDate) {
        this.creditcardNumber = creditcardNumber;
        this.nameOnCard = nameOnCard;
        this.expiredDate = LocalDate.parse(expiredDate);
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

}
