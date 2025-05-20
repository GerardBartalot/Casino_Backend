package FondoRoyaleApplication.entities;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private String username;
    private String password;
    private String dateOfBirth;
    private int fondocoins;
    private int experiencePoints;
    
    @Column(name = "registration_date", insertable = false, updatable = false)
    private Date registrationDate;
    
    @Lob
    @Column(columnDefinition = "LONGTEXT") 
    private String profilePicture;
    
    @Column(name = "last_daily_reward", nullable = true)
    private LocalDateTime lastDailyReward;
}