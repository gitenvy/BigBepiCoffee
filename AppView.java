import java.lang.ModuleLayer.Controller;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppView {
    private VBox view;
    private TableView<Todo> todoView;
    private Button addTodoBtn;
    private Button updateTodoBtn;
    private Button removeTodoBtn;

    private AppController controller;
    private AppModel model;
    private Stage primaryStage;
    private Button removeAllButton;
    private Button removeDoneTodos;

    public AppView(AppController controller, AppModel model, Stage primaryStage) {

        this.controller = controller;
        this.model = model;
        this.primaryStage = primaryStage;

        createAndConfigurePane();
        createAndLayoutControls();
        updateControllerFromListeners();
        observeModelAndUpdateControls();
    }

    public Parent asParent() {
        return view;
    }

    private void observeModelAndUpdateControls() {
        // Not needed here
    }

    private void updateControllerFromListeners() {
        // Not needed here
    }

    private void createAndLayoutControls() {

        // Setup the TableView
        this.todoView = new TableView<>();
        TableColumn<Todo, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setMinWidth(220.0);
        descriptionCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        // TODO
        TableColumn<Todo, Status> statusCol = new TableColumn<>("Status");
        TableColumn<Todo, Priority> priorityCol = new TableColumn<>("Priority");



        statusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        // TODO
        priorityCol.setCellValueFactory(cellData -> cellData.getValue().priorityProperty());

        this.todoView.getColumns().addAll(descriptionCol, statusCol, priorityCol);
        this.todoView.setItems(model.todosProperty());

        // Setup the buttons
        this.addTodoBtn = new Button("Add To-do");
        this.addTodoBtn.setOnAction(event -> createAddTodoForm());

        this.updateTodoBtn = new Button("Edit to-do");
        this.updateTodoBtn.setOnAction(event -> {
            int index = this.todoView.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                createUpdateTodoForm(index);
            }

        });

        this.removeTodoBtn = new Button("Remove To-do");
        removeTodoBtn.setOnAction(event -> {
            int index = this.todoView.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                this.controller.removeTodo(index);
            }
        });

        this.removeAllButton = new Button("Remove all To-do");
        removeAllButton.setOnAction(event -> {
            controller.removeAllTodo();
        });

        this.removeDoneTodos = new Button("Remove done To-do");
        removeDoneTodos.setOnAction(event -> {
            controller.removeDoneTodos();
        });

        

        HBox buttonRow = new HBox(5, addTodoBtn, updateTodoBtn, removeTodoBtn, removeAllButton, removeDoneTodos);

        view.getChildren().addAll(this.todoView, buttonRow);
    }

    private void createAndConfigurePane() {
        view = new VBox(5);
        view.setAlignment(Pos.CENTER);
    }

    private void createAddTodoForm() {
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter to-do description");
        HBox descriptionRow = new HBox(5, new Label("Description:"), descriptionField);
        descriptionRow.setAlignment(Pos.CENTER);

       
        

        // Toggle group to set status
        ToggleGroup toggleGroup = new ToggleGroup();
        ToggleGroup priorityToggleGroup = new ToggleGroup();
        RadioButton inProgressBtn = new RadioButton("In progress");
        inProgressBtn.setToggleGroup(toggleGroup);

        RadioButton wontCompleteBtn = new RadioButton("Won't complete");
        wontCompleteBtn.setToggleGroup(toggleGroup);

        RadioButton doneBtn = new RadioButton("Done");
        doneBtn.setToggleGroup(toggleGroup);

        // TODO tuta

        RadioButton highPriorityBtn = new RadioButton(Priority.LOW.toString());
        highPriorityBtn.setToggleGroup(priorityToggleGroup);

        RadioButton mediumPriorityBtn = new RadioButton(Priority.MEDIUM.toString());
        mediumPriorityBtn.setToggleGroup(priorityToggleGroup);

        RadioButton lowPriorityBtn = new RadioButton(Priority.HIGH.toString());
        lowPriorityBtn.setToggleGroup(priorityToggleGroup);


        HBox radioBtnRow = new HBox(5, inProgressBtn, doneBtn, wontCompleteBtn);
        radioBtnRow.setAlignment(Pos.CENTER);

        HBox priorityRow = new HBox(5, lowPriorityBtn, mediumPriorityBtn, highPriorityBtn);
        priorityRow.setAlignment(Pos.CENTER);

        


        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(event -> {

            String text = descriptionField.getText().trim();
            Status status;
            if (inProgressBtn.isSelected()) {
                status = Status.IN_PROGRESS;
            } else if (wontCompleteBtn.isSelected()) {
                status = Status.WONT_COMPLETE;
            } else {
                status = Status.DONE;
            }

            Priority priority;

            if (lowPriorityBtn.isSelected()) {
                priority = Priority.LOW;
            } 
            else if (mediumPriorityBtn.isSelected()) {
                priority = Priority.MEDIUM;
            } else {
                priority = Priority.HIGH;
            }

            if (!text.isEmpty()) {
                Todo t = new Todo(descriptionField.getText(), status, priority);
                this.controller.addTodo(t);
                stage.close();
            }

           
        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> stage.close());

        HBox buttonRow = new HBox(5, submitBtn, cancelBtn);
        buttonRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, descriptionRow, radioBtnRow, priorityRow,  buttonRow);
        root.setAlignment(Pos.CENTER);

        Scene helloScene = new Scene(root, 300, 100);

        stage.setScene(helloScene);
        stage.show();
    }

    private void createUpdateTodoForm(int index) {
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        // We access the model's todosProperty to determine the description of the
        // selected to-do.
        TextField descriptionField = new TextField();
        String oldText = model.todosProperty().get(index).getDescription();
        descriptionField.setPromptText("Enter new description");
        descriptionField.setText(oldText);

        HBox descriptionRow = new HBox(5, new Label("Description:"), descriptionField);
        descriptionRow.setAlignment(Pos.CENTER);

        // Toggle group to set status
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton inProgressBtn = new RadioButton("In progress");
        inProgressBtn.setToggleGroup(toggleGroup);

        RadioButton wontCompleteBtn = new RadioButton("Won't complete");
        wontCompleteBtn.setToggleGroup(toggleGroup);

        RadioButton doneBtn = new RadioButton("Done");
        doneBtn.setToggleGroup(toggleGroup);

         ToggleGroup priorityToggleGroup = new ToggleGroup();

     RadioButton highPriorityBtn = new RadioButton(Priority.LOW.toString());
        highPriorityBtn.setToggleGroup(priorityToggleGroup);

        RadioButton mediumPriorityBtn = new RadioButton(Priority.MEDIUM.toString());
        mediumPriorityBtn.setToggleGroup(priorityToggleGroup);

        RadioButton lowPriorityBtn = new RadioButton(Priority.HIGH.toString());
        lowPriorityBtn.setToggleGroup(priorityToggleGroup);


        HBox radioBtnRow = new HBox(5, inProgressBtn, doneBtn, wontCompleteBtn);
        radioBtnRow.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(event -> {
            String text = descriptionField.getText().trim();
            Status oldStatus = model.todosProperty().get(index).getStatus();
            Status newStatus;
            if (inProgressBtn.isSelected()) {
                newStatus = Status.IN_PROGRESS;
            } else if (wontCompleteBtn.isSelected()) {
                newStatus = Status.WONT_COMPLETE;
            } else {
                newStatus = Status.DONE;
            }

            //TODO


            Priority newPriority;

            if (lowPriorityBtn.isSelected()) {
                newPriority = Priority.LOW;
            } 
            else if (mediumPriorityBtn.isSelected()) {
                newPriority = Priority.MEDIUM;
            } else {
                newPriority = Priority.HIGH;
            }



            boolean changedStatus = oldStatus != newStatus;
            boolean newTextIsNonEmptyAndDiffers = !text.isEmpty() && !text.equals(oldText);
            if (newTextIsNonEmptyAndDiffers || changedStatus) {
                Todo t = new Todo(descriptionField.getText(), newStatus, newPriority);
                this.controller.updateTodo(t, index);
                stage.close();
            }
        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> stage.close());

        HBox buttonRow = new HBox(5, submitBtn, cancelBtn);
        buttonRow.setAlignment(Pos.CENTER);

         HBox priorityRow = new HBox(5, lowPriorityBtn, mediumPriorityBtn, highPriorityBtn);
        priorityRow.setAlignment(Pos.CENTER);


        VBox root = new VBox(5, descriptionRow, radioBtnRow,priorityRow, buttonRow);
        root.setAlignment(Pos.CENTER);

        Scene helloScene = new Scene(root, 300, 100);

        stage.setScene(helloScene);
        stage.show();
    }
}