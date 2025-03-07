package FondoRoyaleApplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skin_rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkinReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private int rewardId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "required_points", nullable = false)
    private int requiredPoints;

    @Column(name = "image_url", length = 255)
    private String imageUrl;
}