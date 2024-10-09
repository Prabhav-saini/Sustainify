package sustainifyapp.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Column(updatable = false)
    private LocalDateTime dateLogged;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private int amount;
    private String amountUnit;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Activity(Long id, String type, LocalDateTime dateLogged, int amount, String amountUnit, User user) {
        this.id = id;
        this.type = type;
        this.dateLogged = dateLogged;
        this.amount = amount;
        this.amountUnit = amountUnit;
        this.user = user;
    }

    public Activity() {
        super();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateLogged() {
        return dateLogged;
    }

    public void setDateLogged(LocalDateTime dateLogged) {
        this.dateLogged = dateLogged;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", dateLogged=" + dateLogged +
                ", amount=" + amount +
                ", amountUnit='" + amountUnit + '\'' +
                ", user=" + user +
                '}';
    }
}
