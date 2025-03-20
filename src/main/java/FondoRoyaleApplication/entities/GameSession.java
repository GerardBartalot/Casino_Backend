package FondoRoyaleApplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "session_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime sessionDate = LocalDateTime.now();

    @Column(name = "result", length = 50)
    private String result;

    @Column(name = "experience_earned")
    private int experienceEarned;

    @Column(name = "fondocoins_spent")
    private int fondocoinsSpent;

    @Column(name = "fondocoins_earned")
    private int fondocoinsEarned;
}