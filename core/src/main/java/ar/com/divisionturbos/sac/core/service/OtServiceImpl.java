package ar.com.divisionturbos.sac.core.service;

import ar.com.divisionturbos.sac.core.dao.OtDao;
import ar.com.divisionturbos.sac.core.model.OtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mzanetti on 08/06/17.
 */
@Service
public class OtServiceImpl implements OtService {


    @Autowired
    private OtDao otDao;


    @Override
    @Transactional
    public OtEntity save(OtEntity otEntity) {
        return otDao.save(otEntity);
    }

    @Override
    @Transactional
    public List<OtEntity> getAll() {
        return otDao.getAll();
    }

    @Override
    @Transactional
    public OtEntity get(long id) {
        return otDao.get(id);
    }

    @Override
    @Transactional
    public List<OtEntity> search(String search){
        return otDao.search(search);
    }


    @Transactional
    public List<OtEntity> findFilter(OtEntity otEntity){
        return otDao.findPaginated(otEntity,null,null,null,null);
    }



}
