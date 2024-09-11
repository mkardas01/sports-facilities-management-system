import 'package:sport_plus/models/details/coach.dart';
import 'package:sport_plus/models/details/training_session_participant.dart';

class TrainingSession {
  int? id;
  int? coachesId;
  int? sportFacilitiesId;
  String? name;
  String? startHour;
  int? duration;
  int? isWeekly;
  DateTime? trainingDate;
  int? capacity;
  int? freeBooked;
  Coach? coach;
  List<TrainingSessionParticipant>? trainingSessionParticipants;

  TrainingSession({
    this.id,
    this.coachesId,
    this.sportFacilitiesId,
    this.name,
    this.startHour,
    this.duration,
    this.isWeekly,
    this.trainingDate,
    this.capacity,
    this.freeBooked,
    this.coach,
    this.trainingSessionParticipants,
  });

  factory TrainingSession.fromJson(Map<String, dynamic> json) {
    return TrainingSession(
      id: json['id'] as int?,
      coachesId: json['coachesId'] as int?,
      sportFacilitiesId: json['sportFacilitiesId'] as int?,
      name: json['name'] as String?,
      startHour: json['startHour'] as String?,
      duration: json['duration'] as int?,
      isWeekly: json['isWeekly'] as int?,
      trainingDate: json['trainingDate'] != null
          ? DateTime.parse(json['trainingDate'])
          : null,
      capacity: json['capacity'] as int?,
      freeBooked: json['freeBooked'] as int?,
      coach: json['coach'] != null ? Coach.fromJson(json['coach']) : null,
      trainingSessionParticipants:
          (json['trainingSessionParticipants'] as List<dynamic>?)
              ?.map((e) => TrainingSessionParticipant.fromJson(e))
              .toList(),
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['coachesId'] = coachesId;
    data['sportFacilitiesId'] = sportFacilitiesId;
    data['name'] = name;
    data['startHour'] = startHour;
    data['duration'] = duration;
    data['isWeekly'] = isWeekly;
    data['trainingDate'] = trainingDate?.toIso8601String();
    data['capacity'] = capacity;
    data['freeBooked'] = freeBooked;
    if (coach != null) {
      data['coach'] = coach!.toJson();
    }

    if (trainingSessionParticipants != null) {
      data['trainingSessionParticipants'] =
          trainingSessionParticipants!.map((e) => e.toJson()).toList();
    }
    return data;
  }
}
