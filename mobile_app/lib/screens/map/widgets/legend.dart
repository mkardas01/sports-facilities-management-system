import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/config/app_typography.dart';
import 'package:sport_plus/screens/map/widgets/legend_row.dart';

class Legend extends StatelessWidget {
  const Legend({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 5),
      color: Colors.white,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisSize: MainAxisSize.min,
        children: [
          Text(
            AppStrings.legend,
            style: AppTypography.defaultBoldTextStyle(),
          ),
          const LegendRow(color: AppColors.icerink, text: AppStrings.icerink),
          const LegendRow(color: AppColors.gym, text: AppStrings.gym),
          const LegendRow(color: AppColors.field, text: AppStrings.field),
          const LegendRow(
              color: AppColors.otherType, text: AppStrings.otherType),
        ],
      ),
    );
  }
}
