package S05T2Michel.DiceGame.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_player")
    private int playerId;

    @Column(name = "player_name", length = 45, nullable = false)
    private String playerName;

    @Column(name = "registration_date", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
}