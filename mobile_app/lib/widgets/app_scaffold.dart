import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/screens/profile/profile/profile_screen.dart';
import 'package:sport_plus/widgets/images/bar_logo.dart';

class AppScaffold extends StatelessWidget {
  final Widget child;
  final bool showDrawer;
  final bool setPadding;
  const AppScaffold(
      {super.key,
      required this.child,
      this.showDrawer = false,
      this.setPadding = true});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        foregroundColor: Colors.white,
        backgroundColor: AppColors.mainColor,
        title: const Center(
          child: SizedBox(
            height: 50,
            child: BarLogo(),
          ),
        ),
      ),
      drawer: showDrawer
          ? Drawer(
              width: MediaQuery.of(context).size.width * 0.6,
              child: Center(
                child: ListTile(
                  onTap: () =>
                      Navigator.pushNamed(context, ProfileScreen.route),
                  leading: const Icon(Icons.person),
                  title: const Text(AppStrings.profile),
                ),
              ),
            )
          : null,
      body: Padding(
        padding: EdgeInsets.all(setPadding ? 20 : 0),
        child: child,
      ),
    );
  }
}
