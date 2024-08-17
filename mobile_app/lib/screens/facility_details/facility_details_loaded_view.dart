import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/screens/facility_details/models/day_training.dart';
import 'package:sport_plus/models/sport_facility.dart';
import 'package:sport_plus/screens/facility_details/models/open_hours_item.dart';
import 'package:sport_plus/screens/facility_details/widgets/coach_tab.dart';
import 'package:sport_plus/screens/facility_details/widgets/equipment_tab.dart';
import 'package:sport_plus/screens/facility_details/widgets/info_tab.dart';
import 'package:sport_plus/widgets/app_scaffold.dart';
import 'package:flutter_rating_stars/flutter_rating_stars.dart';

class FacilityDetailsLoadedView extends StatelessWidget {
  final SportFacility facility;
  final List<DayTrainings> trainings;
  final List<OpenHoursItem> openHours;
  const FacilityDetailsLoadedView(
      {super.key,
      required this.facility,
      required this.trainings,
      required this.openHours});

  @override
  Widget build(BuildContext context) {
    return AppScaffold(
      setPadding: false,
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Stack(
            alignment: Alignment.bottomRight,
            children: [
              Image.network(facility.imageUrl),
              Align(
                alignment: Alignment.bottomRight,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: RatingStars(
                    value: facility.rating.rate.toDouble(),
                    valueLabelVisibility: false,
                  ),
                ),
              ),
            ],
          ),
          Expanded(
            child: DefaultTabController(
              length: 3,
              child: Column(
                children: <Widget>[
                  Container(
                    color: AppColors.backgroundColor,
                    child: const TabBar(
                      labelColor: Colors.white,
                      unselectedLabelColor: Colors.black,
                      indicatorColor: AppColors.mainColor,
                      indicatorSize: TabBarIndicatorSize.tab,
                      tabs: [
                        Tab(text: AppStrings.infoTab),
                        Tab(text: AppStrings.trainersTab),
                        Tab(text: AppStrings.equipmentTab),
                      ],
                    ),
                  ),
                  Expanded(
                    child: TabBarView(
                      children: [
                        InfoTab(
                          facility: facility,
                          trainings: trainings,
                          openHours: openHours,
                        ),
                        CoachTab(
                          coaches: facility.coaches,
                          trainings: trainings,
                        ),
                        EquipmentTab(
                          equipment: facility.equipment,
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
