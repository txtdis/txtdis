package ph.txtdis.mgdc.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.dto.Account;
import ph.txtdis.dto.Route;
import ph.txtdis.dto.Truck;
import ph.txtdis.info.Information;
import ph.txtdis.service.ListedAndResetableService;
import ph.txtdis.service.TitleAndHeaderAndIconAndModuleNamedAndTypeMappedService;
import ph.txtdis.service.UniqueNamedService;

public interface RouteService //
		extends ListedAndResetableService<Route>, TitleAndHeaderAndIconAndModuleNamedAndTypeMappedService, UniqueNamedService<Route> {

	Route findById(String id) throws Exception;

	Route findByIds(String[] ids) throws Exception;

	List<Account> getSellerHistory();

	List<Truck> getTrucks();

	List<String> listNames();

	List<String> listUsers() throws Exception;

	Account save(String seller, LocalDate date) throws Information, Exception;

	Route save(String name) throws Information, Exception;
}