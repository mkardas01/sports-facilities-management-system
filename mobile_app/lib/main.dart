import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_consts.dart';
import 'package:sport_plus/config/navigation_routes.dart';
import 'package:sport_plus/screens/splash/splash_screen.dart';
import 'package:sport_plus/services/locator.dart';

void main() {
  setUp();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: AppConsts.appName,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: AppColors.mainColor),
        useMaterial3: true,
      ),
      initialRoute: SplashScreen.route,
      routes: routes,
    );
  }
}
