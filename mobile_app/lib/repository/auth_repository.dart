import 'package:logger/logger.dart';
import 'package:sport_plus/models/auth/login_user_dto.dart';
import 'package:sport_plus/models/auth/register_user_dto.dart';
import 'package:sport_plus/services/http/http_client.dart';
import 'package:sport_plus/services/locator.dart';

class AuthRepository {
  final HttpClient _client = locator.get<HttpClient>();
  final Logger _logger = locator.get<Logger>();

  Future<String?> registerUser(RegisterUserDto user) async {
    const String url = "/auth/signup";
    try {
      var response = await _client.dio.post(url, data: user.toJson());
      return response.data["token"];
    } catch (e) {
      _logger.e("Błąd na endpoincie $url: $e");
      return null;
    }
  }

  Future<String?> logIn(LoginUserDto user) async {
    const String url = "auth/login";
    try {
      var response = await _client.dio.post(url, data: user.toJson());
      return response.data["token"];
    } catch (e) {
      _logger.e("Błąd na endpoincie $url: $e");
      return null;
    }
  }
}
