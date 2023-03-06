package task.autoservice.service.impl;

import org.springframework.stereotype.Service;
import task.autoservice.model.Order.OrderStatus;
import task.autoservice.model.Overhaul;
import task.autoservice.repository.OverhaulRepository;
import task.autoservice.service.OverhaulService;

import java.util.List;

@Service
public class OverhaulServiceImpl extends GenericServiceImpl<Overhaul>
        implements OverhaulService {
    protected final OverhaulRepository repository;

    public OverhaulServiceImpl(OverhaulRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Overhaul create(Overhaul overhaul) {
        overhaul.setIsPaid(false);
        return super.create(overhaul);
    }

    @Override
    public List<Overhaul> getOverhaulsToPay(Long repairerId) {
        return repository.getOverhaulsByRepairerAndOrderStatus(repairerId, OrderStatus.PAID);
    }

    @Override
    public void markAllPaidById(List<Long> ids) {
        repository.markAllPaidById(ids);
    }
}
