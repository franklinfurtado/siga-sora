package co.ao.sigp.catequese.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//@NoRepositoryBean - define que a classe nao deve ser instaciada com uma implementacao pelo spring
//a interface nao tera implementacao
@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> buscarPrimeiro();

    void detach(T entity);
    
    boolean existeEntidadesDependentes(String nomeAtributoId, Integer valorAtributoId, String nomeAtributoListEntidadeDependent);
    
    boolean nomeExistenteNoSistema(String nomeAtributoNome, String valorAtributoNome);
    
    boolean nomeExistenteNoSistema(String nomeAtributoId, Integer valorAtributoId, String nomeAtributoNome, String valorAtributoNome);
}
