import 'package:sport_plus/screens/facility_details/models/day_training.dart';
import 'package:sport_plus/models/sport_facility.dart';

class TrainingService {
  List<DayTrainings> extractTrainings(SportFacility facility) {
    List<DayTrainings> trainings = [];
    for (var training in facility.tranings) {
      if (training.isWeekly) {
        var trainingDate = _getTrainingDateThisWeek(training.trainingDate);
        trainings.add(DayTrainings(
            trainingId: training.id,
            freeBooked: training.freeBooked,
            coachId: training.coach.id,
            description:
                "prowadzone przez: ${training.coach.name} ${training.coach.surname}",
            end: trainingDate
                .add(Duration(hours: training.startHour + training.duration)),
            start: trainingDate.add(Duration(hours: training.startHour)),
            title: training.name));
      } else if (_isSameWeek(training.trainingDate)) {
        trainings.add(DayTrainings(
            trainingId: training.id,
            freeBooked: training.freeBooked,
            coachId: training.coach.id,
            description:
                "prowadzone przez: ${training.coach.name} ${training.coach.surname}",
            end: training.trainingDate
                .add(Duration(hours: training.startHour + training.duration)),
            start:
                training.trainingDate.add(Duration(hours: training.startHour)),
            title: training.name));
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
