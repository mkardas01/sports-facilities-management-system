import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/models/rating/object_type.dart';
import 'package:sport_plus/models/rating/rating_dto.dart';
import 'package:sport_plus/repository/facility_details_repository.dart';
import 'package:sport_plus/repository/rating_repository.dart';
import 'package:sport_plus/screens/facility_details/models/facility_data.dart';
import 'package:sport_plus/services/details_extractor.dart';

part 'facility_details_event.dart';
part 'facility_details_state.dart';

class FacilityDetailsBloc
    extends Bloc<FacilityDetailsEvent, FacilityDetailsState> {
  final DetailsExtractor detailsExtractor;
  final RatingRepository ratingRepository;
  final FacilityDetailsRepository facilityDetailsRepository;
  FacilityDetailsBloc(
      {required this.ratingRepository,
      required this.facilityDetailsRepository,
      required this.detailsExtractor})
      : super(const FacilityDetailsState()) {
    on<LoadingFacilityDetailsEvent>(_onLoad);
    on<AddRatingEvent>(_addRating);
  }
  void _onLoad(LoadingFacilityDetailsEvent event,
      Emitter<FacilityDetailsState> emitter) async {
    emitter(state.copyWith(status: FacilityDetailsLoadingStatus.loading));
    var details = await facilityDetailsRepository.getDetails(event.facilityId);
    if (details == null) {
      emitter(state.copyWith(status: FacilityDetailsLoadingStatus.error));
      return;
    }

    var data = detailsExtractor.getData(details);

    emitter(state.copyWith(
      status: FacilityDetailsLoadingStatus.loaded,
      facility: data,
    ));
  }

  void _addRating(
      AddRatingEvent event, Emitter<FacilityDetailsState> emitter) async {
    emitter(state.copyWith(ratingStatus: RatingLoadingStatus.adding));
    var result = await ratingRepository.addRating(RatingDto(
        rate: event.rating,
        objectType: event.objectType,
        objectId: event.objectId));
    if (!result) {
      emitter(state.copyWith(ratingStatus: RatingLoadingStatus.error));
      return;
    }

    emitter(state.copyWith(ratingStatus: RatingLoadingStatus.success));
    add(LoadingFacilityDetailsEvent(event.objectId));
  }
}
