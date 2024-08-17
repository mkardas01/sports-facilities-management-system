part of 'trainings_bloc.dart';

abstract class TrainingsEvent {}

class SingUpForTrainingEvent extends TrainingsEvent {
  final int trainingId;
  SingUpForTrainingEvent(this.trainingId);
}
