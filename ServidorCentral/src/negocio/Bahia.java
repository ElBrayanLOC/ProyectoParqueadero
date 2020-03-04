package negocio;

public class Bahia {
    private Integer bahId;
    private Integer zonId;
    private Integer bahEstado;

    public Bahia() {
    }

    public Bahia(Integer bahId, Integer zonId, Integer bahEstado) {
        this.bahId = bahId;
        this.zonId = zonId;
        this.bahEstado = bahEstado;
    }

    public Integer getBahId() {
        return bahId;
    }

    public void setBahId(Integer bahId) {
        this.bahId = bahId;
    }

    public Integer getZonId() {
        return zonId;
    }

    public void setZonId(Integer zonId) {
        this.zonId = zonId;
    }

    public Integer getBahEstado() {
        return bahEstado;
    }

    public void setBahEstado(Integer bahEstado) {
        this.bahEstado = bahEstado;
    }
    
    
}