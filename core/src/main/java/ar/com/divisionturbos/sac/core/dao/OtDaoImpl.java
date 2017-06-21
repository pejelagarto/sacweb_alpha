package ar.com.divisionturbos.sac.core.dao;

import ar.com.divisionturbos.sac.core.model.OtEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mzanetti on 08/06/17.
 */
@Service
public class OtDaoImpl extends GenericDaoPaginatedImpl<OtEntity, Long> implements OtDao {

    public List<Predicate> aplicarFiltros(OtEntity search,
                                          CriteriaBuilder builder, Root<OtEntity> entityRoot) {

        List<Predicate> filtros = new ArrayList<Predicate>();

        if (search.getNrot() != null && !"".equals(search.getNrot())) {
            Predicate nrot = builder.equal(
                    entityRoot.get("nrot").as(Long.class),
                    search.getNrot());
            filtros.add(nrot);
        }


        if (search.getEstado() != null && !"".equals(search.getEstado().trim())) {
            Predicate estacionCodigo = builder.equal(
                    builder.upper(entityRoot.get("estado").as(String.class)),
                    search.getEstado().trim());
            filtros.add(estacionCodigo);
        }

        if (search.getCliente() != null & search.getCliente().getClienteId() != null) {
            Predicate cuitEmp = builder.equal(
                    entityRoot.get("cliente").get("clienteId").as(Long.class),
                    search.getCliente().getClienteId());
            filtros.add(cuitEmp);
        }

        if (search.getFechaCreacion() != null) {
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(search.getFechaCreacion());
            Predicate fechaEstado = builder.and(
                    builder.equal(builder.function("year", Integer.class, entityRoot.get("fechaCreacion")),
                            dateCalendar.get(Calendar.YEAR)),
                    builder.equal(builder.function("month", Integer.class, entityRoot.get("fechaCreacion")),
                            dateCalendar.get(Calendar.MONTH) + 1),
                    builder.equal(builder.function("day", Integer.class, entityRoot.get("fechaCreacion")),
                            dateCalendar.get
                            (Calendar.DATE)));
            filtros.add(fechaEstado);
        }


        return filtros;
    }

}
