package negocio;

public class Ingreso {
    private String vehPlaca;
    private String bahId;
    private String fechaIngreso;
    private String fechaSalida;

    public Ingreso(String vehPlaca, String bahId, String fechaIngreso, String fechaSalida) {
        this.vehPlaca = vehPlaca;
        this.bahId = bahId;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public Ingreso() {
    }

    public String getVehPlaca() {
        return vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getBahId() {
        return bahId;
    }

    public void setBahId(String bahId) {
        this.bahId = bahId;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    
}
