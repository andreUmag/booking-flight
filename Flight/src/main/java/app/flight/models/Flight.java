package app.flight.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Fligh")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
}