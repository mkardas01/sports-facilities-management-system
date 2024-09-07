import 'dart:developer';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/models/auth/login_user_dto.dart';
import 'package:sport_plus/models/auth/register_user_dto.dart';
import 'package:sport_plus/repository/auth_repository.dart';
import 'package:sport_plus/services/storage_service.dart';

part 'sign_in_event.dart';
part 'sign_in_state.dart';

class SignInBloc extends Bloc<SignInEvent, SignInState> {
  final AuthRepository authRepository;
  final StorageService storageService;

  SignInBloc({required this.authRepository, required this.storageService})
      : super(const SignInState()) {
    on<LogInEvent>(_logIn);
    on<RegisterEvent>(_registerUser);
    on<OnStartEvent>(_onStart);
  }
  Future<void> _onStart(
      OnStartEvent event, Emitter<SignInState> emitter) async {
    if (await storageService.isTokenValid()) {
      emitter(state.copyWith(status: SignInLoadingStatus.loggedIn));
    }
  }

  Future<void> _logIn(LogInEvent event, Emitter<SignInState> emitter) async {
    emitter(state.copyWith(status: SignInLoadingStatus.loggedIn));
    /* var result = await authRepository.logIn(LoginUserDto(
      email: event.email,
      password: event.password,
    ));
    if (result != null) {
      log(result);
      storageService.addToken(result);
      emitter(state.copyWith(status: SignInLoadingStatus.loggedIn));
    } else {
      emitter(state.copyWith(status: SignInLoadingStatus.error));
    }*/
  }

  Future<void> _registerUser(
      RegisterEvent event, Emitter<SignInState> emitter) async {
    emitter(state.copyWith(status: SignInLoadingStatus.loading));
    var result = await authRepository.registerUser(RegisterUserDto(
        email: event.email,
        password: event.password,
        name: event.name,
        surname: event.surname));
    if (result != null) {
      storageService.addToken(result);
      emitter(state.copyWith(status: SignInLoadingStatus.loggedIn));
    } else {
      emitter(state.copyWith(status: SignInLoadingStatus.error));
    }
  }
}
