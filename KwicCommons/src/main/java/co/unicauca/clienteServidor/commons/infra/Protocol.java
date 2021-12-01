
package co.unicauca.clienteServidor.commons.infra;
import java.util.ArrayList;
import java.util.List;

/**
 * Protocolo de comunicación entre la aplicación cliente y el servidor
 *
 * @author Julián
 */
public class Protocol {

    private String resource;
    private String action;
    private List<Parameter> parameters;

    public Protocol() {
        parameters = new ArrayList<>();
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String name, List<String> value) {
        parameters.add(new Parameter(name, value));
    }

}
