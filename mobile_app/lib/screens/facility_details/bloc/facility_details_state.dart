part of 'facility_details_bloc.dart';

enum FacilityDetailsLoadingStatus { loading, loaded, error }

enum RatingLoadingStatus { idle, adding, success, error }

class FacilityDetailsState extends Equatable {
  final FacilityDetailsLoadingStatus status;
  final RatingLoadingStatus ratingStatus;
  final SportFacility? facility;
  final List<DayTrainings> trainings;
  final List<OpenHoursItem> openHours;
  const FacilityDetailsState(
      {this.status = FacilityDetailsLoadingStatus.loading,
      this.ratingStatus = RatingLoadingStatus.idle,
      this.facility,
      this.trainings = const [],
      this.openHours = const []});

  FacilityDetailsState copyWith(
      {FacilityDetailsLoadingStatus? status,
      RatingLoadingStatus? ratingStatus,
      SportFacility? facility,
      List<DayTrainings>? trainings,
      List<OpenHoursItem>? openHours}) {
    return FacilityDetailsState(
        status: status ?? this.status,
        ratingStatus: ratingStatus ?? this.ratingStatus,
        facility: facility ?? this.facility,
        trainings: trainings ?? this.trainings,
        openHours: openHours ?? this.openHours);
  }

  @override
  List<Object?> get props =>
      [status, facility, trainings, openHours, ratingStatus];
}
