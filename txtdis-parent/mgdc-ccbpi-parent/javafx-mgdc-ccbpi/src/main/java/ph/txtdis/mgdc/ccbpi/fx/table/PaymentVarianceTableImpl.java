package ph.txtdis.mgdc.ccbpi.fx.table;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.scene.control.TableColumn;
import ph.txtdis.dto.SalesItemVariance;
import ph.txtdis.mgdc.ccbpi.service.PaymentService;

@Scope("prototype")
@Component("paymentVarianceTable")
public class PaymentVarianceTableImpl //
		extends AbstractVarianceTable<PaymentService> //
		implements PaymentVarianceTable {

	@Override
	protected List<TableColumn<SalesItemVariance, ?>> addColumns() {
		super.addColumns();
		return asList(id, item, expected, returned, variance, value);
	}
}
