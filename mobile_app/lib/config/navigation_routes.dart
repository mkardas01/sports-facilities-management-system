import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sport_plus/screens/facility_details/bloc/facility_details_bloc.dart';
import 'package:sport_plus/screens/facility_details/facility_details_screen.dart';
import 'package:sport_plus/screens/home/bloc/home_bloc.dart';
import 'package:sport_plus/screens/home/home_screen.dart';
import 'package:sport_plus/screens/trainings/bloc/trainings_bloc.dart';
import 'package:sport_plus/screens/trainings/trainings_screen.dart';
import 'package:sport_plus/services/locator.dart';
import 'package:sport_plus/services/training_service.dart';

Map<String, Widget Function(BuildContext)> routes = {
  HomeScreen.route: (context) => BlocProvider(
        create: (context) => HomeBloc()..add(HomeLoadingEvent()),
        child: const HomeScreen(),
      ),
  FacilityDetailsScreen.route: (context) => BlocProvider(
        create: (context) => FacilityDetailsBloc(
          trainingService: locator.get<TrainingService>(),
        ),
        child: const FacilityDetailsScreen(),
      ),
  TrainingsScreen.route: (context) => BlocProvider(
        create: (context) => TrainingsBloc(),
        child: const TrainingsScreen(),
      ),
};
