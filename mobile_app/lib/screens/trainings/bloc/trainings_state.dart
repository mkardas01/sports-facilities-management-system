part of 'trainings_bloc.dart';

enum TrainingsLoadingStatus { idle, saving, saved, error, userAlreadySaved }

class TrainingsState extends Equatable {
  final TrainingsLoadingStatus status;
  const TrainingsState({this.status = TrainingsLoadingStatus.idle});

  TrainingsState copyWith({TrainingsLoadingStatus? status}) {
    return TrainingsState(status: status ?? this.status);
  }

  @override
  List<Object> get props => [status];
}
