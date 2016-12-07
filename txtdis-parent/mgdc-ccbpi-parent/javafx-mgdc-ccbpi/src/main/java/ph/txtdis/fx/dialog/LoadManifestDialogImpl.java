package ph.txtdis.fx.dialog;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ph.txtdis.dto.BillableDetail;
import ph.txtdis.service.DeliveryListService;

@Scope("prototype")
@Component("deliveryListDialog")
public class LoadManifestDialogImpl //
		extends AbstractAllItemInCasesAndBottlesInputDialog<DeliveryListService, BillableDetail>
		implements DeliveryListDialog {
}
