part of 'calendar_bloc.dart';

abstract class CalendarEvent {}

class InitTrainingDataEvent extends CalendarEvent {}

class GiveupTrainingEvent extends CalendarEvent {
  final int trainingId;
  GiveupTrainingEvent(this.trainingId);
}
