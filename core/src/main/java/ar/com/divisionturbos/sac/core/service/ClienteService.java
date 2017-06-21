package ar.com.divisionturbos.sac.core.service;

import ar.com.divisionturbos.sac.core.model.ClienteEntity;

import java.util.List;

/**
 * Created by mzanetti on 05/06/17.
 */
public interface ClienteService {

    ClienteEntity save (ClienteEntity clienteEntity);

    List<ClienteEntity> getAll ();

    ClienteEntity get(long id);

}
