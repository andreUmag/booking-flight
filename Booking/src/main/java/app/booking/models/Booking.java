package app.booking.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Booking")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String passengerName;
}
