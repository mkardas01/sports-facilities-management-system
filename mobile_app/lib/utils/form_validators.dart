import 'package:sport_plus/config/app_strings.dart';

class FormValidators {
  static String? confirmPasswordValidator(
      String password, String confirmPassword) {
    if (confirmPassword.isEmpty) {
      return AppStrings.requiredText;
    } else if (confirmPassword != password) {
      return AppStrings.passwordNotConfirmed;
    }
    return null;
  }

  static String? notEmpty(String value) {
    if (value.isEmpty) {
      return AppStrings.requiredText;
    }
    return null;
  }
}
