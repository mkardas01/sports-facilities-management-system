part of 'calendar_bloc.dart';

class CalendarState extends Equatable {
  final bool isLoaded;
  final Map<DateTime, List<TrainingSession>> trainings;
  const CalendarState({this.isLoaded = false, this.trainings = const {}});

  CalendarState copyWith(
      {bool? isLoaded, Map<DateTime, List<TrainingSession>>? trainings}) {
    return CalendarState(
        isLoaded: isLoaded ?? this.isLoaded,
        trainings: trainings ?? this.trainings);
  }

  @override
  List<Object?> get props => [isLoaded, trainings];
}
