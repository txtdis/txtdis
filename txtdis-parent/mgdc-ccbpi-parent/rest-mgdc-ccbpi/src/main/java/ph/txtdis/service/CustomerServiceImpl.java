package ph.txtdis.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ph.txtdis.util.DateTimeUtils;

@Service("customerService")
public class CustomerServiceImpl extends AbstractCustomerService {

	@Value("${go.live}")
	private String goLive;

	@Override
	public LocalDate goLiveDate() {
		return DateTimeUtils.toDate(goLive);
	}
}