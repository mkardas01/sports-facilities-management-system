part of 'facility_details_bloc.dart';

enum FacilityDetailsLoadingStatus { loading, loaded, error }

enum RatingLoadingStatus { idle, adding, success, error }

class FacilityDetailsState extends Equatable {
  final FacilityDetailsLoadingStatus status;
  final RatingLoadingStatus ratingStatus;
  final FacilityData? facility;

  const FacilityDetailsState({
    this.status = FacilityDetailsLoadingStatus.loading,
    this.ratingStatus = RatingLoadingStatus.idle,
    this.facility,
  });

  FacilityDetailsState copyWith({
    FacilityDetailsLoadingStatus? status,
    RatingLoadingStatus? ratingStatus,
    FacilityData? facility,
  }) {
    return FacilityDetailsState(
      status: status ?? this.status,
      ratingStatus: ratingStatus ?? this.ratingStatus,
      facility: facility ?? this.facility,
    );
  }

  @override
  List<Object?> get props => [status, facility, ratingStatus];
}
