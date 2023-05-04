package model;

import lombok.Data;

@Data
public class TransactionHistory {
    private Integer transactionId;
    private Integer senderId;
    private Integer receiverId;
    private Integer amount;
    private String time;
    private String date;
}
