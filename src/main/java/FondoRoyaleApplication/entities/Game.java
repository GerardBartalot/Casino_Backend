package FondoRoyaleApplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "game_name", nullable = false)
    private String gameName;

    @Column(name = "game_description")
    private String gameDescription;

    @Column(name = "level_unlock")
    private int levelUnlock;

    @Column(name = "game_img")
    private String gameImg;
    
    @Column(name = "game_rules")
    private String gameRules;
}