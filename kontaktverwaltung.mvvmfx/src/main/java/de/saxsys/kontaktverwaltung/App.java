package de.saxsys.kontaktverwaltung;

import javax.inject.Provider;

import de.saxsys.kontaktverwaltung.model.Kontaktverwaltung;
import de.saxsys.kontaktverwaltung.model.Verwaltung;
import de.saxsys.kontaktverwaltung.view.ContactListOverview;
import de.saxsys.kontaktverwaltung.viewmodel.ContactListOverviewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class App extends Application {

	private Stage stage;
	private EasyDI easyDI;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;

		easyDI = new EasyDI();
		
		easyDI.bindInterface(Verwaltung.class, Kontaktverwaltung.class);

		easyDI.bindProvider(Stage.class, new Provider<Stage>() {
			@Override
			public Stage get() {
				return stage;
			}
		});

		MvvmFX.setCustomDependencyInjector(new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> arg0) {
				return easyDI.getInstance(arg0);
			}
		});

		showContactOverview();
	}

	public void showContactOverview() {
		ViewTuple<ContactListOverview, ContactListOverviewModel> viewTuple = FluentViewLoader
				.fxmlView(ContactListOverview.class).load();

		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		stage.show();
	}

}
