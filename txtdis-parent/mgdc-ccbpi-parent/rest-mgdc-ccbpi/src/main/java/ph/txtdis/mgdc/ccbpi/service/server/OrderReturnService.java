package ph.txtdis.mgdc.ccbpi.service.server;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.dto.Billable;
import ph.txtdis.mgdc.ccbpi.domain.BillableDetailEntity;
import ph.txtdis.mgdc.ccbpi.domain.BomEntity;

public interface OrderReturnService
		extends QtyPerItemService, QtyToBillableDetailsItemMappingService, SpunSavedBillableService {

	Billable find(LocalDate date, Long customerVendorId, Long count);

	List<BillableDetailEntity> getDetailEntityList(String itemVendorNo, String route, LocalDate start, LocalDate end);

	List<BomEntity> getOrderNoAndCustomerAndRouteGroupedBomList();

	List<BomEntity> getBomList(LocalDate start, LocalDate end);
}
