package ph.txtdis.dyvek.service.server;

import java.util.List;

import ph.txtdis.dyvek.domain.CustomerEntity;
import ph.txtdis.dyvek.model.Customer;
import ph.txtdis.service.SavedNameListService;

public interface CustomerService //
		extends SavedNameListService<Customer> {

	List<Customer> findAllBanks();

	List<Customer> findAllTradingClients();

	List<Customer> findAllTradingPartners();

	List<Customer> findAllTruckingClients();

	List<Customer> findAllVendors();

	CustomerEntity findEntityByName(String name);
}
