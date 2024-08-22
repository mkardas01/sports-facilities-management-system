import 'package:get_it/get_it.dart';
import 'package:sport_plus/screens/profile/bloc/profile_bloc.dart';
import 'package:sport_plus/services/image_service.dart';
import 'package:sport_plus/services/training_service.dart';

var locator = GetIt.instance;

void setUp() {
  locator.registerLazySingleton<TrainingService>(() => TrainingService());
  locator.registerLazySingleton<ImageService>(() => ImageService());
  locator.registerLazySingleton<ProfileBloc>(
      () => ProfileBloc(imageService: locator.get<ImageService>()));
}
