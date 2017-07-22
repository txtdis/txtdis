package ph.txtdis.mgdc.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ph.txtdis.domain.AbstractDecisionNeededValidatedCreatedKeyedEntity;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractCustomerDiscountEntity //
		extends AbstractDecisionNeededValidatedCreatedKeyedEntity<Long> {

	private static final long serialVersionUID = -5451725270886595800L;

	@Column(name = "percent", precision = 5, scale = 2)
	private BigDecimal value;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
}
