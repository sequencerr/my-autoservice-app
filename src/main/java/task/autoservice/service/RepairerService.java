package task.autoservice.service;

import task.autoservice.model.Repairer;

import java.math.BigDecimal;

public interface RepairerService extends GenericService<Repairer> {
    BigDecimal calculateSalary(Long repairerId);
}
