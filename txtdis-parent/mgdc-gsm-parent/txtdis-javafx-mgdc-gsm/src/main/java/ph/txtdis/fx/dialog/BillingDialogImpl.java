package ph.txtdis.fx.dialog;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ph.txtdis.dto.BillableDetail;
import ph.txtdis.service.BillingService;

@Scope("prototype")
@Component("billingDialog")
public class BillingDialogImpl extends AbstractAllItemInCasesAndBottlesInputDialog<BillingService, BillableDetail>
		implements BillingDialog {
}
