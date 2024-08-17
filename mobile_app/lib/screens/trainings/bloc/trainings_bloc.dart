import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

part 'trainings_event.dart';
part 'trainings_state.dart';

class TrainingsBloc extends Bloc<TrainingsEvent, TrainingsState> {
  TrainingsBloc() : super(const TrainingsState()) {
    on<SingUpForTrainingEvent>(_singUp);
  }
  Future<void> _singUp(
      SingUpForTrainingEvent event, Emitter<TrainingsState> emitter) async {
    emitter(state.copyWith(status: TrainingsLoadingStatus.saving));
    //TODO sprawdzenie czy już nie jest zapisany
    if (false) {
      emitter(state.copyWith(status: TrainingsLoadingStatus.userAlreadySaved));
    } else {
      //TODO zapisanie na trening
      if (true) {
        emitter(state.copyWith(status: TrainingsLoadingStatus.saved));
      } else {
        emitter(state.copyWith(status: TrainingsLoadingStatus.error));
      }
    }

    emitter(state.copyWith(status: TrainingsLoadingStatus.idle));
  }
}
