package Controlador;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import Modelo.Consulta;
import Modelo.Terapeuta;
import dao.ConsultaDAO;
import dao.jpa.ConsultaJPA;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import util.Sessao;
import java.util.List;
import util.Mensagens;

@ManagedBean
@ViewScoped
public class ConsultaBean implements Serializable {

    private List<Consulta> listaConsultas;
    private Consulta novaConsulta = new Consulta();    
    private ConsultaDAO consultaDAO = new ConsultaJPA();
    private Terapeuta terapeutaLogado = Sessao.obterTerapeutaLogado();

    @PostConstruct
    private void carregarConsultas() {
        setListaConsultas(consultaDAO.todos(terapeutaLogado.getId()));
    }
    
    public String salvar() {
        if (!getListaConsultas().contains(novaConsulta)) {
            novaConsulta.setTerapeuta(terapeutaLogado);
            consultaDAO.salvar(novaConsulta);
            novaConsulta = new Consulta();
            carregarConsultas();
            Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                    "Consulta cadastrada com sucesso!", null);
        } else {
            Mensagens.adicionarMensagem(FacesMessage.SEVERITY_ERROR,
                    "Não pode haver duas consultas iguais!",
                    null);
        }
        return "consultas.xhtml?faces-redirect=true";
    }

    public String concluir(Consulta consulta) {
        consultaDAO.concluir(consulta.getId());
        carregarConsultas();
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Consulta concluída com sucesso!", null);

        return "paginaInicial.xhtml?faces-redirect=true";
    } 
    
    

//    public void cadastrarNovaConsulta() {
//        FacesMessage mensagem;
//        if (!listaConsultas.contains(novaConsulta)) {
//            listaConsultas.add(novaConsulta);
//            novaConsulta = new Consulta();  //atualizar
//            mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
//                    "Consulta cadastrada com sucesso!", null);
//        } else {
//            mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "Essa consulta já foi cadastrada!",
//                    null);
//        }
//        FacesContext.getCurrentInstance().addMessage(null, mensagem);
//    }
//
//    public void excluirConsultaManualmente(Consulta consulta) {
//        listaConsultas.remove(consulta);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                "Consulta concluída com sucesso!", null));
//    }

    public List<Consulta> getListaConsultas() {
        return listaConsultas;
    }

    public void setListaConsultas(List<Consulta> listaConsultas) {
        this.listaConsultas = listaConsultas;
    }

    public Consulta getNovaConsulta() {
        return novaConsulta;
    }

    public void setNovaConsulta(Consulta novaConsulta) {
        this.novaConsulta = novaConsulta;
    }  

}
