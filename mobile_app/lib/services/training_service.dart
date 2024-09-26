import 'package:sport_plus/models/training_session/training_session.dart';
import 'package:sport_plus/screens/facility_details/models/day_training.dart';

class TrainingService {
  List<DayTrainings> extractTrainings(List<TrainingSession> data) {
    List<DayTrainings> trainings = [];
    for (var training in data) {
      var date = training.trainingDate;
      var startTime = training.startHour?.split(":") ?? []; //TODO check
      var startHour = int.tryParse(startTime[0]);
      var startMin = int.tryParse(startTime[1]) ?? 0;
      var duration = training.duration;
      if (date == null || startHour == null || duration == null) continue;
      var trainingId = training.id ?? -1;
      var freeBooked = training.freeBooked ?? 0;
      var coachId = training.coachesId ?? -1;
      var coachName = training.coach?.name ?? "";
      var coachSurname = training.coach?.surname ?? "";
      var trainingName = training.name ?? "";
      if (training.isWeekly == 1) {
        var trainingDate = _getTrainingDateThisWeek(date);
        trainings.add(DayTrainings(
            trainingId: trainingId,
            freeBooked: freeBooked,
            coachId: coachId,
            description: "prowadzone przez: $coachName $coachSurname",
            end: trainingDate
                .add(Duration(hours: startHour, minutes: startMin + duration)),
            start:
                trainingDate.add(Duration(hours: startHour, minutes: startMin)),
            title: trainingName));
      } else if (_isSameWeek(date)) {
        trainings.add(DayTrainings(
            trainingId: trainingId,
            freeBooked: freeBooked,
            coachId: coachId,
            description: "prowadzone przez: $coachName $coachSurname",
            end: date
                .add(Duration(hours: startHour, minutes: startMin + duration)),
            start: date.add(Duration(hours: startHour, minutes: startMin)),
            title: trainingName));
      }
    }
    return trainings;
  }

  bool _isSameWeek(DateTime dateToCheck) {
    DateTime now = DateTime.now();

    DateTime startOfWeek = now.subtract(Duration(days: now.weekday - 1));

    DateTime endOfWeek = startOfWeek.add(const Duration(days: 6));

    return dateToCheck.isAfter(startOfWeek.subtract(const Duration(days: 1))) &&
        dateToCheck.isBefore(endOfWeek.add(const Duration(days: 1)));
  }

  DateTime _getTrainingDateThisWeek(DateTime firstTrainingDate) {
    DateTime nowDate = DateTime.now();
    DateTime now = DateTime(nowDate.year, nowDate.month, nowDate.day);
    DateTime startOfWeek = now.subtract(Duration(days: now.weekday - 1));

    int daysDifference = firstTrainingDate.weekday - startOfWeek.weekday;

    DateTime trainingDateThisWeek =
        startOfWeek.add(Duration(days: daysDifference));

    /*if (trainingDateThisWeek.isBefore(now)) {
      trainingDateThisWeek = trainingDateThisWeek.add(const Duration(days: 7));
    }*/

    return trainingDateThisWeek;
  }
}
