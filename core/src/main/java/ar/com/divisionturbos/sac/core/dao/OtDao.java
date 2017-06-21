package ar.com.divisionturbos.sac.core.dao;

import ar.com.divisionturbos.sac.core.model.OtEntity;
import org.springframework.dao.DataAccessException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by mzanetti on 08/06/17.
 */
public interface OtDao extends GenericDaoPaginated<OtEntity,Long>{

    public List<OtEntity> findPaginated(OtEntity search, Integer startPosition,
                                 Integer maxResult, String sortFields, String sortDirections);

}
