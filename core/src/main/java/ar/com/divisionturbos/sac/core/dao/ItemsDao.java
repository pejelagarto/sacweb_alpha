package ar.com.divisionturbos.sac.core.dao;

import ar.com.divisionturbos.sac.core.model.ItemsEntity;


import java.util.List;

/**
 * Created by mzanetti on 26/05/17.
 */
public interface ItemsDao extends GenericDaoPaginated<ItemsEntity, Long> {

    public List<ItemsEntity> findPaginated(ItemsEntity search, Integer startPosition,
                                        Integer maxResult, String sortFields, String sortDirections);

}
