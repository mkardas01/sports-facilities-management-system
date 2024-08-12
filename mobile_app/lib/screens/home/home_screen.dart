import 'package:flutter/material.dart';
import 'package:sport_plus/widgets/app_scaffold.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});
  static const String route = "/home";

  @override
  Widget build(BuildContext context) {
    return const AppScaffold(
      child: Text("Home"),
    );
  }
}
