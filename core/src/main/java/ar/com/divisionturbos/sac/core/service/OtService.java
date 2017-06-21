package ar.com.divisionturbos.sac.core.service;

import ar.com.divisionturbos.sac.core.model.ClienteEntity;
import ar.com.divisionturbos.sac.core.model.OtEntity;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by mzanetti on 08/06/17.
 */
public interface OtService {


    OtEntity save (OtEntity otEntity);

    List<OtEntity> getAll ();

    OtEntity get(long id);

    List<OtEntity> search(String search);

    List<OtEntity> findFilter (OtEntity otEntity);


}
