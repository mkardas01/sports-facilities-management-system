import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';

class GenericDialog extends StatelessWidget {
  final String title;
  const GenericDialog({super.key, required this.title});

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      insetPadding: const EdgeInsets.all(20),
      title: Text(
        title,
        textAlign: TextAlign.center,
      ),
      content: ElevatedButton(
          style: ElevatedButton.styleFrom(
              foregroundColor: Colors.white,
              backgroundColor: AppColors.mainColor,
              minimumSize: const Size(50, 50)),
          onPressed: () => Navigator.pop(context),
          child: const Text(AppStrings.agree)),
    );
  }
}
