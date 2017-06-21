package ar.com.divisionturbos.sac.core.service;

import ar.com.divisionturbos.sac.core.model.ItemsEntity;

import java.util.List;

/**
 * Created by mzanetti on 26/05/17.
 */
public interface ItemsService {

    List<ItemsEntity> getAll();

    ItemsEntity save (ItemsEntity detalleOtEntity);

    List<ItemsEntity> findPaginated(ItemsEntity search, Integer startPosition,
                                           Integer maxResult, String sortFields, String sortDirections);

}
