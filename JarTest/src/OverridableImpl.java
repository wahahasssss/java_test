/**
 * Created by CTWLPC on 2017/4/24.
 */
public class OverridableImpl implements Defaultable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}
