import 'package:logger/logger.dart';
import 'package:sport_plus/models/user/user_dto.dart';
import 'package:sport_plus/services/http/http_client.dart';
import 'package:sport_plus/services/locator.dart';

class UserRepository {
  final HttpClient _client = locator.get<HttpClient>();
  final Logger _logger = locator.get<Logger>();

  Future<UserDto?> getCurrentUser() async {
    const String url = "/api/User";
    try {
      var response = await _client.dio.get(url);
      var x = 2;
      var result = UserDto.fromJson(response.data);
      return result;
    } catch (e) {
      _logger.e("Błąd na endpoincie $url: $e");
      return null;
    }
  }
}
