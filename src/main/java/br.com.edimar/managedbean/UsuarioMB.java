package br.com.edimar.managedbean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.edimar.model.Usuario;
import br.com.edimar.service.UsuarioService;

@ManagedBean
@ViewScoped
public class UsuarioMB {

    @EJB
    private UsuarioService usuarioService;

    private Usuario usuario = new Usuario();
    private List<Usuario> listaUsuarios;

    public void carregarUsuarios() {
        listaUsuarios = usuarioService.listarUsuarios();
    }

    public void adicionarUsuario() {
        usuarioService.adicionarUsuario(usuario);
        limparFormulario();
    }

    public void editarUsuario() {
        usuarioService.editarUsuario(usuario);
        limparFormulario();
    }

    public void excluirUsuario() {
        usuarioService.excluirUsuario(usuario);
        limparFormulario();
    }

    private void limparFormulario() {
        usuario = new Usuario();
        carregarUsuarios();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}