import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/models/auth/login_user_dto.dart';
import 'package:sport_plus/models/auth/register_user_dto.dart';
import 'package:sport_plus/repository/auth_repository.dart';

part 'sign_in_event.dart';
part 'sign_in_state.dart';

class SignInBloc extends Bloc<SignInEvent, SignInState> {
  final AuthRepository authRepository;

  SignInBloc({required this.authRepository}) : super(const SignInState()) {
    on<LogInEvent>(_logIn);
    on<RegisterEvent>(_registerUser);
  }
  Future<void> _logIn(LogInEvent event, Emitter<SignInState> emitter) async {
    emitter(state.copyWith(status: SignInLoadingStatus.loading));
    var result = await authRepository.logIn(LoginUserDto(
      email: event.email,
      password: event.password,
    ));
    if (result) {
      emitter(state.copyWith(status: SignInLoadingStatus.loggedIn));
    } else {
      emitter(state.copyWith(status: SignInLoadingStatus.error));
    }
  }

  Future<void> _registerUser(
      RegisterEvent event, Emitter<SignInState> emitter) async {
    emitter(state.copyWith(status: SignInLoadingStatus.loading));
    var result = await authRepository.registerUser(RegisterUserDto(
        email: event.email,
        password: event.password,
        name: event.name,
        surname: event.surname));
    if (result) {
      emitter(state.copyWith(status: SignInLoadingStatus.registerSuccess));
    } else {
      emitter(state.copyWith(status: SignInLoadingStatus.error));
    }
  }
}
