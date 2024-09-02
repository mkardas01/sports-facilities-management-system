import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class StorageService {
  final storage = const FlutterSecureStorage();

  Future<String?> getToken() async {
    var result = await storage.read(key: "token");
    return result;
  }

  Future<void> addToken(String token) async {
    await storage.write(key: "token", value: token);
  }

  Future<void> removeToken() async {
    await storage.delete(key: "token");
  }
}
