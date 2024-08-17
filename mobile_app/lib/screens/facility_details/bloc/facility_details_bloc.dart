import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/screens/facility_details/models/day_training.dart';
import 'package:sport_plus/models/sport_facility.dart';
import 'package:sport_plus/screens/facility_details/models/open_hours_item.dart';
import 'package:sport_plus/services/training_service.dart';

part 'facility_details_event.dart';
part 'facility_details_state.dart';

class FacilityDetailsBloc
    extends Bloc<FacilityDetailsEvent, FacilityDetailsState> {
  final TrainingService trainingService;
  FacilityDetailsBloc({required this.trainingService})
      : super(const FacilityDetailsState()) {
    on<LoadingFacilityDetailsEvent>(_onLoad);
  }
  void _onLoad(LoadingFacilityDetailsEvent event,
      Emitter<FacilityDetailsState> emitter) {
    var facility = event.facility;
    var trainings = trainingService.extractTrainings(facility);
    final List<OpenHoursItem> openHours = [
      OpenHoursItem(AppStrings.monday, facility.openHours.monday),
      OpenHoursItem(AppStrings.tuesday, facility.openHours.tuesday),
      OpenHoursItem(AppStrings.wednesday, facility.openHours.wednesday),
      OpenHoursItem(AppStrings.thursday, facility.openHours.thursday),
      OpenHoursItem(AppStrings.friday, facility.openHours.friday),
      OpenHoursItem(AppStrings.saturday, facility.openHours.saturday),
      OpenHoursItem(AppStrings.sunday, facility.openHours.sunday),
    ];
    emitter(state.copyWith(
        status: FacilityDetailsLoadingStatus.loaded,
        facility: facility,
        trainings: trainings,
        openHours: openHours));
  }
}
