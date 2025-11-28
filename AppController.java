import javafx.collections.ObservableList;

public class AppController {
    private final AppModel model;

    public AppController(AppModel model) {
        this.model = model;
    }

    public void updateTodo(Todo t, int index) {
        this.model.updateTodo(t, index);
    }

    public void addTodo(Todo t) {
        this.model.addTodo(t);
    }

    public void removeTodo(int index) {
        this.model.removeTodo(index);
    }

    public void removeAllTodo() {
        this.model.removeAll();
    }

    public void removeDoneTodos() {

        ObservableList<Todo> todos = this.model.todosProperty();

        if (!todos.isEmpty()) {
            for (int i = todos.size() - 1; i >= 0; i--) {
           // If the Todo object at index i is DONE, remove it.
                if (model.todosProperty().get(i).getStatus() == Status.DONE) {
                    removeTodo(i);
                }
           
            }

        }
    }


}