package acceso;

import negocio.Ingreso;
import negocio.Reporte;

public interface IServicioIngreso {
    public String registrarIngreso(String prmPlaca, String bahId, String fechaEntrada, String fechaSalida);
    public String registrarSalida(int bahId, String prmFechaSalida);
    public String buscarBahias();
    public String reporteIngreso();
    public String reporteIngresoVeh(String prmPlaca);
}
