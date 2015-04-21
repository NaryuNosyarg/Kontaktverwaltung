package de.saxsys.kontaktverwaltung.util;

import java.util.regex.Pattern;

import javafx.scene.control.Control;

import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

public class EmailValidator implements Validator<String> {

	private static final Pattern SIMPLE_EMAIL_PATTERN = Pattern
			.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

	@Override
	public ValidationResult apply(Control control, String newValue) {
		if (newValue == null || newValue.trim().isEmpty()) {
			return ValidationResult.fromError(control,
					"Geben Sie eine E-Mail-Adresse ein!");
		}

		if (!SIMPLE_EMAIL_PATTERN.matcher(newValue).matches()) {
			return ValidationResult.fromError(control,
					"Die E-Mail-Adresse ist ungültig!");
		}

		return null;
	}
}
