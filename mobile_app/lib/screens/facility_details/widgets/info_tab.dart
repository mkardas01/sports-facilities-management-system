import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/config/app_typography.dart';
import 'package:sport_plus/screens/facility_details/models/day_training.dart';
import 'package:sport_plus/models/sport_facility.dart';
import 'package:sport_plus/screens/facility_details/models/open_hours_item.dart';
import 'package:sport_plus/screens/facility_details/widgets/info_label.dart';
import 'package:sport_plus/screens/facility_details/widgets/news_dialog.dart';
import 'package:sport_plus/screens/trainings/trainings_screen.dart';

class InfoTab extends StatelessWidget {
  final SportFacility facility;
  final List<DayTrainings> trainings;
  final List<OpenHoursItem> openHours;
  const InfoTab(
      {super.key,
      required this.facility,
      required this.trainings,
      required this.openHours});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(20),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Text(
            facility.name,
            style: AppTypography.bigBoldTextStyle(),
          ),
          Text(
            facility.description,
            style: AppTypography.defaultTextStyle,
          ),
          const SizedBox(height: 20),
          InfoLabel(
              data: Text(
                facility.address,
                style: AppTypography.defaultBoldTextStyle(),
              ),
              title: AppStrings.address),
          InfoLabel(
              data: facility.membershipRequired
                  ? const Icon(
                      Icons.check,
                      color: Colors.green,
                    )
                  : const Icon(
                      Icons.close,
                      color: Colors.red,
                    ),
              title: AppStrings.membershipRequired),
          Text(
            AppStrings.openHours,
            style: AppTypography.defaultBoldTextStyle(),
          ),
          Expanded(
            child: ListView.builder(
              physics: const NeverScrollableScrollPhysics(),
              itemCount: openHours.length,
              itemBuilder: (context, index) {
                return InfoLabel(
                  showDivider: false,
                  data: Text(
                    openHours[index].value,
                    style: AppTypography.defaultBoldTextStyle(),
                  ),
                  title: openHours[index].day,
                );
              },
            ),
          ),
          Row(
            children: [
              Expanded(
                child: TextButton(
                  onPressed: () => Navigator.pushNamed(
                      context, TrainingsScreen.route,
                      arguments: trainings),
                  child: const Text(AppStrings.checkTrainings),
                ),
              ),
              Expanded(
                child: TextButton(
                  onPressed: () => showDialog(
                    context: context,
                    builder: (context) => NewsDialog(news: facility.news),
                  ),
                  child: const Text(AppStrings.whatsnew),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
