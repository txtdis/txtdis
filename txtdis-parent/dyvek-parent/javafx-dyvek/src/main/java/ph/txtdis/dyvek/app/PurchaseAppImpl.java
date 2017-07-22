package ph.txtdis.dyvek.app;

import static ph.txtdis.type.Type.TEXT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.scene.Node;
import ph.txtdis.dyvek.fx.table.OrderTable;
import ph.txtdis.dyvek.service.PurchaseService;
import ph.txtdis.fx.control.AppButton;
import ph.txtdis.fx.control.AppButtonImpl;
import ph.txtdis.fx.control.AppFieldImpl;
import ph.txtdis.fx.control.LocalDatePicker;
import ph.txtdis.info.Information;

@Scope("prototype")
@Component("purchaseApp")
public class PurchaseAppImpl //
		extends AbstractTabledOpenListedOrderApp<OpenPurchaseListApp, SearchedPurchaseListApp, OrderTable, PurchaseService> //
		implements PurchaseApp {

	@Autowired
	private AppButton closeButton;

	@Autowired
	private AppFieldImpl<LocalDate> endDateDisplay;

	@Autowired
	private LocalDatePicker endDatePicker;

	@Override
	protected List<AppButtonImpl> addButtons() {
		List<AppButtonImpl> b = new ArrayList<>(super.addButtons());
		b.add(closeButton.icon("deactivate").tooltip("Close P/O").build());
		return b;
	}

	@Override
	protected List<Node> creationNodes() {
		List<Node> l = new ArrayList<>(super.creationNodes());
		l.addAll(closureNodes());
		return l;
	}

	@Override
	protected void firstGridLine() {
		comboAndInputGridNodes("Supplier", customerCombo.width(310), "P/O No.", orderNoInput.width(110).build(TEXT), 0, 3);
		dateGridNodes("Date", orderDateDisplay, orderDatePicker, 6, 0, 2);
		dateGridNodes("till", endDateDisplay, endDatePicker, 9, 0, 2);
	}

	@Override
	protected void secondGridLine() {
		super.secondGridLine();
		qtyInKgDisplayGridNodes("Balance", balanceDisplay, 8, 1, 2);
	}

	@Override
	public void refresh() {
		super.refresh();
		customerCombo.items(service.listCustomers());
		endDatePicker.setValue(service.getEndDate());
		endDateDisplay.setValue(service.getEndDate());
		refreshClosureNodes();
	}

	@Override
	protected void setBindings() {
		super.setBindings();
		closeButton.disableIf(isNew()//
				.or(closedOnDisplay.isNotEmpty()));
		setEndDateBindings();
		itemCombo.disableIf(endDatePicker.isEmpty());
	}

	private void setEndDateBindings() {
		endDatePicker.disableIf(orderDatePicker.disabledProperty());
		endDatePicker.showIf(isNew());
		endDateDisplay.showIf(endDatePicker.isNotVisible());
	}

	@Override
	protected void setListeners() {
		super.setListeners();
		endDatePicker.onAction(e -> setEndDateUponValidation(endDatePicker.getValue()));
		closeButton.onAction(e -> closeOrder());
	}

	private void setEndDateUponValidation(LocalDate end) {
		if (service.isNew() && end != null)
			try {
				service.setEndDateUponValidation(end);
			} catch (Exception e) {
				handleError(e, endDatePicker);
			}
	}

	private void closeOrder() {
		try {
			service.close();
		} catch (Information i) {
			dialog.showInfo("Successfully closed:\n" + service.getSavingInfo()).addParent(this).start();
		} catch (Exception e) {
			showErrorDialog(e);
		} finally {
			refresh();
		}
	}
}
