import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
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
          ? const Drawer(
              child: Center(
                child: ListTile(
                  leading: Icon(Icons.person),
                  title: Text(AppStrings.profile),
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
