import 'package:sport_plus/models/details/coach.dart';
import 'package:sport_plus/models/details/coach_avarege_rating.dart';
import 'package:sport_plus/models/details/open_time.dart';
import 'package:sport_plus/models/details/sport_equipment.dart';

class SportFacilityDetails {
  int? id;
  String? name;
  String? description;
  String? address;
  String? type;
  bool membershipRequired;
  String? imageUrl;
  OpenHour? openHours;
  List<Coach>? coaches;
  List<SportEquipment>? equipment;
  double? averageRating;
  List<CoachAverageRating>? coachRatings;

  SportFacilityDetails({
    this.id,
    this.name,
    this.description,
    this.address,
    this.type,
    this.membershipRequired = false,
    this.imageUrl,
    this.openHours,
    this.coaches,
    this.equipment,
    this.averageRating,
    this.coachRatings,
  });

  factory SportFacilityDetails.fromJson(Map<String, dynamic> json) {
    return SportFacilityDetails(
      id: json['id'],
      name: json['name'],
      description: json['description'],
      address: json['address'],
      type: json['type'],
      membershipRequired: json['membershipRequired'] ?? false,
      imageUrl: json['imageUrl'],
      openHours: json['openHours'] != null
          ? OpenHour.fromJson(json['openHours'])
          : null,
      coaches: json['coaches'] != null
          ? (json['coaches'] as List).map((i) => Coach.fromJson(i)).toList()
          : null,
      equipment: json['equipment'] != null
          ? (json['equipment'] as List)
              .map((i) => SportEquipment.fromJson(i))
              .toList()
          : null,
      averageRating: json['averageRating']?.toDouble(),
      coachRatings: json['coachRatings'] != null
          ? (json['coachRatings'] as List)
              .map((i) => CoachAverageRating.fromJson(i))
              .toList()
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'description': description,
      'address': address,
      'type': type,
      'membershipRequired': membershipRequired,
      'imageUrl': imageUrl,
      'openHours': openHours?.toJson(),
      'coaches': coaches?.map((e) => e.toJson()).toList(),
      'equipment': equipment?.map((e) => e.toJson()).toList(),
      'averageRating': averageRating,
      'coachRatings': coachRatings?.map((e) => e.toJson()).toList(),
    };
  }
}
