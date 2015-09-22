/**
 * Created by brad on 9/21/15.
 */
import com.bradcypert.ginger.*;

@Methods
public class Todo implements Model {
    @Exposed public String name;
    @Exposed public boolean completed;

    @Override
    public String save(PropertyMap map) {
        boolean completed = map.get("completed").toString().toLowerCase().equals("true");
        String name = map.get("name").toString();
        return H2Sample.save(name, completed);
    }

    @Override
    public String fetch(String id) {
        return H2Sample.fetchById(id);
    }

    @Override
    public String fetchAll() {
        return H2Sample.fetchAll();
    }

    @Override
    public String remove(String id) {
        return H2Sample.deleteById(id);
    }
}
