part of 'facility_details_bloc.dart';

abstract class FacilityDetailsEvent {}

class LoadingFacilityDetailsEvent extends FacilityDetailsEvent {
  final SportFacility facility;
  LoadingFacilityDetailsEvent(this.facility);
}
