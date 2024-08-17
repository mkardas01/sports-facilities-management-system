import 'package:flutter/material.dart';
import 'package:flutter_rating_stars/flutter_rating_stars.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/config/app_typography.dart';
import 'package:sport_plus/models/coach.dart';
import 'package:sport_plus/screens/facility_details/models/day_training.dart';
import 'package:sport_plus/screens/trainings/trainings_screen.dart';

class CoachTab extends StatelessWidget {
  final List<Coach> coaches;
  final List<DayTrainings> trainings;
  const CoachTab({super.key, required this.coaches, required this.trainings});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          ...coaches.map(
            (coach) => Card(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15),
              ),
              margin: const EdgeInsets.all(10),
              child: Row(
                children: [
                  ClipRRect(
                    borderRadius: const BorderRadius.only(
                        topLeft: Radius.circular(15),
                        bottomLeft: Radius.circular(15)),
                    child: Image.network(
                      coach.imageUrl,
                      width: MediaQuery.of(context).size.width * 0.35,
                    ),
                  ),
                  Expanded(
                    child: Padding(
                      padding: const EdgeInsets.all(8),
                      child: Column(
                        children: [
                          Align(
                            alignment: Alignment.topRight,
                            child: RatingStars(
                              value: coach.rating.rate.toDouble(),
                              valueLabelVisibility: false,
                            ),
                          ),
                          const SizedBox(height: 20),
                          Text(
                            "${coach.name} ${coach.surname}",
                            style: AppTypography.bigBoldTextStyle,
                          ),
                          TextButton(
                              onPressed: () => Navigator.pushNamed(
                                  context, TrainingsScreen.route,
                                  arguments: trainings
                                      .where((element) =>
                                          element.coachId == coach.id)
                                      .toList()),
                              child: const Text(AppStrings.checkTrainings)),
                        ],
                      ),
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
