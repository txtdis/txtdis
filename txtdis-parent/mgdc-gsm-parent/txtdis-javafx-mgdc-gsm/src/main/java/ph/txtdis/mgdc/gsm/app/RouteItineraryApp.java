package ph.txtdis.mgdc.gsm.app;

import javafx.stage.Stage;
import ph.txtdis.fx.control.AppButtonImpl;
import ph.txtdis.mgdc.gsm.service.ItineraryService;

public interface RouteItineraryApp {

	AppButtonImpl addButton(Stage stage, ItineraryService service);
}