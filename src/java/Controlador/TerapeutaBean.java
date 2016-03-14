package Controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import Modelo.Terapeuta;
import dao.TerapeutaDAO;
import dao.jpa.TerapeutaJPA;
import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpSession;
import util.Mensagens;

@ManagedBean
@SessionScoped
public class TerapeutaBean implements Serializable {

    private final TerapeutaDAO terapeutaDAO = new TerapeutaJPA();
    private Terapeuta novoTerapeuta = new Terapeuta();
    private Terapeuta terapeutaLogado = null;
    private String confirmarSenha;
    
    
    public boolean estahLogado() {
        return (terapeutaLogado != null);
    }

    public String salvar() {
        if (!confirmarSenha.equals(novoTerapeuta.getSenha())) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "As senhas informadas não conferem!",
                    null);
            return null;
        }
        if (terapeutaDAO.existeLogin(novoTerapeuta.getLogin())) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Terapeuta já cadastrado com esse Login!",
                    null);
            return null;
        }
        terapeutaDAO.salvar(novoTerapeuta);
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                    "Terapeuta cadastrado com sucesso!", null);
        novoTerapeuta = new Terapeuta();
        return "index.xhtml?faces-redirect=true";
    }

    public String entrar() {
        terapeutaLogado = terapeutaDAO.porLogineSenha(novoTerapeuta.getLogin(), 
                novoTerapeuta.getSenha());
        novoTerapeuta = new Terapeuta();
        if (terapeutaLogado == null) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Login ou senha inválidos!",
                    null);
            return null;
        }
        return "paginaInicial.xhtml?faces-redirect=true";
    }

    public String sair() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        novoTerapeuta = new Terapeuta();
        return "index.xhtml?faces-redirect=true";
    }

    
   
    public String queroMeCadastrar() {
        return "cadastro";
    }
    
    public String voltarIndex() {
        return "index";
    }
//
//    public String paginaInicialTerapeuta(Terapeuta novoTerapeuta) {
//        return "paginaInicial";
//    }
//
//    public boolean ehValido(Terapeuta t) {
//        for (Terapeuta tt : listaTerapeutas) {
//            if (tt.getLogin().equals(t.getLogin()) && tt.getSenha().equals(t.getSenha())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public String entrar() {
//        FacesMessage mensagem;
//        if (ehValido(getNovoTerapeuta())) {
//            // PORQUE QUANDO EXECUTA NÃO APARECE O NOME DEPOIS DO "SEJA BEM VINDO"?
//            mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seja bem vindo", null);
//            FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            return "paginaInicial";
//        } else {
//            mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha "
//                    + "incorretos!", null);
//
//            FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            return null;
//        }
//    }
//
//    public String cadastrarTerapeuta() {
//        FacesMessage mensagem;
//        if (!listaTerapeutas.contains(novoTerapeuta)) {
//            listaTerapeutas.add(getNovoTerapeuta());
//            setNovoTerapeuta(new Terapeuta());  //atualizar
//            mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
//                    "Fisioterapeuta cadastrado com sucesso!", null);
//
//        } else {
//            mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "Fisioterapeuta já cadastrado!",
//                    null);
//        }
//        FacesContext.getCurrentInstance().addMessage(null, mensagem);
//        return "index";  /// NÃO VAI PARA O INDEX.. CONTINUA NA MESMA PÁGINA!!!
//    }

    public Terapeuta getNovoTerapeuta() {
        return novoTerapeuta;
    }

    public void setNovoTerapeuta(Terapeuta novoTerapeuta) {
        this.novoTerapeuta = novoTerapeuta;
    }

    public Terapeuta getTerapeutaLogado() {
        return terapeutaLogado;
    }

    public void setTerapeutaLogado(Terapeuta terapeutaLogado) {
        this.terapeutaLogado = terapeutaLogado;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

}
