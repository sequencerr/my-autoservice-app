package task.autoservice.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import task.autoservice.model.Overhaul;
import task.autoservice.model.Repairer;
import task.autoservice.repository.RepairerRepository;
import task.autoservice.service.OverhaulService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepairerServiceTest {
    private static final long REPAIRER_ID = 1L;
    private static final BigDecimal PRICE_OVERHAUL_1 = BigDecimal.valueOf(259);
    private static final BigDecimal PRICE_OVERHAUL_2 = BigDecimal.valueOf(357);
    private static final BigDecimal PRICE_EXPECTED = BigDecimal.valueOf(246.4);
    private RepairerServiceImpl repairerService;
    private RepairerRepository repairerRepository;
    private OverhaulService overhaulService;

    @BeforeEach
    void setUp() {
        repairerRepository = Mockito.mock(RepairerRepository.class);
        overhaulService = Mockito.mock(OverhaulService.class);
        repairerService = new RepairerServiceImpl(repairerRepository, overhaulService);
    }

    @Test
    void calculateSalary_twoOverhaulsCalculateRepairersSalary_ok() {
        Repairer repairer = new Repairer();
        repairer.setId(REPAIRER_ID);
        Overhaul overhaul = new Overhaul();
        overhaul.setPrice(PRICE_OVERHAUL_1);
        Overhaul overhaul2 = new Overhaul();
        overhaul2.setPrice(PRICE_OVERHAUL_2);
        Mockito.when(overhaulService.getOverhaulsToPay(REPAIRER_ID))
                .thenReturn(List.of(overhaul, overhaul2));
        assertEquals(
                PRICE_EXPECTED,
                repairerService.calculateSalary(REPAIRER_ID),
                "Expected to be calculated correctly");
    }
}
