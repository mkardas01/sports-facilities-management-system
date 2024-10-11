import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sport_plus/screens/calendar/bloc/calendar_bloc.dart';
import 'package:sport_plus/screens/calendar/widgets/trainings_calendar.dart';
import 'package:sport_plus/screens/calendar/widgets/trainings_list.dart';
import 'package:sport_plus/widgets/app_scaffold.dart';

class CalendarLoadedView extends StatefulWidget {
  const CalendarLoadedView({super.key});

  @override
  State<CalendarLoadedView> createState() => _CalendarLoadedViewState();
}

class _CalendarLoadedViewState extends State<CalendarLoadedView> {
  DateTime selectedDay = DateTime.now();
  DateTime focusedDay = DateTime.now();
  @override
  Widget build(BuildContext context) {
    return BlocBuilder<CalendarBloc, CalendarState>(
      builder: (context, state) {
        return AppScaffold(
          setPadding: false,
          child: Column(
            children: [
              TrainingsCalendar(
                trainings: state.trainings,
                focusedDay: focusedDay,
                selectedDay: selectedDay,
                onChanged: (value) {
                  if (focusedDay.month != value.month) {
                    context.read<CalendarBloc>().add(MonthchangedEvent(value));
                  }
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
