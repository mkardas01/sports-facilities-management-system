part of 'facility_details_bloc.dart';

abstract class FacilityDetailsEvent {}

class LoadingFacilityDetailsEvent extends FacilityDetailsEvent {
  final SportFacility facility;
  LoadingFacilityDetailsEvent(this.facility);
}

class AddRatingEvent extends FacilityDetailsEvent {
  final int rating;
  final int objectId;
  final ObjectType objectType;
  AddRatingEvent(
      {required this.rating, required this.objectId, required this.objectType});
}
