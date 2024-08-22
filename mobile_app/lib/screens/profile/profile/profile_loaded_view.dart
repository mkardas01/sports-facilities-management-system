import 'dart:io';

import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/config/app_typography.dart';
import 'package:sport_plus/models/user.dart';
import 'package:sport_plus/screens/profile/edit_profile/edit_profile_screen.dart';
import 'package:sport_plus/screens/profile/profile/widgets/profil_info_label.dart';
import 'package:sport_plus/widgets/app_scaffold.dart';
import 'package:sport_plus/widgets/generic_button.dart';

class ProfileLoadedView extends StatelessWidget {
  final User user;
  final File? avatarFile;
  const ProfileLoadedView(
      {super.key, required this.user, required this.avatarFile});

  @override
  Widget build(BuildContext context) {
    return AppScaffold(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            alignment: Alignment.center,
            margin: const EdgeInsets.symmetric(vertical: 20),
            child: CircleAvatar(
              minRadius: 50,
              maxRadius: 80,
              backgroundColor: AppColors.backgroundColor,
              backgroundImage:
                  avatarFile != null ? FileImage(avatarFile!) : null,
              child: user.imageUrl == null
                  ? Text(
                      user.getInitials,
                      style: AppTypography.bigBoldTextStyle(),
                    )
                  : null,
            ),
          ),
          ProfilInfoLabel(
              label: AppStrings.nameSurname,
              value: "${user.name} ${user.surname}"),
          ProfilInfoLabel(label: AppStrings.email, value: "${user.email}"),
          const Spacer(),
          Padding(
            padding: const EdgeInsets.all(10),
            child: GenericButton(
                onTap: () =>
                    Navigator.pushNamed(context, EditProfileScreen.route),
                title: AppStrings.edit),
          ),
        ],
      ),
    );
  }
}
