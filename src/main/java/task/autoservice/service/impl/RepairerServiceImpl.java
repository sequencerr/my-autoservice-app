package task.autoservice.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Overhaul;
import task.autoservice.model.Repairer;
import task.autoservice.service.OverhaulService;
import task.autoservice.service.RepairerService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RepairerServiceImpl extends GenericServiceImpl<Repairer> implements RepairerService {
    private static final BigDecimal PERCENTAGE_TO_PAY = BigDecimal.valueOf(0.4);
    private final OverhaulService overhaulService;

    public RepairerServiceImpl(
            JpaRepository<Repairer, Long> repository,
            OverhaulService overhaulService
    ) {
        super(repository);
        this.overhaulService = overhaulService;
    }

    @Override
    @Transactional
    public BigDecimal calculateSalary(Long repairerId) {
        List<Overhaul> overhauls = overhaulService.getOverhaulsToPay(repairerId);
        overhaulService.markAllPaidById(overhauls.stream().map(Overhaul::getId).toList());
        return overhauls.stream().reduce(BigDecimal.ZERO,
                (a, c) -> a.add(c.getPrice().multiply(PERCENTAGE_TO_PAY)), BigDecimal::add);
    }
}
