package examples.mvp.model;

import java.io.File;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {

    private final ObservableList<Person> personList = FXCollections.observableArrayList(person -> 
        new Observable[] {person.firstNameProperty(), person.lastNameProperty()});

    private final ObjectProperty<Person> currentPerson = new SimpleObjectProperty<>(null);

    public ObjectProperty<Person> currentPersonProperty() {
        return currentPerson ;
    }

    public final Person getCurrentPerson() {
        return currentPersonProperty().get();
    }

    public final void setCurrentPerson(Person person) {
        currentPersonProperty().set(person);
    }

    public ObservableList<Person> getPersonList() {
        return personList ;
    }
    
    public void loadData(File file) {
        // mock...
        personList.setAll(
                new Person("Jacob", "Smith", "jacob.smith@example.com"),
                new Person("Isabella", "Johnson","isabella.johnson@example.com"), 
                new Person("Ethan", "Williams", "ethan.williams@example.com"), 
                new Person("Emma", "Jones", "emma.jones@example.com"), 
                new Person("Michael", "Brown", "michael.brown@example.com")
        );
    }
    
    public void saveData(File file) { }
}