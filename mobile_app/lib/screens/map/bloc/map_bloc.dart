import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/config/app_consts.dart';
import 'package:sport_plus/models/sport_facility.dart';
import 'package:sport_plus/repository/sport_facility_repository.dart';
import 'package:sport_plus/screens/map/models/coordinates.dart';
import 'package:sport_plus/screens/map/models/facility_coords.dart';
import 'package:sport_plus/services/location_service.dart';

part 'map_event.dart';
part 'map_state.dart';

class MapBloc extends Bloc<MapEvent, MapState> {
  final LocationService locationService;
  final SportFacilityRepository sportFacilityRepository;
  MapBloc(
      {required this.locationService, required this.sportFacilityRepository})
      : super(const MapState()) {
    on<InitDataEvent>(_initData);
  }

  Future<void> _initData(InitDataEvent event, Emitter<MapState> emitter) async {
    emitter(state.copyWith(isLoaded: false));
    var userCords = await locationService.getCoordinates();
    List<FacilityCoords> cords = [];
    // var gyms = await sportFacilityRepository.getAllFacilities() ?? [];
    var gyms = [
      SportFacility(
        id: 1,
        name: "fabryka formy",
        description: "silownia",
        address: "pl. Władysława Andersa 7, 61-894 Poznań",
        type: "silownia",
        membershipRequired: true,
        imageUrl: "test_url",
      ),
      SportFacility(
        id: 2,
        name: "fabryka formy",
        description: "silownia2",
        address: "Roosevelta 22, 60-829 Poznań",
        type: "silownia",
        membershipRequired: true,
        imageUrl: "test_url",
      ),
      SportFacility(
        id: 3,
        name: "test",
        description: "test",
        address: "Pleszewska 1, 61-136 Poznań",
        type: "test",
        membershipRequired: false,
        imageUrl: "test",
      ),
    ];
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
