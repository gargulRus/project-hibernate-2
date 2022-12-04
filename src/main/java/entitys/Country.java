package entitys;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Country")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Country {

    @Column(name = "country_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name = "country")
    @NotNull
    private String country;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_update", nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date last_update;

}
