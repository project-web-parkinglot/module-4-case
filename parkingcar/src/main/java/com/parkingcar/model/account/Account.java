package com.parkingcar.model.account;

import com.parkingcar.model.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

//    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
//    List<Notification> notificationList;


}
