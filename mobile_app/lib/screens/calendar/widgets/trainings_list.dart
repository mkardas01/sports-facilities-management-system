import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/config/app_typography.dart';
import 'package:sport_plus/models/details/training_session.dart';
import 'package:sport_plus/screens/calendar/bloc/calendar_bloc.dart';

class TrainingsList extends StatelessWidget {
  final List<TrainingSession> trainings;
  const TrainingsList({super.key, required this.trainings});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        ...trainings.map(
          (training) => Padding(
            padding: const EdgeInsets.all(8.0),
            child: ListTile(
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(15)),
              dense: true,
              tileColor: AppColors.backgroundColor,
              isThreeLine: true,
              title: Text(
                "Cardio",
                style: AppTypography.bigTextStyle,
              ),
              subtitle: const Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text("16:00 - 17:00, Main Gym"),
                  Text("trener: Anna Michalak"),
                ],
              ),
              trailing: TextButton(
                onPressed: () => context
                    .read<CalendarBloc>()
                    .add(GiveupTrainingEvent(training.id ?? -1)),
                child: const Text(AppStrings.giveup),
              ),
            ),
          ),
        ),
      ],
    );
  }
}
