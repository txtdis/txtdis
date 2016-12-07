package ph.txtdis.fx.table;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.scene.control.TableColumn;
import ph.txtdis.dto.BillableDetail;
import ph.txtdis.fx.dialog.SalesOrderDialog;
import ph.txtdis.service.SalesOrderService;

@Scope("prototype")
@Component("salesOrderTable")
public class SalesOrderTableImpl //
		extends AbstractBeverageBillableTable<SalesOrderService, SalesOrderDialog> implements SalesOrderTable {

	@Override
	protected String bookedQtyColumnName() {
		if (service.isExTruck())
			return "Loaded";
		return super.bookedQtyColumnName();
	}

	@Override
	protected List<TableColumn<BillableDetail, ?>> columns() {
		List<TableColumn<BillableDetail, ?>> l = new ArrayList<>(super.columns());
		if (service.isExTruck())
			l.addAll(asList(bookedQtyInFractions, soldQtyInFractions, returnedQtyInFractions, qtyColumn()));
		else
			l.addAll(asList(price, qtyColumn(), subtotal));
		return l;
	}

	@Override
	protected String netQtyColumnName() {
		if (service.isExTruck())
			return "Variance";
		return super.netQtyColumnName();
	}

	@Override
	protected String returnedQtyColumnName() {
		if (service.isExTruck())
			return "Returned";
		return netQtyColumnName();
	}
}