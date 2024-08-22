import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';

class GenericButton extends StatelessWidget {
  final String title;
  final void Function() onTap;
  final bool takeLessSpace;
  const GenericButton(
      {super.key,
      required this.onTap,
      required this.title,
      this.takeLessSpace = false});

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      style: ElevatedButton.styleFrom(
        minimumSize: Size.fromHeight(takeLessSpace ? 40 : 50),
        foregroundColor: Colors.white,
        backgroundColor: AppColors.mainColor,
      ),
      onPressed: onTap,
      child: Text(title),
    );
  }
}
