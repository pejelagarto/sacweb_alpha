package ar.com.divisionturbos.sac.core.service;

import ar.com.divisionturbos.sac.core.dao.ClienteDao;
import ar.com.divisionturbos.sac.core.model.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mzanetti on 05/06/17.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDao clienteDao;

    @Override
    @Transactional
    public ClienteEntity save(ClienteEntity clienteEntity) {

        /*Hacer las correspondientes validaciones*/
        return clienteDao.save(clienteEntity);
    }


    @Transactional
    public List<ClienteEntity> getAll() {
        return clienteDao.getAll();

    }

    @Override
    public ClienteEntity get(long id) {
        return clienteDao.get(id);
    }
}
