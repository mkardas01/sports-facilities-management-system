part of 'trainings_bloc.dart';

enum TrainingsLoadingStatus {
  idle,
  saving,
  saved,
  error,
  userAlreadySaved,
  loading,
  loadingError
}

class TrainingsState extends Equatable {
  final TrainingsLoadingStatus status;
  final List<TrainingSession> userTrainings;
  final List<DayTrainings> extractedTrainings;
  final List<TrainingSession> trainingsList;
  const TrainingsState(
      {this.status = TrainingsLoadingStatus.loading,
      this.userTrainings = const [],
      this.extractedTrainings = const [],
      this.trainingsList = const []});

  TrainingsState copyWith({
    TrainingsLoadingStatus? status,
    List<TrainingSession>? userTrainings,
    List<DayTrainings>? extractedTrainings,
    List<TrainingSession>? trainingsList,
  }) {
    return TrainingsState(
        status: status ?? this.status,
        userTrainings: userTrainings ?? this.userTrainings,
        extractedTrainings: extractedTrainings ?? this.extractedTrainings,
        trainingsList: trainingsList ?? this.trainingsList);
  }

  @override
  List<Object> get props => [
        status,
        userTrainings,
        extractedTrainings,
        trainingsList,
      ];
}
