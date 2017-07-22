package ph.txtdis.mgdc.gsm.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.mgdc.gsm.domain.StockTakeVarianceEntity;

@Repository("stockTakeVarianceRepository")
public interface StockTakeVarianceRepository //
		extends CrudRepository<StockTakeVarianceEntity, Long> {

	List<StockTakeVarianceEntity> findByCountDate( //
			@Param("countDate") LocalDate d);

	StockTakeVarianceEntity findFirstByCountDateLessThanEqualOrderByCountDateDesc( //
			@Param("countDate") LocalDate d);
}
