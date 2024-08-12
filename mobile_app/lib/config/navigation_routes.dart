import 'package:flutter/material.dart';
import 'package:sport_plus/screens/home/home_screen.dart';

Map<String, Widget Function(BuildContext)> routes = {
  HomeScreen.route: (context) => const HomeScreen(),
};
