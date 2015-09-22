/**
 * Created by brad on 9/21/15.
 */
import com.bradcypert.ginger.Resource;

public class Handler {
    static Resource todos;
    public static void main(String... args) {

        //Some H2 jazz.
        try{
            H2Sample.createDB();
        } catch (Exception e) {
            System.out.println("DONT YOU DIE ON ME, H2!");
        }

        //Heres where the Ginger starts.
        todos = new Resource(Todo.class);
        todos.setBasePath("/api/v1");  // /api/v1/todo/
        todos.generateRoutes();
    }
}
