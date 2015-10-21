package examples.mvp.editor;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import examples.mvp.model.DataModel;

public class EditorController {

    @FXML
    private TextField firstNameField ;
    @FXML
    private TextField lastNameField ;
    @FXML
    private TextField emailField ;

    private DataModel model ;

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.model = model ;
        model.currentPersonProperty().addListener((obs, oldPerson, newPerson) -> {
            if (oldPerson != null) {
                firstNameField.textProperty().unbindBidirectional(oldPerson.firstNameProperty());
                lastNameField.textProperty().unbindBidirectional(oldPerson.lastNameProperty());
                emailField.textProperty().unbindBidirectional(oldPerson.emailProperty());
            }
            if (newPerson == null) {
                firstNameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
            } else {
                firstNameField.textProperty().bindBidirectional(newPerson.firstNameProperty());
                lastNameField.textProperty().bindBidirectional(newPerson.lastNameProperty());
                emailField.textProperty().bindBidirectional(newPerson.emailProperty());
            }
        });
    }
}