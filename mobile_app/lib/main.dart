import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/navigation_routes.dart';
import 'package:sport_plus/screens/home/home_screen.dart';
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
      title: 'Sport+',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: AppColors.mainColor),
        useMaterial3: true,
      ),
      initialRoute: HomeScreen.route,
      routes: routes,
    );
  }
}
