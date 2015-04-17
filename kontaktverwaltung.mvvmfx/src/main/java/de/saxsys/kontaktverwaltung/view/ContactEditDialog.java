package de.saxsys.kontaktverwaltung.view;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Function;

import javax.inject.Singleton;

import org.controlsfx.dialog.Dialogs;

import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.viewmodel.ContactEditDialogModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

@Singleton
public class ContactEditDialog implements FxmlView<ContactEditDialogModel>,
		Initializable {
	@FXML
	private TextField nameField;
	@FXML
	private TextField familyNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField zipCodeField;
	@FXML
	private TextField placeField;
	@FXML
	private TextField countryField;
	@FXML
	private DatePicker birthDateField;
	@FXML
	private TextField telephoneField;
	@FXML
	private TextField emailField;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;

	@InjectViewModel
	private ContactEditDialogModel viewModel;
	private final Stage parentStage;
	private Parent root;
	private Stage dialogStage;

	private static final String DATE_PATTERN = "dd.MM.yyyy";

	public ContactEditDialog(Stage stage) {
		this.parentStage = stage;
		ViewTuple<ContactEditDialog, ContactEditDialogModel> viewTuple = FluentViewLoader
				.fxmlView(ContactEditDialog.class).codeBehind(this).load();

		root = viewTuple.getView();

		dialogStage = new Stage();
		dialogStage.initOwner(parentStage);
		dialogStage.setScene(new Scene(root));
	}

	public boolean show(Contact contact) {
		viewModel.show(contact);
		return viewModel.isOkClicked();
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		nameField.textProperty().bindBidirectional(viewModel.nameProperty());
		familyNameField.textProperty().bindBidirectional(
				viewModel.familyNameProperty());
		streetField.textProperty()
				.bindBidirectional(viewModel.streetProperty());
		zipCodeField.textProperty().bindBidirectional(
				viewModel.zipCodeProperty());
		placeField.textProperty().bindBidirectional(viewModel.placeProperty());
		countryField.textProperty().bindBidirectional(
				viewModel.countryProperty());
		birthDateField.valueProperty().bindBidirectional(
				viewModel.birthDateProperty());
		telephoneField.textProperty().bindBidirectional(
				viewModel.telephoneProperty());
		emailField.textProperty().bindBidirectional(viewModel.emailProperty());

		birthDateField.setConverter(converter);
		
		viewModel.initValidationForName(nameField);
		viewModel.initValidationForFamilyName(familyNameField);
		viewModel.initValidationForStreet(streetField);
		viewModel.initValidationForZipCode(zipCodeField);
		viewModel.initValidationForPlace(placeField);
		viewModel.initValidationForCountry(countryField);
		viewModel.initValidationForBirthDate(birthDateField);
		viewModel.initValidationForTelephone(telephoneField);
		viewModel.initValidationForEmail(emailField);

		viewModel.setDialogShowAndWaitFunction(new Function<Void, Void>() {
			@Override
			public Void apply(Void t) {
				dialogStage.showAndWait();
				return null;
			}
		});

		viewModel.setDialogCloseFunction(new Function<Void, Void>() {

			@Override
			public Void apply(Void t) {
				dialogStage.close();
				return null;
			}
		});

		viewModel.setMessageFunction(new Function<String, Void>() {

			@Override
			public Void apply(String errorMessage) {
				Dialogs.create().title("Ups!")
				.masthead("Bitte Felder richtig ausfüllen!")
				.message(errorMessage).showError();
				return null;
			}
		});
	}

	StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
		DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
				.ofPattern(DATE_PATTERN);

		@Override
		public String toString(LocalDate date) {
			if (date == null) {
				return null;
			}
			return DATE_FORMATTER.format(date);
		}

		@Override
		public LocalDate fromString(String string) {
			if (string != null && !string.isEmpty()) {
				return LocalDate.parse(string, DATE_FORMATTER);
			} else {
				return null;
			}
		}
	};

	@FXML
	private void handleCancel() {
		viewModel.cancel();
	}

	@FXML
	private void handleOk() {
		viewModel.ok();
	}

	

}
