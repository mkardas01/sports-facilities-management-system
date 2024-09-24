import 'package:get_it/get_it.dart';
import 'package:logger/logger.dart';
import 'package:sport_plus/repository/auth_repository.dart';
import 'package:sport_plus/repository/facility_details_repository.dart';
import 'package:sport_plus/repository/news_repository.dart';
import 'package:sport_plus/repository/rating_repository.dart';
import 'package:sport_plus/repository/sport_facility_repository.dart';
import 'package:sport_plus/repository/user_repository.dart';
import 'package:sport_plus/screens/calendar/bloc/calendar_bloc.dart';
import 'package:sport_plus/screens/facilities/bloc/all_facilities_bloc.dart';
import 'package:sport_plus/screens/facility_details/bloc/facility_details_bloc.dart';
import 'package:sport_plus/screens/home/bloc/home_bloc.dart';
import 'package:sport_plus/screens/map/bloc/map_bloc.dart';
import 'package:sport_plus/screens/profile/bloc/profile_bloc.dart';
import 'package:sport_plus/screens/splash/bloc/sign_in_bloc.dart';
import 'package:sport_plus/screens/trainings/bloc/trainings_bloc.dart';
import 'package:sport_plus/services/details_extractor.dart';
import 'package:sport_plus/services/http/http_client.dart';
import 'package:sport_plus/services/image_service.dart';
import 'package:sport_plus/services/location_service.dart';
import 'package:sport_plus/services/storage_service.dart';
import 'package:sport_plus/services/training_service.dart';

var locator = GetIt.instance;

void setUp() {
  locator.registerLazySingleton<StorageService>(() => StorageService());
  locator.registerLazySingleton<Logger>(() => Logger());
  locator.registerLazySingleton<HttpClient>(() => HttpClient());
  locator.registerLazySingleton<TrainingService>(() => TrainingService());
  locator.registerLazySingleton<DetailsExtractor>(
      () => DetailsExtractor(trainingService: locator.get<TrainingService>()));
  locator.registerLazySingleton<ImageService>(() => ImageService());
  locator.registerLazySingleton<LocationService>(() => LocationService());

  //REPOS
  locator.registerLazySingleton<AuthRepository>(() => AuthRepository());
  locator.registerLazySingleton<UserRepository>(() => UserRepository());
  locator.registerLazySingleton<RatingRepository>(() => RatingRepository());
  locator.registerLazySingleton<FacilityDetailsRepository>(
      () => FacilityDetailsRepository());
  locator.registerLazySingleton<SportFacilityRepository>(
      () => SportFacilityRepository());
  locator.registerLazySingleton<NewsRepository>(() => NewsRepository());

  //BLOCS
  locator.registerLazySingleton<HomeBloc>(() => HomeBloc(
      storageService: locator.get<StorageService>(),
      sportFacilityRepository: locator.get<SportFacilityRepository>(),
      newsRepository: locator.get<NewsRepository>(),
      locationService: locator.get<LocationService>(),
      authRepository: locator.get<AuthRepository>()));
  locator.registerLazySingleton<FacilityDetailsBloc>(() => FacilityDetailsBloc(
        ratingRepository: locator.get<RatingRepository>(),
        facilityDetailsRepository: locator.get<FacilityDetailsRepository>(),
        detailsExtractor: locator.get<DetailsExtractor>(),
      ));
  locator.registerLazySingleton<TrainingsBloc>(() => TrainingsBloc());
  locator.registerLazySingleton<ProfileBloc>(() => ProfileBloc(
      imageService: locator.get<ImageService>(),
      userRepository: locator.get<UserRepository>()));
  locator.registerLazySingleton<SignInBloc>(() => SignInBloc(
      authRepository: locator.get<AuthRepository>(),
      storageService: locator.get<StorageService>()));
  locator.registerLazySingleton<MapBloc>(() => MapBloc(
      locationService: locator.get<LocationService>(),
      sportFacilityRepository: locator.get<SportFacilityRepository>()));
  locator.registerLazySingleton<CalendarBloc>(() => CalendarBloc());
  locator.registerLazySingleton<AllFacilitiesBloc>(() => AllFacilitiesBloc(
      sportFacilityRepository: locator.get<SportFacilityRepository>()));
}
