package negocio;

public class Bahia {
    private String bahId;
    private String zonId;
    private String bahEstado;

    public Bahia() {
    }

    public Bahia(String bahId, String zonId, String bahEstado) {
        this.bahId = bahId;
        this.zonId = zonId;
        this.bahEstado = bahEstado;
    }

    public String getBahId() {
        return bahId;
    }

    public void setBahId(String bahId) {
        this.bahId = bahId;
    }

    public String getZonId() {
        return zonId;
    }

    public void setZonId(String zonId) {
        this.zonId = zonId;
    }

    public String getBahEstado() {
        return bahEstado;
    }

    public void setBahEstado(String bahEstado) {
        this.bahEstado = bahEstado;
    }
    
    
}
