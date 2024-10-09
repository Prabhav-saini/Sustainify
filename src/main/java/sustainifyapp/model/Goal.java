package sustainifyapp.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String targetDays;
    private Boolean achieved;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Goal() {
        super();
    }

    public Goal(Long id, String description, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime startDate, LocalDateTime endDate, String targetDays, Boolean achieved, User user) {
        this.id = id;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetDays = targetDays;
        this.achieved = achieved;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getAchieved() {
        return achieved;
    }

    public void setAchieved(Boolean achieved) {
        this.achieved = achieved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", achieved=" + achieved +
                ", user=" + user +
                '}';
    }

    public String getTargetDays() {
        return targetDays;
    }

    public void setTargetDays(String targetDays) {
        this.targetDays = targetDays;
    }
}
