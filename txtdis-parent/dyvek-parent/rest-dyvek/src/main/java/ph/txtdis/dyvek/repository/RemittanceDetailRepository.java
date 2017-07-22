package ph.txtdis.dyvek.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.dyvek.domain.RemittanceDetailEntity;

@Repository("remittanceDetailRepository")
public interface RemittanceDetailRepository //
		extends CrudRepository<RemittanceDetailEntity, Long> {

	List<RemittanceDetailEntity> findByBillingId( //
			@Param("billingId") Long id);

	RemittanceDetailEntity findFirstByBillingIdOrderByIdDesc( //
			@Param("id") Long id);
}
