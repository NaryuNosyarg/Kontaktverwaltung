package de.saxsys.kontaktverwaltung.util;

import java.util.regex.Pattern;

import javafx.scene.control.Control;

import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

public class TelephoneValidator implements Validator<String> {

	private static final Pattern SIMPLE_PHONE_PATTERN = Pattern
			.compile("\\+?[0-9\\s]{3,20}");

	@Override
	public ValidationResult apply(Control control, String input) {
		if (input == null || input.trim().isEmpty()) {
			return ValidationResult.fromError(control,
					"Geben Sie eine Telefonnummer ein!\n");
		}

		if (!SIMPLE_PHONE_PATTERN.matcher(input).matches()) {
			return ValidationResult.fromError(control,
					"Die Telefonnummer ist ungültig!\n");
		}

		return null;
	}

}