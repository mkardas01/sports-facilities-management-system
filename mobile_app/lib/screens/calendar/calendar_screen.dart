import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sport_plus/screens/calendar/bloc/calendar_bloc.dart';
import 'package:sport_plus/screens/calendar/widgets/trainings_calendar.dart';
import 'package:sport_plus/screens/calendar/widgets/trainings_list.dart';
import 'package:sport_plus/widgets/app_scaffold.dart';
import 'package:sport_plus/widgets/loading_view.dart';

class CalendarScreen extends StatefulWidget {
  const CalendarScreen({super.key});
  static const String route = "/calendar";

  @override
  State<CalendarScreen> createState() => _CalendarScreenState();
}

class _CalendarScreenState extends State<CalendarScreen> {
  DateTime selectedDay = DateTime.now();
  DateTime focusedDay = DateTime.now();

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<CalendarBloc, CalendarState>(
      builder: (context, state) {
        if (!state.isLoaded) {
          return const LoadingView();
        }
        return AppScaffold(
          setPadding: false,
          child: Column(
            children: [
              TrainingsCalendar(
                trainings: state.trainings,
                focusedDay: focusedDay,
                selectedDay: selectedDay,
                onChanged: (value) {
                  setState(() {
                    focusedDay = value;
                    selectedDay = value;
                  });
                },
              ),
              TrainingsList(trainings: state.trainings[selectedDay] ?? [])
            ],
          ),
        );
      },
    );
  }
}
