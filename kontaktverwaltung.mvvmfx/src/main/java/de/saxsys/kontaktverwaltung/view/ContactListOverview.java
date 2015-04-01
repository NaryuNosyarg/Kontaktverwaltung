package de.saxsys.kontaktverwaltung.view;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.inject.Singleton;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.viewmodel.ContactListOverviewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

@Singleton
public class ContactListOverview implements FxmlView<ContactListOverviewModel>,
		Initializable {

	@InjectViewModel
	private ContactListOverviewModel viewModel;

	@FXML
	private Button btnExp;
	@FXML
	private Button btnAdd;
	@FXML
	private ListView<Contact> listView;

	private ContactEditDialog editDialog;
	private ContactDetailView detailView;
	
	
	public ContactListOverview (ContactEditDialog editDialog, ContactDetailView detailView) {
		this.editDialog = editDialog;
		this.detailView = detailView;
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		listView.setItems(viewModel.listProperty());

		viewModel.selectedContactProperty().bind(
				listView.getSelectionModel().selectedItemProperty());
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					viewModel.show(person -> {
						detailView.show(person);
						return null;
					});
				}
			}
		});

	}

	@FXML
	public void addContactToList() throws ParseException {
//		viewModel.add(new Function<Contact, Boolean>() {
//			
//			@Override
//			public Boolean apply(Contact tempPerson) {
//				return editDialog.show(tempPerson);
//			}
//		});
		
		viewModel.add(tempPerson -> {
			return editDialog.show(tempPerson);
		});
		
	}

}
