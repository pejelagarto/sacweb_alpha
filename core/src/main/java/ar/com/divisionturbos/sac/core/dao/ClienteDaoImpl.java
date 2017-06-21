package ar.com.divisionturbos.sac.core.dao;

import ar.com.divisionturbos.sac.core.model.ClienteEntity;
import org.appfuse.dao.jpa.GenericDaoJpa;
import org.springframework.stereotype.Service;

/**
 * Created by mzanetti on 05/06/17.
 */
@Service("ClienteDao")
public class ClienteDaoImpl extends GenericDaoJpa<ClienteEntity, Long>  implements ClienteDao {

    public ClienteDaoImpl() {
        super(ClienteEntity.class);
    }

}
