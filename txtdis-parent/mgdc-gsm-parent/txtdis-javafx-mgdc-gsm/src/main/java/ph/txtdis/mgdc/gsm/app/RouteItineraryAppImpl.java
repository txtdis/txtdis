package ph.txtdis.mgdc.gsm.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.stage.Stage;
import ph.txtdis.dto.Route;
import ph.txtdis.fx.control.AppButtonImpl;
import ph.txtdis.fx.dialog.MessageDialog;
import ph.txtdis.mgdc.gsm.dto.Customer;
import ph.txtdis.mgdc.gsm.fx.dialog.ExTruckRouteItineraryDialog;
import ph.txtdis.mgdc.gsm.fx.table.RouteItineraryTable;
import ph.txtdis.mgdc.gsm.service.ItineraryService;

@Scope("prototype")
@Component("routeItineraryApp")
public class RouteItineraryAppImpl //
		implements RouteItineraryApp {

	@Autowired
	private AppButtonImpl routeItineraryButton;

	@Autowired
	private ExTruckRouteItineraryDialog routeItineraryDialog;

	@Autowired
	private MessageDialog dialog;

	@Autowired
	private RouteItineraryTable routeItineraryTable;

	private ItineraryService itineraryService;

	private Stage stage;

	@Override
	public AppButtonImpl addButton(Stage stage, ItineraryService service) {
		this.itineraryService = service;
		routeItineraryButton.icon("route").tooltip("DRoAR...").build();
		routeItineraryButton.onAction(e -> generateRouteItinerary());
		return routeItineraryButton;
	}

	private void generateRouteItinerary() {
		Route r = getSelectedRoute();
		generateExcelRouteItinerary(itineraryService.listCustomersByScheduledRouteAndGoodCreditStanding(r));
	}

	private Route getSelectedRoute() {
		routeItineraryDialog.addParent(stage).start();
		return routeItineraryDialog.getRoute();
	}

	@SuppressWarnings("unchecked")
	private void generateExcelRouteItinerary(List<Customer> customers) {
		try {
			if (customers == null || customers.isEmpty())
				return;
			routeItineraryTable.build();
			routeItineraryTable.items(customers);
			itineraryService.saveAsExcel(routeItineraryTable);
		} catch (Exception e) {
			e.printStackTrace();
			dialog.show(e).addParent(stage).start();
		}
	}
}
