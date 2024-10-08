import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sport_plus/screens/calendar/bloc/calendar_bloc.dart';
import 'package:sport_plus/screens/calendar/calendar_loaded_view.dart';
import 'package:sport_plus/widgets/error_view.dart';
import 'package:sport_plus/widgets/loading_view.dart';

class CalendarScreen extends StatelessWidget {
  const CalendarScreen({super.key});
  static const String route = "/calendar";

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<CalendarBloc, CalendarState>(
      builder: (context, state) {
        switch (state.status) {
          case CalendarLoadingStatus.loading:
            return const LoadingView();
          case CalendarLoadingStatus.loaded:
            return const CalendarLoadedView();
          case CalendarLoadingStatus.error:
            return const ErrorView();
        }
      },
    );
  }
}
