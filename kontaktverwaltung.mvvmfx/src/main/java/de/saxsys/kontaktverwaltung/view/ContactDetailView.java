package de.saxsys.kontaktverwaltung.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;

import javax.inject.Singleton;

import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.viewmodel.ContactDetailViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

@Singleton
public class ContactDetailView implements FxmlView<ContactDetailViewModel>,
		Initializable {

	@InjectViewModel
	private ContactDetailViewModel viewModel;
	@FXML
	private Label contactName;
	@FXML
	private Label streetLabel;
	@FXML
	private Label zipcodePlaceLabel;
	@FXML
	private Label countryLabel;
	@FXML
	private Label birthDateLabel;
	@FXML
	private Label telephoneLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;

	private ContactEditDialog editDialog;
	private final Stage parentStage;
	private Parent root;
	private Stage detailStage;
	

	public ContactDetailView (ContactEditDialog editDialog, Stage stage) {
		this.editDialog = editDialog;
		this.parentStage = stage;
		ViewTuple<ContactDetailView, ContactDetailViewModel> viewTuple = FluentViewLoader.fxmlView(ContactDetailView.class).codeBehind(this).load();
		
		root = viewTuple.getView();

		detailStage = new Stage();
		detailStage.initOwner(parentStage);
		detailStage.setScene(new Scene(root));
	}
	
	public void show(Contact contact){
		viewModel.show(contact);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		

		contactName.textProperty().bind(viewModel.contactNameProperty());
		streetLabel.textProperty().bind(viewModel.streetProperty());
		zipcodePlaceLabel.textProperty().bind(viewModel.zipCodePlaceProperty());
		countryLabel.textProperty().bind(viewModel.countryProperty());
		birthDateLabel.textProperty().bind(viewModel.birthDateProperty());
		telephoneLabel.textProperty().bind(viewModel.telephoneProperty());
		emailLabel.textProperty().bind(viewModel.emailProperty());
		
		viewModel.setDetailShowFunction(new Function<Void, Void>() {
			
			@Override
			public Void apply(Void t) {
				detailStage.show();
				return null;
			}
		});
		
		viewModel.setDetailCloseFunction(new Function<Void, Void>() {

			@Override
			public Void apply(Void t) {
				detailStage.close();
				return null;
			}
		});
		
		viewModel.setDetailShowAndWaitFunction(new Function<Void, Void>() {
			@Override
			public Void apply(Void t) {
				detailStage.showAndWait();
				return null;
			}
		});
		
	}

	@FXML
	public void editContact() {
		viewModel.edit(tempPerson -> {
			return editDialog.show(tempPerson);
		});
	}

	@FXML
	private void removeContact() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Achtung!");
		alert.setHeaderText(null);
		alert.setContentText("Wollen Sie den Kontakt wirklich löschen?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			viewModel.remove();
		} else {
			detailStage.show();
		}
	}

}
