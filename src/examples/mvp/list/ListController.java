package examples.mvp.list;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import examples.mvp.model.DataModel;
import examples.mvp.model.Person;

public class ListController {

    @FXML
    private ListView<Person> listView ;

    private DataModel model ;

    public void initModel(DataModel model) {
        // ensure model is only set once:
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }

        this.model = model ;
        listView.setItems(model.getPersonList());

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
            model.setCurrentPerson(newSelection));

        model.currentPersonProperty().addListener((obs, oldPerson, newPerson) -> {
            if (newPerson == null) {
                listView.getSelectionModel().clearSelection();
            } else {
                listView.getSelectionModel().select(newPerson);
            }
        });
        
        listView.setCellFactory(lv -> new ListCell<Person>() {
            @Override
            public void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(person.getFirstName() + " " + person.getLastName());
                }
            }
        });
    }
}