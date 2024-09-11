import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/screens/map/models/coordinates.dart';
import 'package:sport_plus/models/dummy_data.dart';
import 'package:sport_plus/screens/map/models/facility_coords.dart';
import 'package:sport_plus/services/location_service.dart';

part 'map_event.dart';
part 'map_state.dart';

class MapBloc extends Bloc<MapEvent, MapState> {
  final LocationService locationService;
  MapBloc({required this.locationService}) : super(const MapState()) {
    on<InitDataEvent>(_initData);
  }

  Future<void> _initData(InitDataEvent event, Emitter<MapState> emitter) async {
    var userCords = await locationService.getCoordinates();
    List<FacilityCoords> cords = [];
    var gyms = DummyData.getSportFacilities();
    for (var gym in gyms) {
      cords.add(FacilityCoords(
          cords: await locationService.getCoordsFromAddres(gym.address),
          facilityId: gym.id,
          name: gym.name,
          address: gym.address,
          pinColor: resolvePinColor(gym.type)));
    }
    emitter(
        state.copyWith(isLoaded: true, cords: cords, userLocation: userCords));
  }
}
