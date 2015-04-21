package de.saxsys.kontaktverwaltung.util;

import java.time.LocalDate;

import javafx.scene.control.Control;

import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

public class BirthDateValidator implements Validator<LocalDate> {

	@Override
	public ValidationResult apply(Control control, LocalDate newValue) {
		if (newValue == null) {
			return ValidationResult.fromError(control,
					"Geben Sie ein Geburtsdatum ein!\n");

		}
		if (newValue != null) {
			LocalDate now = LocalDate.now();

			if (newValue.isAfter(now)) {
				return ValidationResult.fromError(control,
						"Das Geburtsdatum kann nicht in der Zukunft liegen!\n");
			}
		}

		return null;
	}
}
