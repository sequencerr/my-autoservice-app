package task.autoservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends IdentifiableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal totalPriceForClient;
    private LocalDateTime acceptationDate;
    private LocalDateTime completionDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToOne(fetch = FetchType.LAZY)
    private Car car;
    @ManyToMany
    @Column(name = "car_services")
    private List<CarService> carServices;
    @ManyToMany
    @Column(name = "car_parts")
    private List<CarPart> carParts;

    public enum OrderStatus {
        ACCEPTED,
        IN_PROGRESS,
        COMPLETED_SUCCESSFULLY,
        COMPLETED_UNSUCCESSFULLY,
        PAID
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", totalPrice=" + totalPriceForClient
                + ", acceptationDate=" + acceptationDate
                + ", completionDate=" + completionDate
                + ", status=" + status
                + ", car=" + car
                + ", carServices=" + carServices
                + ", carParts=" + carParts
                + '}';
    }
}
