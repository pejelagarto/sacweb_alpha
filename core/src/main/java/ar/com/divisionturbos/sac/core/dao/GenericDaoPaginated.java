package ar.com.divisionturbos.sac.core.dao;

import org.appfuse.dao.SearchException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mzanetti on 13/06/17.
 */
public interface GenericDaoPaginated <T, PK extends Serializable> {
    List<T> getAll();

    List<T> getAllDistinct();

    List<T> search(String var1) throws SearchException;

    T get(PK var1);

    boolean exists(PK var1);

    T save(T var1);

    void remove(T var1);

    void remove(PK var1);

    void reindex();

    void reindexAll(boolean var1);

    List<Predicate> aplicarFiltros(T search,
                                   CriteriaBuilder builder, Root<T> entityRoot);

    public List<T> findPaginated(T search, Integer startPosition,
                                 Integer maxResult, String sortFields, String sortDirections);

}