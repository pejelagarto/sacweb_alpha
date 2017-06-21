package ar.com.divisionturbos.sac.core.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.appfuse.dao.GenericDao;
import org.appfuse.dao.SearchException;
import org.appfuse.dao.jpa.HibernateSearchJpaTools;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by mzanetti on 13/06/17.
 */
public abstract class GenericDaoPaginatedImpl<T, PK extends Serializable> implements GenericDaoPaginated<T, PK> {
    protected final Log log = LogFactory.getLog(this.getClass());
    public static final String PERSISTENCE_UNIT_NAME = "ApplicationEntityManager";
    @PersistenceContext(
            unitName = "ApplicationEntityManager"
    )
    private EntityManager entityManager;
    private Class<T> persistentClass;
    private Analyzer defaultAnalyzer;


    public GenericDaoPaginatedImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        this.persistentClass = (Class<T>) pt.getActualTypeArguments()[0];
        this.defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_36);
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public List<T> getAll() {
        return this.entityManager.createQuery("select obj from " + this.persistentClass.getName() + " obj").getResultList();
    }

    public List<T> getAllDistinct() {
        Collection result = new LinkedHashSet(this.getAll());
        return new ArrayList(result);
    }

    public T get(PK id) {
        T entity = this.entityManager.find(this.persistentClass, id);
        if(entity == null) {
            String msg = "Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...";
            this.log.warn(msg);
            throw new EntityNotFoundException(msg);
        } else {
            return entity;
        }
    }

    public boolean exists(PK id) {
        T entity = this.entityManager.find(this.persistentClass, id);
        return entity != null;
    }

    public T save(T object) {
        return this.entityManager.merge(object);
    }

    public void remove(T object) {
        this.entityManager.remove(this.entityManager.contains(object)?object:this.entityManager.merge(object));
    }

    public void remove(PK id) {
        this.entityManager.remove(this.get(id));
    }

    public List<T> search(String searchTerm) throws SearchException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);

        Query qry;
        try {
            qry = HibernateSearchJpaTools.generateQuery(searchTerm, this.persistentClass, this.entityManager, this.defaultAnalyzer);
        } catch (ParseException var5) {
            throw new SearchException(var5);
        }

        FullTextQuery hibQuery = fullTextEntityManager.createFullTextQuery(qry, new Class[]{this.persistentClass});
        return hibQuery.getResultList();
    }

    public void reindex() {
        HibernateSearchJpaTools.reindex(this.persistentClass, this.getEntityManager());
    }

    public void reindexAll(boolean async) {
        HibernateSearchJpaTools.reindexAll(async, this.getEntityManager());
    }

    Class<T> typeClass;

    public List<T> find(T search) throws DataAccessException {
        return findPaginated(search, null, null, null, null);
    }

    /**
     * Búsqueda paginada o no. Ver indicación en parámetros.
     */
    public List<T> findPaginated(T search, Integer startPosition,
                                 Integer maxResult, String sortFields, String sortDirections)
            throws DataAccessException {
        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(persistentClass);
        Root<T> entityRoot = cq.from(persistentClass);

        List<Predicate> filtros = aplicarFiltros(search, builder, entityRoot);

        CriteriaQuery<T> select = cq.select(entityRoot);
        if (filtros != null && filtros.size() > 0) {
            //manuelhernandez Puede fallar el cast.
            //select.where(builder.and((Predicate[]) filtros.toArray()));
            Predicate[] castLoco = new Predicate[filtros.size()];
            select.where(builder.and(filtros.toArray(castLoco)));
        }
        //Un poco flojo, pero debería funcionar
        if (sortFields != null && !"".equals(sortFields.trim())) {
            String[] sortFiels = sortFields.trim().split(",");
            String[] sortFielsDirection = {"sortDirections"};
            //También feo
            if (sortDirections != null) {
                sortFielsDirection = sortDirections.split(",");
            }
            List<Order> orders = new ArrayList<Order>();
            for (int i = 0; i < sortFiels.length; i++) {
                Order order = null;
                String sDirec = "asc";
                if (sortFielsDirection.length < i) {
                    sDirec = sortFielsDirection[i];
                }
                if ("desc".equalsIgnoreCase(sDirec)) {
                    order = builder.desc(entityRoot.get(sortFiels[i]));
                } else {
                    order = builder.asc(entityRoot.get(sortFiels[i]));
                }
                orders.add(order);
            }
            cq.orderBy(orders);
        }
        TypedQuery<T> query = em.createQuery(cq);
        if (startPosition != null) {
            query.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            query.setMaxResults(maxResult);
        }
        List<T> resp = query.getResultList();
        return resp;
    }

    public Long count(T search) throws DataAccessException {
        EntityManager em = getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<T> entityRoot = cq.from(persistentClass);

        List<Predicate> filtros = aplicarFiltros(search, builder, entityRoot);

        CriteriaQuery<Long> select = cq.select(builder.count(entityRoot));
        if (filtros != null && filtros.size() > 0) {
            //select.where(builder.and((Predicate[]) filtros.toArray()));
            Predicate[] castLoco = new Predicate[filtros.size()];
            select.where(builder.and(filtros.toArray(castLoco)));
        }
        TypedQuery<Long> query = em.createQuery(cq);
        Long resp = query.getSingleResult();
        return resp;
    }

    /**
     * Permite implementar un método que devuelve una lista de filtros
     * a ser aplicados en cualquier consulta que soporte los parámetros
     * devueltos (<code>{@link Predicate}</code>).
     *
     * @param search     S Objeto con valores para realizar la búsqueda.
     * @param builder    {@link CriteriaBuilder}
     * @param entityRoot Root&lt;T&gt;
     * @return List&lt;Predicate&gt;
     */
    public abstract List<Predicate> aplicarFiltros(T search,
                                                   CriteriaBuilder builder, Root<T> entityRoot);

}
