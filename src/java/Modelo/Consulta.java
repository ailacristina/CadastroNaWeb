package Modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

@NamedQuery(name = Consulta.TODOS, query = "select t from Consulta t where t.terapeuta.id = :terapeutaId")
@Entity
public class Consulta implements Serializable {
    
    public static final String TODOS = "Consulta.todos";

    @Id @GeneratedValue
    private Long id;
    private String cliente;
    @Temporal(TemporalType.DATE)
    private Date dataCons;
    @Temporal(TemporalType.TIME)
    private Date hora;
    private String convenio;
    private String tratamento;
    @ManyToOne
    private Terapeuta terapeuta;

    public Consulta() {
        this.id = null;
        this.cliente = "";
        this.dataCons = Calendar.getInstance().getTime();
        this.hora = Calendar.getInstance().getTime();
        this.convenio = "";
        this.tratamento = "";
    }

    public Consulta(String cliente, Date data, Date hota, String convenio, String tratamento) {
        this.id = null;
        this.cliente = cliente;
        this.dataCons = Calendar.getInstance().getTime();
        this.hora = Calendar.getInstance().getTime();
        this.convenio = convenio;
        this.tratamento = tratamento;
    }
    
    public Terapeuta getTerapeuta() {
        return terapeuta;
    }

    public void setTerapeuta(Terapeuta terapeuta) {
        this.terapeuta = terapeuta;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getDataCons() {
        return dataCons;
    }

    public void setDataCons(Date data) {
        this.dataCons = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }
    
    
}
