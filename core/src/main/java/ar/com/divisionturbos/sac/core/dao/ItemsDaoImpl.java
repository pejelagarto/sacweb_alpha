package ar.com.divisionturbos.sac.core.dao;

import ar.com.divisionturbos.sac.core.model.ItemsEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzanetti on 26/05/17.
 */
@Service("DetalleotDaoImpl")
public class ItemsDaoImpl extends GenericDaoPaginatedImpl<ItemsEntity, Long> implements ItemsDao {

    @Override
    public List<Predicate> aplicarFiltros(ItemsEntity search, CriteriaBuilder builder, Root<ItemsEntity> entityRoot) {

        List<Predicate> filtros = new ArrayList<>();

/*
        if (search.getOtId()!=null){
            Predicate otId = builder.equal(
                    entityRoot.get("otId").as(Long.class),
                    search.getOtId());
            filtros.add(otId);
        }
*/

        if (search.getNrot()!=null){
            Predicate nrot = builder.equal(
                    entityRoot.get("nrot").as(Long.class),
                    search.getNrot());
            filtros.add(nrot);
        }

        if (search.getItem()!=null && !"".equals(search.getItem())){
            Predicate item = builder.equal(
                    entityRoot.get("item").as(Long.class),
                    search.getItem());
            filtros.add(item);
        }

        if (search.getNrcertif()!=null && !"".equals(search.getNrcertif())){
            Predicate nroCertificado = builder.equal(
                    entityRoot.get("nrcertif").as(String.class),
                    search.getNrcertif());
            filtros.add(nroCertificado);
        }

        if (search.getSn()!=null && !"".equals(search.getSn())){
            Predicate sn = builder.equal(
                    entityRoot.get("sn").as(String.class),
                    search.getSn());
            filtros.add(sn);
        }




        return filtros;

    }
}
