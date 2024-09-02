import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

part 'sign_in_event.dart';
part 'sign_in_state.dart';

class SignInBloc extends Bloc<SignInEvent, SignInState> {
  SignInBloc() : super(const SignInState()) {
    on<LogInEvent>(_logIn);
  }
  Future<void> _logIn(LogInEvent event, Emitter<SignInState> emitter) async {}
}
