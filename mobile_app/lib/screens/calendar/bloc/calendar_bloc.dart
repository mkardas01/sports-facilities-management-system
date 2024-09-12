import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/models/details/coach.dart';
import 'package:sport_plus/models/details/training_session.dart';

part 'calendar_event.dart';
part 'calendar_state.dart';

class CalendarBloc extends Bloc<CalendarEvent, CalendarState> {
  CalendarBloc() : super(const CalendarState()) {
    on<InitTrainingDataEvent>(_initData);
    on<GiveupTrainingEvent>(_deleteTraining);
  }
  Future<void> _initData(
      InitTrainingDataEvent event, Emitter<CalendarState> emitter) async {
    emitter(state.copyWith(isLoaded: false));
    //TODO map trainings for sth nicer (startHour - closeHour)
    emitter(state.copyWith(isLoaded: true, trainings: {
      DateTime(2024, 9, 11, 2).toUtc(): [
        TrainingSession(
            name: "Cardio",
            startHour: "18:00",
            coach: Coach(name: "Ania", surname: "Michalak")),
        TrainingSession()
      ],
      DateTime(2024, 9, 1, 2).toUtc(): [TrainingSession()],
    }));
  }

  Future<void> _deleteTraining(
      GiveupTrainingEvent event, Emitter<CalendarState> emitter) async {
    emitter(state.copyWith(isLoaded: false));
    //TODO delete training
    add(InitTrainingDataEvent());
  }
}
