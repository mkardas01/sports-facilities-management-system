import 'package:geocoding/geocoding.dart';
import 'package:sport_plus/models/coordinates.dart';

class LocationService {
  Future<Coordinates> getCoordsFromAddres(String addres) async {
    List<Location> locations = await locationFromAddress(addres);
    return Coordinates(locations[0].latitude, locations[0].longitude);
  }
}
