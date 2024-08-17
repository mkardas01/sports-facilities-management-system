import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/screens/trainings/bloc/trainings_bloc.dart';

class TrainingParticipationDialog extends StatelessWidget {
  final int trainingId;
  const TrainingParticipationDialog({super.key, required this.trainingId});

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      insetPadding: const EdgeInsets.all(20),
      title: const Text(
        AppStrings.trainingParticipation,
        textAlign: TextAlign.center,
      ),
      content: Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          Expanded(
            child: ElevatedButton(
                onPressed: () => Navigator.pop(context),
                child: const Text(AppStrings.no)),
          ),
          const SizedBox(width: 50),
          Expanded(
            child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  foregroundColor: Colors.white,
                  backgroundColor: AppColors.mainColor,
                ),
                onPressed: () {
                  Navigator.pop(context);
                  context
                      .read<TrainingsBloc>()
                      .add(SingUpForTrainingEvent(trainingId));
                },
                child: const Text(AppStrings.yes)),
          ),
        ],
      ),
    );
  }
}
