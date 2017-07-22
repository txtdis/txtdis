package ph.txtdis.mgdc.ccbpi.app;

import static ph.txtdis.type.Type.CURRENCY;
import static ph.txtdis.type.Type.QUANTITY;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.scene.layout.HBox;
import ph.txtdis.app.AbstractTotaledTableApp;
import ph.txtdis.fx.control.AppFieldImpl;
import ph.txtdis.type.Type;

@Scope("prototype")
@Component("totaledVarianceTableApp")
public class TotaledVarianceTableAppImpl<T> //
		extends AbstractTotaledTableApp<T> {

	@Override
	public HBox addTotalDisplays(int count) {
		totalDisplays = new ArrayList<>();
		for (int i = 1; i <= count; i++)
			buildTotalDisplays(i == count);
		return box.forTableTotals(totalDisplays);
	}

	private void buildTotalDisplays(boolean isLast) {
		Type type = isLast ? CURRENCY : QUANTITY;
		totalDisplays.add(new AppFieldImpl<BigDecimal>().readOnly().build(type));
	}
}
