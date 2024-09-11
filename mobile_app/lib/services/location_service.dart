import 'package:geocoding/geocoding.dart';
import 'package:geolocator/geolocator.dart';
import 'package:logger/logger.dart';
import 'package:sport_plus/screens/map/models/coordinates.dart';
import 'package:sport_plus/services/locator.dart';

class LocationService {
  final Logger _logger = locator.get<Logger>();

  Future<bool> checkIsLocationEnabled() async {
    bool serviceEnabled;
    LocationPermission permission;

    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      _logger.e('Location services are disabled.');
      return false;
    }

    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        _logger.e('Location permissions are denied');
        return false;
      }
    }

    if (permission == LocationPermission.deniedForever) {
      _logger.e(
          'Location permissions are permanently denied, we cannot request permissions.');
      return false;
    }

    return true;
  }

  Future<Coordinates> getCoordsFromAddres(String addres) async {
    List<Location> locations = await locationFromAddress(addres);
    return Coordinates(locations[0].latitude, locations[0].longitude);
  }

  Future<Coordinates?> getCoordinates() async {
    if (!await checkIsLocationEnabled()) {
      _logger.e("Can't check location, enable location in phone settings");
      return null;
    }

    var position = await Geolocator.getCurrentPosition();
    return Coordinates(position.latitude, position.longitude);
  }
}
