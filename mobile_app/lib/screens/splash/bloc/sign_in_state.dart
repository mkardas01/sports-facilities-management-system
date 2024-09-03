part of 'sign_in_bloc.dart';

enum SignInLoadingStatus { idle, loading, error, loggedIn }

class SignInState extends Equatable {
  final SignInLoadingStatus status;
  const SignInState({this.status = SignInLoadingStatus.idle});

  SignInState copyWith({SignInLoadingStatus? status}) {
    return SignInState(status: status ?? this.status);
  }

  @override
  List<Object> get props => [status];
}
