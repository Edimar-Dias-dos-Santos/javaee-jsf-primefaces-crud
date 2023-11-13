package br.com.edimar.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.edimar.dao.UsuarioDAO;
import br.com.edimar.model.Usuario;

@Stateless
public class UsuarioService {

    @EJB
    private UsuarioDAO usuarioDAO;

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarioDAO.adicionarUsuario(usuario);
    }

    public void editarUsuario(Usuario usuario) {
        usuarioDAO.editarUsuario(usuario);
    }

    public void excluirUsuario(Usuario usuario) {
        usuarioDAO.excluirUsuario(usuario);
    }
}