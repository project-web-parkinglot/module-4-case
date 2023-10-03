package com.parkingcar.model.notification;

import com.parkingcar.model.account.Account;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,name = "content_notification")
    private String content;

    @Column(nullable = false, name = "date_notifaction")
    private LocalDate dateNotification;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Notification() {
    }

    public Notification(int id, String content, LocalDate dateNotification, Account account) {
        this.id = id;
        this.content = content;
        this.dateNotification = dateNotification;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(LocalDate dateNotification) {
        this.dateNotification = dateNotification;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
