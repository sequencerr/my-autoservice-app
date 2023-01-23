package task.autoservice.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.CarService;
import task.autoservice.model.Repairer;
import task.autoservice.service.CarServiceService;
import task.autoservice.service.RepairerService;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class RepairerServiceImpl extends GenericServiceImpl<Repairer> implements RepairerService {
    private static final BigDecimal PERCENTAGE_TO_PAY = BigDecimal.valueOf(0.4);
    private static final BiFunction<BigDecimal, CarService, BigDecimal> REDUCER =
            (a, c) -> a.add(c.getPrice().multiply(PERCENTAGE_TO_PAY));
    private final CarServiceService carServiceService;

    public RepairerServiceImpl(
            JpaRepository<Repairer, Long> repository,
            CarServiceService carServiceService
    ) {
        super(repository);
        this.carServiceService = carServiceService;
    }

    @Override
    @Transactional
    public BigDecimal calculateSalary(Long repairerId) {
        List<CarService> services = carServiceService.getServiceToPay(repairerId);
        carServiceService.markAllPaidById(services.stream().map(CarService::getId).toList());
        return services.stream().reduce(BigDecimal.ZERO, REDUCER, BigDecimal::add);
    }
}
