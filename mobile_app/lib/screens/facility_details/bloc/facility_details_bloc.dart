import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/models/rating/object_type.dart';
import 'package:sport_plus/models/rating/rating_dto.dart';
import 'package:sport_plus/repository/rating_repository.dart';
import 'package:sport_plus/screens/facility_details/models/day_training.dart';
import 'package:sport_plus/models/sport_facility.dart';
import 'package:sport_plus/screens/facility_details/models/open_hours_item.dart';
import 'package:sport_plus/services/training_service.dart';

part 'facility_details_event.dart';
part 'facility_details_state.dart';

class FacilityDetailsBloc
    extends Bloc<FacilityDetailsEvent, FacilityDetailsState> {
  final TrainingService trainingService;
  final RatingRepository ratingRepository;
  FacilityDetailsBloc(
      {required this.trainingService, required this.ratingRepository})
      : super(const FacilityDetailsState()) {
    on<LoadingFacilityDetailsEvent>(_onLoad);
    on<AddRatingEvent>(_addRating);
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

  void _addRating(
      AddRatingEvent event, Emitter<FacilityDetailsState> emitter) async {
    emitter(state.copyWith(ratingStatus: RatingLoadingStatus.adding));
    await ratingRepository.addRating(RatingDto(
        rate: event.rating,
        objectType: event.objectType,
        objectId: event.objectId));
    emitter(state.copyWith(ratingStatus: RatingLoadingStatus.success));
    emitter(state.copyWith(ratingStatus: RatingLoadingStatus.idle));
  }
}
