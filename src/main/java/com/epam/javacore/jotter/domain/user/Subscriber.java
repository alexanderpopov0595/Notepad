package com.epam.javacore.jotter.domain.user;

import com.epam.javacore.jotter.enums.Status;
import javax.persistence.*;

@Entity
@Table(name="subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_subscriber", referencedColumnName = "id")
    private User subscriber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSubscriber(User subscriber){
        this.subscriber=subscriber;
    }

    public User getSubscriber(){
        return subscriber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
