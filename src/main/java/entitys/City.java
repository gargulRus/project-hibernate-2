package entitys;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long city_id;

    @Column(name = "city")
    @NotNull
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_update", nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date last_update;
}
