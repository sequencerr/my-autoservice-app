package task.autoservice.service;

import task.autoservice.model.Overhaul;

import java.util.List;

public interface OverhaulService extends GenericService<Overhaul> {
    List<Overhaul> getOverhaulsToPay(Long repairerId);

    void markAllPaidById(List<Long> ids);
}
