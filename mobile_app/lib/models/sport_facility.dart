import 'package:sport_plus/models/coach.dart';
import 'package:sport_plus/models/open_hours.dart';
import 'package:sport_plus/models/rating.dart';
import 'package:sport_plus/models/sport_equipment.dart';
import 'package:sport_plus/models/sport_facility_news.dart';
import 'package:sport_plus/models/training_session.dart';

class SportFacility {
  final int id;
  final String name;
  final String description;
  final String address;
  final String type;
  final bool membershipRequired;
  final String imageUrl;
  final OpenHours openHours;
  final Rating rating;
  final List<SportFacilityNews> news;

  final List<SportEquipment> equipment;
  final List<Coach> coaches;
  final List<TrainingSession> tranings;

  SportFacility(
      {required this.id,
      required this.name,
      required this.description,
      required this.address,
      required this.type,
      required this.membershipRequired,
      required this.imageUrl,
      required this.news,
      required this.equipment,
      required this.coaches,
      required this.openHours,
      required this.rating,
      required this.tranings});
}
