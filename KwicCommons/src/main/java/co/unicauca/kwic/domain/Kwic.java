
package co.unicauca.kwic.domain;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julián
 */
public class Kwic {

    private List<String> response;

    public Kwic() {
        this.response = new ArrayList<String>();
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

}
