package controller;

import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import model.ExerciseAerobic;
import model.ExerciseStrength;
import model.Gender;
import model.Person;

public class Controller {
	//FIELDS
	/**
	 * 
	 */
	@FXML
	private ImageView imgWoman;
	/**
	 * 
	 */
	@FXML
	private ImageView imgMan;
	/**
	 * 
	 */
	@FXML
	private ImageView imgUnspecified;
	//PERSONAL INFORMATION
	/**
	 * 
	 */
	@FXML
	private TextField txtStudentID;
	/**
	 * 
	 */
	@FXML
	private TextField txtFirstName;
	/**
	 * 
	 */
	@FXML
	private TextField txtLastName;
	/**
	 * 
	 */
	@FXML
	private ComboBox<Gender> choiceGender;
	/**
	 * 
	 */
	@FXML
	private TextField txtHeight;
	/**
	 * 
	 */
	@FXML
	private TextField txtWeight;
	/**
	 * 
	 */
	@FXML
	private DatePicker txtBirthdate;
	/**
	 * 
	 */
	@FXML
	private Button btnLoadStudent;
	/**
	 * 
	 */
	@FXML
	private Button btnSaveStudent;
	/**
	 * 
	 */
	@FXML
	private Button btnDeleteStudent;
	
	//EXERCISES
	/**
	 * 
	 */
	@FXML
	private ToggleGroup exerciseGroup;
	/**
	 * 
	 */
	@FXML
	private RadioButton radioAerobic;
	/**
	 * 
	 */
	@FXML
	private RadioButton radioWeights;
	/**
	 * 
	 */
	@FXML
	private DatePicker txtExerciseDate;
	/**
	 * 
	 */
	@FXML
	private TextField txtExerciseName;
	/**
	 * 
	 */
	@FXML
	private TextField txtExerciseDuration;
	/**
	 * 
	 */
	@FXML
	private TextField txtMaxHR;
	/**
	 * 
	 */
	@FXML
	private TextField txtAvgHR;
	/**
	 * 
	 */
	@FXML
	private TextField txtExerciseDistance;
	/**
	 * 
	 */
	@FXML
	private Button btnAddExercise;
	/**
	 * 
	 */
	@FXML
	private Button btnRemoveExercise;
	/**
	 * 
	 */
	@FXML
	private TableView<String> exerciseTable;
	/**
	 * 
	 */
	private Person myPerson = new Person();
	/**
	 * 
	 */
	private ExerciseAerobic exerciseAerobic = new ExerciseAerobic();
	/**
	 * 
	 */
	private ExerciseStrength exerciseStrength= new ExerciseStrength();

	// METHODS
	//IF GENDER IS GIRL WHEN LOADING...BRING UP PICTURE OF FITNESS GIRL ANIMATED. SAME WITH BOY
	/**
	 * 
	 */
	public final void initialize() {
		btnSaveStudent.setDisable(true);
		btnLoadStudent.setDisable(true);
		btnDeleteStudent.setDisable(true);
		
		choiceGender.getItems().addAll(Gender.values());
		imgWoman.setVisible(false);
		imgMan.setVisible(false);
		imgUnspecified.setVisible(false);
		
//adds listener to all double text fields to stop anything other than numbers from entering
		addDoubleListener(txtStudentID);
		addDoubleListener(txtHeight);
		addDoubleListener(txtWeight);
		addDoubleListener(txtExerciseDuration);
		addDoubleListener(txtMaxHR);
		addDoubleListener(txtAvgHR);
		addDoubleListener(txtExerciseDistance);
//adds listener to all String text fields
		addStringListener(txtFirstName);
		addStringListener(txtLastName);
		addStringListener(txtExerciseName);
	}
	
	/**
	 *  String Listener for String TextFields to only accept characters for input.
	 * @param textField is a general TextField and this method will be called by 
	 * all TextFields with String input.
	 */
	private void addStringListener(final TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue, 
					final String newValue) {
			// HOW IT WORKS
			// if newValue does not match \w (all letters and numbers) , then whitespace , then all letters and numbers
			// set newValue text to replace anything that is not in the set below (all letters, numbers, and whitespace)
				if (!newValue.matches("\\w*( )?\\w*")) {
					textField.setText(newValue.replaceAll("[^\\w ]", ""));
				}
 			}
		});
	}
	
	/**
	 *  Double Listener for TextFields to only accept numbers for input.
	 * @param textField is a general TextField and this method will be 
	 * called by all TextFields that require numerical input.
	 */
	private void addDoubleListener(final TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue, 
					final String newValue) {
				// HOW IT WORKS
				// if newValue does not match \d (digits 0-9), a period, and \d (digits 0-9)
				// set newValue to replace anything that is not in the set below (\d)
				if (!newValue.matches("\\d*(\\.)?\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d.]", ""));
				}
			}
		});
	}
	
	/**
	 * 
	 */
	@FXML
	private void toggle() {
		if (exerciseGroup.getSelectedToggle().equals(radioAerobic)) {
			radioWeights.setSelected(false);
		} else if (exerciseGroup.getSelectedToggle().equals(radioWeights)){
			radioAerobic.setSelected(false);
		}
	}
	
	/**
	 * Clears all fields after a save or delete.
	 */
	public void clear() {
		txtStudentID.clear();
		txtFirstName.clear();
		txtLastName.clear();
		choiceGender.setValue(null);
		txtHeight.clear();
		txtWeight.clear();
		txtBirthdate.setValue(null);
		txtStudentID.requestFocus();
	}
	
	/**
	 * This method is an error-checking methodology that will disable the "Save" button
	 * until all information is entered or selected in the form.
	 */
	@FXML
	private void disableSave() {
		if (txtStudentID.getText().isEmpty() 
				|| txtFirstName.getText().isEmpty()
				|| txtLastName.getText().isEmpty() 
				|| txtHeight.getText().isEmpty()
				|| txtWeight.getText().isEmpty())
//				|| txtBirthdate.getValue().equals("")) 
			{
			btnSaveStudent.setDisable(true);
		} else {
			btnSaveStudent.setDisable(false);
		}
	}
	
	/**
	 * This method is an error-checking methodology that will disable 
	 * the "Load Student" button until a Student ID has been entered
	 * to be loaded if it is a valid ID number.
	 */
	@FXML
	private void disableLoadStudent() {
		if (txtStudentID.getText().isEmpty()) {
			btnLoadStudent.setDisable(true);
		} else {
			btnLoadStudent.setDisable(false);
		}
	}
	
	/**
	 * This method is an error-checking methodology that will disable 
	 * the "Delete Student" button until a Student ID has been entered to 
	 * be potentially deleted.
	 */
	@FXML
	private void disableDeleteStudent() {
		if (txtStudentID.getText().isEmpty()) {
			btnDeleteStudent.setDisable(true);
		} else {
			btnDeleteStudent.setDisable(false);
		}
	}
	
	/**
	 * This is a method that calls the three methods that disable the save, load,
	 * and delete buttons in the form. Since some TextFields require more than one
	 * of these methods to be called for their proper validation, this is a method that
	 * contains them all so it can be easily accessed and maintained.
	 */
	@FXML
	private void disableButtons() {
		disableSave();
		disableDeleteStudent();
		disableLoadStudent();
	}
	
	/**
	 * 	Display picture of corresponding gender when student is loaded
	 */
	@FXML
	public void displayGender() {
		if (choiceGender.getSelectionModel().getSelectedItem().equals(Gender.UNSPECIFIED)) {
			imgUnspecified.setVisible(true);
			imgWoman.setVisible(false);
			imgMan.setVisible(false);
		} else if (choiceGender.getSelectionModel().getSelectedItem().equals(Gender.FEMALE)) {
			imgWoman.setVisible(true);
			imgUnspecified.setVisible(false);
			imgMan.setVisible(false);
		} else if (choiceGender.getSelectionModel().getSelectedItem().equals(Gender.MALE)) {
			imgMan.setVisible(true);
			imgUnspecified.setVisible(false);
			imgWoman.setVisible(false);
		} else {
			imgUnspecified.setVisible(false);
			imgWoman.setVisible(false);
			imgMan.setVisible(false);
		}
	}

	/**
	 * Loads the student's information and populates the GUI with it. Takes the
	 * student ID as a parameter.
	 */
	@FXML
	public void handleLoad() {
		try {
			myPerson.load(Integer.parseInt(txtStudentID.getText()));

			txtFirstName.setText(myPerson.getFirstName());
			txtLastName.setText(myPerson.getLastName());
			choiceGender.setValue(myPerson.getGender());
			txtHeight.setText(String.valueOf(myPerson.getHeight()));
			txtWeight.setText(String.valueOf(myPerson.getWeight()));
			txtBirthdate.setValue(myPerson.getBirthdate());
			
			displayGender();

		} catch (IllegalArgumentException e) {
			// alert box for if studentID is not found in db
			Alert studentIDNotFound = new Alert(AlertType.ERROR);
			studentIDNotFound.setTitle("Load Error");
			studentIDNotFound.setContentText(
					"Your student's ID number" + " has not been found. Please try a different student ID.");
			studentIDNotFound.showAndWait();
		}

	}
	
	/**
	 * Saves a new students information to the database.
	 * Repeated student information will be overwrote with the most recent save.
	 */
	@FXML
	public void handleSave() {
	
			myPerson.setStudentID(Integer.parseInt(txtStudentID.getText()));
			myPerson.setFirstName(txtFirstName.getText());
			myPerson.setLastName(txtLastName.getText());
			myPerson.setGender(choiceGender.getSelectionModel().getSelectedItem());
			myPerson.setHeight(Double.parseDouble(txtHeight.getText()));
			myPerson.setWeight(Double.parseDouble(txtWeight.getText()));
			myPerson.setBirthdate(txtBirthdate.getValue());

			myPerson.save();
			clear();
			imgMan.setVisible(false);
			imgWoman.setVisible(false);
			imgUnspecified.setVisible(false);

			// confirmation of save
			Alert confirm = new Alert(AlertType.CONFIRMATION);
			confirm.setTitle("Save Confirmed");
			confirm.setContentText("Your student information has been "
					+ "saved. Click OK to save or load another student's information.");
			confirm.showAndWait();
	}

	/**
	 * Deletes student's information by receiving the student ID as a parameter.
	 */
	@FXML
	public void handleDelete() {
		try {
			myPerson.load(Integer.parseInt(txtStudentID.getText()));
			Alert deleteMessage = new Alert(AlertType.CONFIRMATION);
			deleteMessage.setTitle("Confirm Deletion");
			deleteMessage.setContentText("Are you sure you want to permanently delete this" + " student's information?"
					+ "\nPress OK to delete or CANCEL to cancel deletion.");
			Optional<ButtonType> result = deleteMessage.showAndWait();

			if (result.get() == ButtonType.CANCEL) {
				deleteMessage.close();
			} else {
				myPerson.delete();
				clear();
				imgMan.setVisible(false);
				imgWoman.setVisible(false);
				imgUnspecified.setVisible(false);
			}
		} catch (IllegalArgumentException e) {
			// alert box for if toyID is not found in db
			Alert studentIDNotFound = new Alert(AlertType.ERROR);
			studentIDNotFound.setTitle("Deletion Error");
			studentIDNotFound.setContentText(
					"Your student ID number" + " has not been found. Please try a different student ID.");
			studentIDNotFound.showAndWait();
		}
	}
	
	/**
	 * 
	 */
	@FXML
	public void handleAddExercise() {
		
	}
	
	/**
	 * 
	 */
	@FXML
	public void handleRemoveExercise() {
		
	}
}