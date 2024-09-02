import 'package:dio/dio.dart';
import 'package:sport_plus/config/app_consts.dart';
import 'package:sport_plus/services/http/token_interceptor.dart';

class HttpClient {
  Dio dio = Dio(BaseOptions(
    baseUrl: AppConsts.apiUrl,
    connectTimeout: const Duration(seconds: 15),
  ))
    ..interceptors.add(TokenInterceptor());
}
