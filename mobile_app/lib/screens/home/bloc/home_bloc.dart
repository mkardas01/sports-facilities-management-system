import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sport_plus/models/dummy_data.dart';
import 'package:sport_plus/models/sport_facility.dart';
import 'package:sport_plus/services/storage_service.dart';

part 'home_event.dart';
part 'home_state.dart';

class HomeBloc extends Bloc<HomeEvent, HomeState> {
  final StorageService storageService;
  HomeBloc({required this.storageService}) : super(const HomeState()) {
    on<HomeLoadingEvent>(_loadData);
    on<SignOutEvent>(_signOut);
  }
  Future<void> _loadData(
      HomeLoadingEvent event, Emitter<HomeState> emitter) async {
    var sportFacilities = DummyData.getSportFacilities();
    emitter(state.copyWith(
        status: HomeLoadingStatus.loaded,
        propositions: sportFacilities,
        userFacility: sportFacilities));
  }

  Future<void> _signOut(SignOutEvent event, Emitter<HomeState> emitter) async {
    await storageService.removeToken();
  }
}
