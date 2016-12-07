package ph.txtdis.fx.table;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.scene.control.TableColumn;
import ph.txtdis.dto.BillableDetail;
import ph.txtdis.fx.dialog.ReceivingReportDialog;
import ph.txtdis.service.ReceivingReportService;

@Scope("prototype")
@Component("receivingReportTable")
public class ReceivingReportTableImpl //
		extends AbstractBeverageBillableTable<ReceivingReportService, ReceivingReportDialog>
		implements ReceivingReportTable {

	@Override
	protected TableColumn<BillableDetail, ?> qtyColumn() {
		return returnedQtyInFractions;
	}

	@Override
	protected List<TableColumn<BillableDetail, ?>> columns() {
		List<TableColumn<BillableDetail, ?>> l = new ArrayList<>(super.columns());
		l.add(qtyColumn());
		return l;
	}
}