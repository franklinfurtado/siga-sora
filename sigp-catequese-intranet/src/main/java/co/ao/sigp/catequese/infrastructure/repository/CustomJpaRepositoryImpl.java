package co.ao.sigp.catequese.infrastructure.repository;

import java.util.Optional;
import javax.persistence.EntityManager;
import co.ao.sigp.catequese.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private final EntityManager manager;
    
    private final String nomeClasse = getDomainClass().getName();

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.manager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        //getDomainClass - retorna a classe Pai, classe que chama os metodos 
        var jpql = "from " + getDomainClass().getName();

        //setMaxResults - retorna apenas a primeira linha do resultado
        T entity = manager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();

        //retorna um optional com valor nulo ou com o valor da entity
        return Optional.ofNullable(entity);
    }

    @Override
    public void detach(T entity) {
        manager.detach(entity);
    }

    @Override
    public boolean nomeExistenteNoSistema(String nomeAtributoId, Integer valorAtributoId, String nomeAtributoNome, String valorAtributoNome) {
        
        var jpql = String.format("SELECT CASE WHEN (COUNT(entity) > 0) THEN true ELSE false END "
                 + "FROM %s entity "
                 + "WHERE entity.%s = :valorAtributoNome and entity.%s != :valorAtributoId"
                 , nomeClasse, nomeAtributoNome, nomeAtributoId);
        
        return manager.createQuery(jpql, Boolean.class)
                .setParameter("valorAtributoNome", valorAtributoNome)
                .setParameter("valorAtributoId", valorAtributoId).getSingleResult();
    }

    @Override
    public boolean nomeExistenteNoSistema(String nomeAtributoNome, String valorAtributoNome) {
        
        var jpql = String.format("SELECT CASE WHEN (COUNT(entity) > 0) THEN true ELSE false END "
                 + "FROM %s entity "
                 + "WHERE entity.%s = :valorAtributoNome"
                 , nomeClasse, nomeAtributoNome);
        
        return manager.createQuery(jpql, Boolean.class)
                .setParameter("valorAtributoNome", valorAtributoNome).getSingleResult();
    }

    @Override
    public boolean existeEntidadesDependentes(String nomeAtributoId, Integer valorAtributoId, String nomeAtributoListEntidadeDependent) {
        var jpql = String.format("SELECT CASE WHEN (SIZE(entity.%s) > 0) THEN true ELSE false END "
                + "FROM %s entity "
                + "WHERE entity.%s = :valorAtributoId"
                 , nomeAtributoListEntidadeDependent, nomeClasse, nomeAtributoId);
        
        return manager.createQuery(jpql, Boolean.class)
                .setParameter("valorAtributoId", valorAtributoId).getSingleResult();
    }
}
