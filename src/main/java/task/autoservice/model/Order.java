package task.autoservice.model;

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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal totalPrice;
    private LocalDateTime acceptationDate;
    private LocalDateTime completionDate;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;
    @OneToOne(fetch = FetchType.LAZY)
    private Car car;
    @ManyToMany
    private List<Service> services;
    @ManyToMany
    private List<Goods> goods;

    enum OrderStatus {
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
                + ", totalPrice=" + totalPrice
                + ", acceptationDate=" + acceptationDate
                + ", completionDate=" + completionDate
                + ", status=" + status
                + ", car=" + car
                + ", services=" + services
                + ", goods=" + goods
                + '}';
    }
}
