package ph.txtdis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.dto.StockTake;

@Service("stockTakeService")
public class StockTakeServiceImpl extends AbstractStockTakeService {

	@Autowired
	private InventoryReadOnlyService<StockTake> inventoryReadOnlyService;

	@Override
	public StockTake last() {
		try {
			return inventoryReadOnlyService.module("stockTake").getOne("/last");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}