import 'package:get_it/get_it.dart';
import 'package:logger/logger.dart';
import 'package:sport_plus/repository/auth_repository.dart';
import 'package:sport_plus/screens/profile/bloc/profile_bloc.dart';
import 'package:sport_plus/screens/splash/bloc/sign_in_bloc.dart';
import 'package:sport_plus/services/http/http_client.dart';
import 'package:sport_plus/services/image_service.dart';
import 'package:sport_plus/services/storage_service.dart';
import 'package:sport_plus/services/training_service.dart';

var locator = GetIt.instance;

void setUp() {
  locator.registerLazySingleton<StorageService>(() => StorageService());
  locator.registerLazySingleton<Logger>(() => Logger());
  locator.registerLazySingleton<HttpClient>(() => HttpClient());
  locator.registerLazySingleton<TrainingService>(() => TrainingService());
  locator.registerLazySingleton<ImageService>(() => ImageService());

  //REPOS
  locator.registerLazySingleton<AuthRepository>(() => AuthRepository());

  //BLOCS
  locator.registerLazySingleton<ProfileBloc>(
      () => ProfileBloc(imageService: locator.get<ImageService>()));
  locator.registerLazySingleton<SignInBloc>(
      () => SignInBloc(authRepository: locator.get<AuthRepository>()));
}
