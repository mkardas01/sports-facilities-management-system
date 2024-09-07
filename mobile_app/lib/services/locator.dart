import 'package:get_it/get_it.dart';
import 'package:logger/logger.dart';
import 'package:sport_plus/repository/auth_repository.dart';
import 'package:sport_plus/repository/rating_repository.dart';
import 'package:sport_plus/repository/user_repository.dart';
import 'package:sport_plus/screens/facility_details/bloc/facility_details_bloc.dart';
import 'package:sport_plus/screens/home/bloc/home_bloc.dart';
import 'package:sport_plus/screens/profile/bloc/profile_bloc.dart';
import 'package:sport_plus/screens/splash/bloc/sign_in_bloc.dart';
import 'package:sport_plus/screens/trainings/bloc/trainings_bloc.dart';
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
  locator.registerLazySingleton<UserRepository>(() => UserRepository());
  locator.registerLazySingleton<RatingRepository>(() => RatingRepository());

  //BLOCS
  locator.registerLazySingleton<HomeBloc>(
      () => HomeBloc(storageService: locator.get<StorageService>()));
  locator.registerLazySingleton<FacilityDetailsBloc>(() => FacilityDetailsBloc(
      trainingService: locator.get<TrainingService>(),
      ratingRepository: locator.get<RatingRepository>()));
  locator.registerLazySingleton<TrainingsBloc>(() => TrainingsBloc());
  locator.registerLazySingleton<ProfileBloc>(() => ProfileBloc(
      imageService: locator.get<ImageService>(),
      userRepository: locator.get<UserRepository>()));
  locator.registerLazySingleton<SignInBloc>(() => SignInBloc(
      authRepository: locator.get<AuthRepository>(),
      storageService: locator.get<StorageService>()));
}
