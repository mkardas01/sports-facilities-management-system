import 'package:sport_plus/models/coach.dart';
import 'package:sport_plus/models/rating.dart';
import 'package:sport_plus/models/user.dart';

class TrainingSession {
  final int id;
  final Coach coach;
  final String name;
  final int startHour;
  final int duration;
  final bool isWeekly;
  final DateTime trainingDate;
  final int capacity;
  final bool freeBooked;
  final Rating rating;
  final List<User> participants;

  TrainingSession(
      {required this.id,
      required this.coach,
      required this.name,
      required this.startHour,
      required this.duration,
      required this.isWeekly,
      required this.trainingDate,
      required this.capacity,
      required this.freeBooked,
      required this.participants,
      required this.rating});
}
