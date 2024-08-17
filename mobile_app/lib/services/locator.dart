import 'package:get_it/get_it.dart';
import 'package:sport_plus/services/training_service.dart';

var locator = GetIt.instance;

void setUp() {
  locator.registerLazySingleton<TrainingService>(() => TrainingService());
}
