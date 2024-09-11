import 'dart:ui';

import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/screens/map/models/coordinates.dart';

class FacilityCoords {
  final int facilityId;
  final String name;
  final String address;
  final Coordinates cords;
  final Color pinColor;
  FacilityCoords(
      {required this.cords,
      required this.facilityId,
      required this.name,
      required this.address,
      required this.pinColor});
}

//TODO resolve all types
Color resolvePinColor(String type) {
  if (type == "Gym") return AppColors.gym;
  return AppColors.icerink;
}
