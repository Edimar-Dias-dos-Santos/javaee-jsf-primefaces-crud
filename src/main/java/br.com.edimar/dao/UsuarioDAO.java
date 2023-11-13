package br.com.edimar.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.edimar.model.Usuario;

@Stateless
public class UsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Usuario> listarUsuarios() {
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public void adicionarUsuario(Usuario usuario) {
        
        if (emailJaExistente(usuario.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado. Escolha outro e-mail.");
        }

        entityManager.persist(usuario);
    }

    public void editarUsuario(Usuario usuario) {
        
        if (emailJaExistente(usuario.getEmail(), usuario.getId())) {
            throw new RuntimeException("E-mail já cadastrado por outro usuário. Escolha outro e-mail.");
        }

        entityManager.merge(usuario);
    }

    public void excluirUsuario(Usuario usuario) {
        
        Usuario usuarioParaExcluir = entityManager.find(Usuario.class, usuario.getId());
        if (usuarioParaExcluir == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        entityManager.remove(usuarioParaExcluir);
    }

    private boolean emailJaExistente(String email) {
        
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return count > 0;
    }

    private boolean emailJaExistente(String email, Long id) {
        
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email AND u.id <> :id", Long.class)
                .setParameter("email", email)
                .setParameter("id", id)
                .getSingleResult();

        return count > 0;
    }
}