package ar.com.divisionturbos.sac.core.service;

import ar.com.divisionturbos.sac.core.dao.ItemsDao;
import ar.com.divisionturbos.sac.core.model.ItemsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mzanetti on 02/06/17.
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ItemsDao itemsDao;

    @Transactional
    public List<ItemsEntity> getAll() {

        List<ItemsEntity> detalleots = itemsDao.getAll();
        return detalleots;
    }

    @Override
    @Transactional
    public ItemsEntity save(ItemsEntity detalleOtEntity) {
        return itemsDao.save(detalleOtEntity);
    }

    @Override
    @Transactional
    public List<ItemsEntity> findPaginated(ItemsEntity search, Integer startPosition, Integer maxResult, String
            sortFields, String sortDirections) {
        return itemsDao.findPaginated(search, startPosition, maxResult, sortFields, sortDirections);
    }
}
