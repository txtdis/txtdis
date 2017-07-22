package ph.txtdis.mgdc.gsm.service.server;

import java.util.List;

import ph.txtdis.mgdc.gsm.dto.Customer;

public interface CreditedAndDiscountedCustomerService //
		extends CustomerValidationService, Imported {

	List<Customer> findAllByScheduledRouteAndGoodCreditStanding(Long id);

	List<Customer> findAllOnAction();

	Customer findDifferentVisitFrequencyAndSchedule();

	Customer findDifferentWeeksOneAndFiveVisitSchedule();

	Customer findEmployeeIdAndName(Long id);

	Customer findIncorrectBarangayAddress();

	Customer findIncorrectCityAddress();

	Customer findIncorrectProvincialAddress();

	Customer findNoContactDetails();

	Customer findNoDesignation();

	Customer findNoMobileNo();

	Customer findNoStreetAddress();

	Customer findNoSurname();

	Customer findNoVisitSchedule();

	Customer getVendor();

	Customer saveToEdms(Customer c) throws Exception;
}
